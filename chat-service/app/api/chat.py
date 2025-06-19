from fastapi import APIRouter, Query
from app.services.chat_service import get_ai_response

router = APIRouter()

@router.get("")
async def chat(property_id: str = Query(...), query: str = Query(...)):
    """
    Handles chat queries for a given property.
    """
    response = await get_ai_response(property_id, query)
    return {"response": response}
