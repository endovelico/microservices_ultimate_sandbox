#!/bin/bash
# File: rebuild-all.sh
# Place in the project root (next to docker-compose.yml)

set -e  # exit if any command fails

echo "==============================="
echo "🛠  Rebuilding all Spring Boot services..."
echo "==============================="

# List of microservices
SERVICES=("discovery-server" "nfl-api-gateway" "nfl-team-service")

for service in "${SERVICES[@]}"; do
    echo "Building $service..."
    cd $service
    ./mvnw clean package -DskipTests
    cd ..
done

echo "==============================="
echo "🐳 Rebuilding Docker images..."
echo "==============================="

docker-compose build

echo "==============================="
echo "🚀 Restarting Docker Compose containers..."
echo "==============================="

docker-compose up -d

echo "==============================="
echo "☸️  Restarting Kubernetes deployments..."
echo "==============================="

# Replace <namespace> with your k8s namespace, or omit -n <namespace> for default
NAMESPACE="default"

for service in "${SERVICES[@]}"; do
    echo "Restarting deployment $service in namespace $NAMESPACE..."
    kubectl rollout restart deployment $service -n $NAMESPACE || echo "$service not deployed in k8s, skipping..."
done

echo "==============================="
echo "✅ All services rebuilt and restarted!"
echo "==============================="
