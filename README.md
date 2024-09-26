Dockerized Application

## Prerequisites

- [Docker](https://docs.docker.com/get-docker/) installed.

## Build and Run

### 1. Build the Docker Image
```bash
docker build -t my-app .
```

### 2. Run the Docker Container
```bash
docker run -p 8080:8080 my-app
```

### 3. Access the Application
Open your browser and go to:
```
http://localhost:8080
```

## Troubleshooting

- Check logs if the container fails:
  ```bash
  docker logs <container_id>
  ```

## Cleanup
Stop and remove the container:
```bash
docker stop <container_id>
docker rm <container_id>
```
