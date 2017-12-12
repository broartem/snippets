# Docker shell snippets
Run docker container in detached mode and publish container's port 8080 to the same port on the host (then http://localhost:8080 will work):
```
docker run -p 8080:8080 -d <image_id>
```
Attach to a detached container with bash shell (see http://stackoverflow.com/questions/23752581):
```
docker exec -it <container_name> bash
```
Get container IP ([9 more ways](http://networkstatic.net/10-examples-of-how-to-get-docker-container-ip-address/)):
```
docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' CONTAINER_ID
```
