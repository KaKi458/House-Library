# Home Library

To run this application in the docker, run the following commands:

```docker
docker network create my_network
docker run --network my_network --name mysqldb -e MYSQL_ROOT_PASSWORD=root -d mysql
docker run --network my_network -e MYSQL_HOST=mysqldb -p 8080:8080 kacperillo/home-library
```

You can also clone the repository and run app locally, but first you need 
launched MySQL Server on the default port 3306.
If you don't have MySQL Server installed, you can build the docker image and start in container. 
To do that, go to the folder of the cloned repository, open terminal and run:

```docker
docker compose up
```

