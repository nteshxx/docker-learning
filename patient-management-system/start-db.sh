# Responsible for creating postgres database
source .env.db

# RUN VOLUME AND NETWORK SETUP
source setup.sh

# CHECK FOR EXISTING CONTAINER ALREADY RUNNING
if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "A container with name $CONTAINER_NAME already exists."
    echo "To kill the container, run: docker kill $CONTAINER_NAME"
    exit 1
fi

# DOCKER RUN COMMAND
docker run --rm -d --name $CONTAINER_NAME \
    -e POSTGRES_DB=$POSTGRES_DB \
    -e POSTGRES_USER=$POSTGRES_USER \
    -e POSTGRES_PASSWORD=$POSTGRES_PASSWORD \
    -e POSTGRES_INITDB_ARGS="--auth-local=md5 --auth-host=md5" \
    -p $LOCALHOST_PORT:$CONTAINER_PORT \
    -v $VOLUME_NAME:$VOLUME_CONTAINER_PATH \
    --network $NETWORK_NAME \
    $POSTGRES_IMAGE:$POSTGRES_TAG

# EXIT WITH STATUS CODE 0
echo "Postgres database setup complete"
exit 0

# TEST USING ANOTHER CONTAINER
#docker run --rm --name debugpsqlsh -e PGPASSWORD=pms-password -it --network pms-network postgres:17.5 psql postgresql://pms-user@postgresDB:5432/pms-db
#docker run --rm --name debugpsqlsh -it --network pms-network postgres:17.5 psql postgresql://pms-user:pms-password@postgresDB:5432/pms-db
