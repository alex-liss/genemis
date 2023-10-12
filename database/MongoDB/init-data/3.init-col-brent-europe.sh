#!/bin/bash

mongoimport --db genemis --collection ts_brent_europe --file /docker-entrypoint-initdb.d/Brent_Europe.csv --type tsv --headerline --username $GENEMIS_DB_USERNAME --password $GENEMIS_DB_PASSWORD