Software_Engineering Administrative Application:

Repository for Team 2's Software Engineering project.

This project is called the Emoji Emotion Checker, where students download an android application which would send them a message at a scheduled time asking how they're feeling at that moment and they would choose the emoji most closely representing that feeling. Then, the response would be sent to the backend database where the admin side would be able to see the responses of each student as well as schedule more messages at times they choose. This project  was spread amongst three teams, and our team handled the administrator aspect.


We created a desktop application using JavaFX that allows the admin to create/modify users data that is stored on AWS Database. We utilized HTTP RESTful operations to send and receive user data through JSON. Our role was to send to the database a timestamp specfic to a user and they would send the message at that time, and we would also have to be able to access the past responses of those students. Basically, responses would gathered through the mobile app, and transferred into the database which would then be queried and that JSON data would be retrieved by this desktop app. The data will be stored and can manipulated for display, namely by being exported into CSV format.

