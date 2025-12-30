# Anomaly Detector

## Overview
A Java Spring Boot application for detecting anomalies from GitHub Webhook events.

## Run The Application

### Using Docker
1. Build the Docker image:
```bash
docker build -t anomaly-detector .
```
2. Run the Docker container:
```bash
docker run -p 8080:8080 anomaly-detector
```

 ### Connecting GitHub Webhooks <br /> 
1. Run ngrok to expose your local container to the internet:
```bash
ngrok http 127.0.0.1:8080
```
2. Copy the public ngrok URL and add it in GitHub → Settings → Webhooks → Add webhook.
Make sure to append /webhook to the URL, for example
```https://your-ngrok-id.ngrok.io/webhook```

3. Set the Content type to `application/json`.   
4. Select individual events or choose "Send me everything" based on your requirements.
5. Click "Add webhook" to save.

Your application should now receive GitHub events and detect anomalies.

### Optional:
**Using the Provided Docker Image**

Instead of building the image yourself, you can use the included anomaly-detector.tar file. <br />
Load it into Docker with:
```bash
docker load -i anomaly-detector.tar
```
Then run the container as usual:
```bash
docker run -p 8080:8080 anomaly-detector:latest
```