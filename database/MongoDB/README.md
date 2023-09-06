## Mongo DB Docker Container

The _docker-compose.yml_ contains services to run Mongo DB in a container environment.
The database is initialized from _init-db.js_

### Usage

- Create a .env file in current directory with the following content:

```
MONGO_ROOT_USERNAME=<root username>
MONGO_ROOT_PASSWORD=<root password>
MONGO_ROOT_DATABASE=<root database>
MONGO_NETWORK=<docker compose network name>

GENEMIS_USERNAME=<genemis db username>
GENEMIS_PASSWORD=<genemis db password>
```
- Replace the placeholders with the real values
- Run ``docker compose up -d``
