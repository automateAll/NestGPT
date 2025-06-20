import httpx

PROPERTY_SERVICE_URL = "http://localhost:8081/v1/properties"

def get_property_details(property_id: str) -> dict:
    url = f"{PROPERTY_SERVICE_URL}/{property_id}"
    try:
        response = httpx.get(url, timeout=5.0)
        response.raise_for_status()
        return response.json()
    except httpx.HTTPStatusError as e:
        raise RuntimeError(f"Property {property_id} fetch failed: {e.response.text}")
    except Exception as e:
        raise RuntimeError(f"Error contacting property-service: {str(e)}")