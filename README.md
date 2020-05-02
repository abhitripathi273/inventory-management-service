# inventory-management-service

## Endpoints
1.  GET /inventory
2.  GET /inventory/purchase/{productType}/quantity/{quantity}
3.  GET /inventory/restock/{productType}/quantity/{quantity}

### Sample Request and Response Details

**Request**

curl --location --request GET 'http://localhost:8900/inventory'

**Response**

```json
[
    {
        "productCategory": "laptop",
        "stock": 10
    },
    {
        "productCategory": "mobile",
        "stock": 31
    }
]
```

**Request**

curl --location --request GET 'http://localhost:8900/inventory/purchase/laptop/quantity/6'

**Response**
```
Success - Http status 200 OK
Failure - Http status 400 BAD REQUEST
```
**Request**

curl --location --request GET 'http://localhost:8900/inventory/restock/laptop/quantity/6'

**Response**
```
Success - Http status 200 OK
{
    "productCategory": "mobile",
    "stock": 31
}
Product Category will be added, if not already present in the inventory.
```
