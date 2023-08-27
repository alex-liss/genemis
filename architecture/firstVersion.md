# Release 0.0.1-alpha

The first release is aimed to make a simple integration between frontend and database via server-side dispatcher.
The integration has a form of database stored predefined greeting "Welcome to Genemis" which is displayed on the simple UI.


```mermaid
sequenceDiagram
    Client->>+Server: Get Greeting
    Server->>+Database: Get Greeting
    Database->>+Server: Return Greeting
    Server ->>+Client: Return Greeting

```



```mermaid
flowchart LR

id1(Client)
id2(Dispatcher)
id3[(Database)]
id1 --> id2 -->id3
```