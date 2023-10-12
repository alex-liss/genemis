// database initialization script

db = db.getSiblingDB('genemis');

db.createUser({
    user: process.env.GENEMIS_DB_USERNAME,
    pwd: process.env.GENEMIS_DB_PASSWORD,
    roles: [{ role: 'readWrite', db: 'genemis' }],
});
