db = db.getSiblingDB('genemis');

//db.createCollection("messages");

db.messages.insertMany([
    {key: "greeting", message: 'Welcome to Genemis', language: 'en'},
    {key: "greeting", message: 'Willkommen im Genemis', language: 'de'}]);