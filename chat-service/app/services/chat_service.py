import httpx
from app.repositories.property_client import get_property_details
from app.core.config import GROQ_API_KEY


async def get_ai_response(property_id: str, user_query: str) -> str:
    property_data = get_property_details(property_id)

    #Build the prompt for LLM
    prompt = f"""
    You are a helpful real estate assistant. Give to-the-point answer to the user-query. Not long answers, but crisp, easy to understand answer.

    Property Info:
    Address: {property_data["address"]}
    City: {property_data["city"]}
    Price: {property_data["price"]}
    Year Built: {property_data["yearBuilt"]}

    User Question: {user_query}
    """

    # Prepare the request payload
    payload = {
        "messages": [
            {"role": "system", "content": "You are a real estate expert assistant."},
            {"role": "user", "content": prompt}
        ],
        "model": "llama3-70b-8192",  # or whichever model Groq supports
        "temperature": 0.7
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
        return response.json()["choices"][0]["message"]["content"].strip()
    else:
        return f"[Groq Error {response.status_code}]: {response.text}"