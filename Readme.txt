Preset:
Set the values of proxy in application.properties
The property proxy.enabled is set to 'false' by default

Service APIs:
GET : /v1/users/count -> Provides the number of unique users
PUT : /v1/users/{itemId} - Provides the user details for the itemId provided
Request Body: 
{
	"title":"1-800 Flower",
	"body":"1-800 Flower "
}