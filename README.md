In application.properties add your properties for DB and nasa API_KEY

added flyway migration. Check in db.migration/ 

###
POST http://localhost:8585/nasa/pictures/steal
Content-Type: application/json

{
  "sol":  14
}


###
GET http://localhost:8585/nasa/pictures
