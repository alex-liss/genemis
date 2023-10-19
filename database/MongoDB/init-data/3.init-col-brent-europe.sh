#!/bin/bash

mongoimport --db=genemis --collection=ts_brent_europe \
--type=csv \
--columnsHaveTypes \
--fields="date.date(2006-01-02),price.double()" \
--file=/docker-entrypoint-initdb.d/Brent_Europe.csv \
--username=$GENEMIS_DB_USERNAME --password=$GENEMIS_DB_PASSWORD