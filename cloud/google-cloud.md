# Google Cloud SDK command line snippets
## Compute Engine
List static addresses:
```
gcloud compute addresses list
```

## Containers
Some prerequisites:
* [Google Cloud SDK download page](https://cloud.google.com/sdk/)
* [Quickstarts for various platforms](https://cloud.google.com/sdk/docs/quickstarts)

Prepare custom Docker image for push to [Container Registry](https://cloud.google.com/container-registry/) in Europe zone. Don't mix up project id with project name!
```
docker tag <image_id> eu.gcr.io/<project-id>/<custom_image_name>
```
And then push the image to the registry:
```
gcloud docker -- push eu.gcr.io/<project-id>/<custom_image_name>
```

On running containers with Google Container Engine read [Quickstart for Google Container Engine](https://cloud.google.com/container-engine/docs/quickstart). It is important to set up kubectl after creating a cluster with the following command (see http://stackoverflow.com/questions/36845569):
```
gcloud config set project PROJECT
gcloud config set compute/zone ZONE
gcloud config set container/cluster CLUSTER_NAME
gcloud container clusters get-credentials CUSTER_NAME
```
