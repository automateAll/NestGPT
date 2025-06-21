from fastapi import APIRouter, Query
from app.services.chat_service import get_ai_response

router = APIRouter()

@router.get("")
async def chat(property_id: str = Query(...), query: str = Query(...)):
    """
    Handles chat queries for a given property.
    """
    print("propertyId received from UI={}",property_id)
    print("user query received from UI={}",query)

    response = await get_ai_response(property_id, query)
    print("AI Response= ",response)
    return {"response": response}
