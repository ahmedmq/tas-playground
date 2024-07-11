# tas-playground

Sample app to deploy a Spring Boot application to Tanzu Application Service(TAS) and configure PostgresSQL and CredHub backing services

- Install `cf` cli

    ```bash
    brew install cloudfoundry/tap/cf-cli@8
    ```

- Target an organization and space.

    ```bash
    cf target -o dig -s demo
    ```
  
- Deploy the application using the given manifest

    ```bash
    cf push --no-start -f deploy/manifest.yml
    ```
  
- Check if apps has been deployed but NOT started

  ```bash
  cf apps
  ```

### Create and bind Postgres service
 
- See service offering in marketplace
  ```bash
  cf marketplace
  ```
  
- Create a postgres service instance

    ```bash
    cf create-service postgres micro-2gb playground-db
    ```

- Bind service instance to application

    ```bash
    cf bind-service tas-playground playground-db
    ```
  
- Check to see if service was created successfully
  
  ```bash
  cf services
  ```
  
### Create and bind CredHub Service

- Create a CredHub service instance and seed with initial configuration

    ```bash
    cf create-service credhub default my-secret -c '{"message":"TAS_SECRET_CREDENTIAL"}'
    ```

- Bind service instance to application, then startup the application

    ```bash
    cf bind-service tas-playground my-secret
    ```
    
- After verifying all services are started successfully, start the application
    ```bash
    cf start tas-playground
    ```
  
- Test the application

    ```bash
    curl -H 'Content-Type: application/json' -d '{ "title":"foo","url":"bar"}' -X POST http://localhost:8080/api/v1/bookmarks
    curl -H 'Content-Type: application/json' -d '{ "title":"baz","url":"foo"}' -X POST http://localhost:8080/api/v1/bookmarks
  
    curl -H 'Content-Type: application/json' http://localhost:8080/api/v1/bookmarks
  
    curl http://localhost:8080/api/v1/secrets/message
    ```
  
- Check the environment variables in actuator `http://localhost:8080/actuator/env` or alternatively use
  ```bash
  cf env tas-playground
  ```

- Update configuration in service instance and restart application

    ```bash
    cf update-service my-secret -c '{"message":"TAS_SECRET_UPDATED_CREDENTIAL"}'
    cf restart tas-playground
    ```

- Test the application

    ```bash
    curl http://localhost:8080/api/v1/secrets/message
    ```
  
### Other CLI references

- Tail logs / show latest logs
  ```bash
  cf logs tap-playground
  cf logs tap-playground --recent
  ```

- Unbind the service
  ```bash
  cf unbind-service tas-playground playground-db
  ```

- Delete the service
  ```bash
  cf delete-service playground-db
  ```
  
- Delete app
  ```bash
  cf delete -f deploy/manifest.yml
  ```