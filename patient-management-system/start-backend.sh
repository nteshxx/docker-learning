# Responsible for creating backend service container
source .env.backend

# CHECK FOR EXISTING CONTAINER ALREADY RUNNING
if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "A container with name $CONTAINER_NAME already exists."
    echo "To kill the container, run: docker kill $CONTAINER_NAME"
    exit 1
fi

# BUILD DOCKER IMAGE
docker build -t $IMAGE_NAME:$IMAGE_TAG \
    -f $DOCKERFILE $BUILD_CONTEXT

# BUILD DOCKER CONTAINER
docker run --rm -d --name $CONTAINER_NAME \
    -e SERVER_PORT=$SERVER_PORT \
    -e LOG_LEVEL=$LOG_LEVEL \
    -e DATABASE_URL=$DATABASE_URL \
    -e DATABASE_USER=$DATABASE_USER \
    -e DATABASE_PASSWORD=$DATABASE_PASSWORD \
    -p $LOCALHOST_PORT:$SERVER_PORT \
    --network $NETWORK_NAME \
    $IMAGE_NAME:$IMAGE_TAG

# EXIT WITH STATUS CODE 0
echo "Patient service setup complete"
exit 0
