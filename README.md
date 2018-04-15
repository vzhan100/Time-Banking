# Time-Banking

Time Bank App

Introduction:
This is a mobile based application where people on this platform can trade hours as local currency. This system provides a means for people to request different services and provide diverse services to people on this platform. When a user needs a request, the request is posted which is broadcasted and made available to all users of the application. The request posted includes how much time the requester is willing to trade for the service.
 
The major roles of this system are:
• The requester who will need a service
• The responder who is available to provide service to the requester
 
The major use cases of the system are:
• User signs up with name, location, description
• Requester posts a service
• Responder browses through all available services request
• Responder views approximate time to a service location
• Requester rates the service provided by a responder
 
Example Usage:

A requester creates a post:

Post Title: “Need Tire Changed”

Post Location: [Insert Location]

Post Description: “I need my car tire changed and will pay 30 mins to someone who helps with this”
Post Amount: 30 minutes
System:
Stores info in database
A responder searches for job:
Searches for a post
Responder finds post and click button to view description
System:
Get Responder location and Requester’s job location and calculate distance
Responder selects the job
System sends notification to Requester
Responder goes to job location(Location services must be turned on). Responder can start the job.
Once 30 minutes has passed, system updates both the number of services and the hours for the requester and the responder. 
Requester of the service leave a rating for the responder. 

