# House-Library

To run this appication in the docker, run the following commands:

```docker
docker create network my_network
docker run --network my_network -e MYSQL_ROOT_PASSWORD=root -d mysql
docker run --network my_network -p 8080:8080 kaki458/house-library
```
