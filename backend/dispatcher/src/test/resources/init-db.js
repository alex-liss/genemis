// initializes the testcontainers database with test data

db = db.getSiblingDB('genemis');

db.ts_brent_europe.insertMany([
    {date: new Date('2023-01-01T00:00:00'), price: 1.0, workday: true},
    {date: new Date('2023-01-02T00:00:00'), price: 2.0, workday: true},
    {date: new Date('2023-01-03T00:00:00'), price: 3.0, workday: true},
    {date: new Date('2023-01-04T00:00:00'), price: 4.0, workday: true},
    {date: new Date('2023-01-05T00:00:00'), price: 5.0, workday: false}
]);
