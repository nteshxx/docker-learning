# Responsible for cleaning up postgres databse
source .env.db

# DELETE CONTAINER
if [ "$(docker ps -aq -f name=$CONTAINER_NAME)" ]; then
    echo "Removing database container with name $CONTAINER_NAME."
    docker kill $CONTAINER_NAME
else
    echo "A container with name $CONTAINER_NAME does not exists."
fi

# DELETE NETWORK
if [ "$(docker network ls -q -f name=$NETWORK_NAME)" ]; then
    echo "Removing network $NETWORK_NAME."
    docker network rm $NETWORK_NAME
else
    echo "A network with the name $NETWORK_NAME does not exists. Skipping network deletion."
fi

# DELETE VOLUME
if [ "$(docker volume ls -q -f name=$VOLUME_NAME)" ]; then
    echo "Removing volume $VOLUME_NAME."
    docker volume rm $VOLUME_NAME
else
    echo "A volume with the name $VOLUME_NAME does not exists. Skipping volume deletion."
fi

echo "Clean complete."
exit 0;
