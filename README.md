# intuition



This app helps new parents to find quick nearby playground/food/other activities locations for kids.

Intuition App functionality:
* while playing load animation get GPS coord and send them to server.
* serve nearby kid activities.
* provide extensive information about location.
* allow navigation to location.
* allow user to rate place if she/he is signed in.
* future features like rank after weather (will be added later)


Server functionality:
* register location coordinates(get requests geo location coord)
* respond to places requests using Google Location api(make call for user with geolocation coord)
* filter places and send back resources as json(this should be optimised later) 
* store most common places(to be added later) + best rate places
* store users(to be added later)


