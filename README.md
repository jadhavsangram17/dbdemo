# dbdemo
Fetch all trades information: localhost:8080/trade method type:GET

Add trade: localhost:8080/trade method type:POST
RAW JSON 
{
        "tradeId": "T2",
        "tradeVersion": 2,
        "counterPartyId": "CP-3",
        "bookId": "B2",
        "maturityDate": "2014-05-20T06:30:00.000+00:00",
        "createdDate": "2021-05-21T06:30:00.000+00:00",
        "expired": "Y"
}

Update expire status: localhost:8080/updateExpiry method type:POST

H2 console can be accessed using below url:
http://localhost:8080/h2-console/