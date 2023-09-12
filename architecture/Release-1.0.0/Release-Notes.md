# Release 1.0.0

The first "real" release is built on the base of alpha-release. In this release then main feature is
the "real" time series data shown in a chart.

### Architecture

The overall architecture remains the same. It consists of frontend, fetching data from the database via 
backend dispatcher. The data in this case is not a simple message anymore, but time series.
However, the time series data is still "hardcoded": it is loaded into database on DB creation.

### Frontend

A JS chart library is used to show the time series data. The UI remains
as simple as possible. This means no labels, tooltips, etc. Only simple chart with a legend.
Some small changes in overall look and feel are applied, e.g.
favicon, tab title, license info, release info, etc.

### Internal Technical Enhancements

#### QA
A new role is introduced in our team, the QA. Our BE as well as FE code is 
enhanced with unit, integration, contract and e2e tests.

#### Containerization
The frontend and backend components are packed into docker containers.

#### CI/CD
The Jenkins Server is used to run pipeline jobs to build the components,
run the tests and build the docker containers out of them. Only manual triggers
from main branch.

### License and Terms of Use
The product is licensed under free license. The terms of use are defined in the license file.



