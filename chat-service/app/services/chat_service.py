import httpx
from certifi import contents
from starlette.responses import JSONResponse

from app.repositories.property_client import get_property_details
from app.core.config import GROQ_API_KEY

def build_llm_prompt(property_data, user_query: str) -> str:
    #Build the prompt for LLM
    prompt = f"""
    You are a highly learned real estate assistant, 
    You are a witty and knowledgeable real estate assistant. 
    You answer questions within 4 lines and if applicable, share links or references. 

    User asked: {user_query}
    
    About the property:
    - {property_data["bedrooms"]} bed, {property_data["bathrooms"]} bath single-family home
    - Built in {property_data["year_built"]}, listed at ${property_data["price"]}
    - Located at {property_data["address"]}
    
    """
    return prompt

async def get_ai_response(property_id: str, user_query: str) -> str:
    property_data = get_property_details(property_id)
    prompt = build_llm_prompt(property_data,user_query)

    # Prepare the request payload
    payload = {
        "messages": [
            {"role": "system", "content": "You are a knowledgeable and friendly real estate assistant helping users evaluate properties based on their questions. Reply concisely and only about the specific property"},
            {"role": "user", "content": prompt}
        ],
        "model": "llama3-70b-8192",  # or whichever model Groq supports
        "temperature": 0.5
    }

    headers = {
        "Authorization": f"Bearer {GROQ_API_KEY}",
        "Content-Type": "application/json"
    }

    async with httpx.AsyncClient() as client:
        response = await client.post(
            "https://api.groq.com/openai/v1/chat/completions",  # Groq endpoint
            json=payload,
            headers=headers
        )

    if response.status_code == 200:
        content = response.json()["choices"][0]["message"]["content"].strip()
        return JSONResponse(content={"response": content})
    else:
        return JSONResponse(status_code=500, content={"error": f"[Groq Error {response.status_code}]: {response.text}"})