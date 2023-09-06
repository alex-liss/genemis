// database initialization script

db = db.getSiblingDB('genemis');

db.createUser({
    user: process.env.GENEMIS_USERNAME,
    pwd: process.env.GENEMIS_PASSWORD,
    roles: [{ role: 'readWrite', db: 'genemis' }],
});

db.createCollection("greetings");

db.greetings.insertMany([
    {greeting: 'Welcome to Genemis', language: 'en'},
    {greeting: 'Willkommen im Genemis', language: 'de'}]);
