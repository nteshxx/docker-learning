# Responsible for creating backend service container
source .env.backend

docker run -d --name patient-service --network pms-network -p 4000:4000 patient-service:0.0.1
