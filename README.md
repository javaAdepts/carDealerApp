
# **To run the Project**

## Step 1: 

### **Build the Docker image** 

`docker build -t car-dealer-app .    `

## Step 2:

### **Build the docker container using the docker compose file** 

#### For Dev env :
`docker-compose -f compose-dev.yml up --build`

[http://localhost:8484/api/register]()

#### For QA env :
`docker-compose -f compose-qa.yml up --build`

[http://localhost:8585/api/register]()

#### For Prod env :
`docker-compose -f compose-prod.yml up --build`

[http://localhost:8686/api/register]()