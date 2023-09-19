// database initialization script

db = db.getSiblingDB('genemis');

db.createUser({
    user: process.env.GENEMIS_DB_USERNAME,
    pwd: process.env.GENEMIS_DB_PASSWORD,
    roles: [{ role: 'readWrite', db: 'genemis' }],
});

db.createCollection("messages");

db.messages.insertMany([
    {key: "greeting", message: 'Welcome to Genemis', language: 'en'},
    {key: "greeting", message: 'Willkommen im Genemis', language: 'de'}]);
