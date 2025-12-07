# Docker & Kubernetes Deployment Guide

This guide covers deploying the EffinTrak backend application using Docker and Kubernetes.

## Table of Contents

1. [Docker Deployment](#docker-deployment)
2. [Docker Compose](#docker-compose)
3. [Kubernetes Deployment](#kubernetes-deployment)
4. [Production Considerations](#production-considerations)

---

## Docker Deployment

### Prerequisites

- Docker installed (version 20.10+)
- Docker Compose installed (version 2.0+)
- At least 4GB of available RAM
- PostgreSQL database (or use docker-compose)

### Building the Docker Image

#### Option 1: Build from Dockerfile

```bash
# Build the image
docker build -t effintrak:latest .

# Tag for registry (if pushing to a registry)
docker tag effintrak:latest your-registry/effintrak:latest

# Push to registry (optional)
docker push your-registry/effintrak:latest
```

#### Option 2: Build with Maven first (for faster rebuilds)

```bash
# Build JAR locally
./mvnw clean package -DskipTests

# Build Docker image (will use cached layers)
docker build -t effintrak:latest .
```

### Running the Container

#### Basic Run

```bash
docker run -d \
  --name effintrak-app \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/effintrak \
  -e SPRING_DATASOURCE_USERNAME=your_username \
  -e SPRING_DATASOURCE_PASSWORD=your_password \
  effintrak:latest
```

#### With Environment File

Create a `.env` file:

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/effintrak
SPRING_DATASOURCE_USERNAME=effintrak_user
SPRING_DATASOURCE_PASSWORD=your_secure_password
SPRING_JPA_HIBERNATE_DDL_AUTO=update
```

Run with:

```bash
docker run -d \
  --name effintrak-app \
  -p 8080:8080 \
  --env-file .env \
  effintrak:latest
```

### Health Check

```bash
# Check container status
docker ps

# Check logs
docker logs effintrak-app

# Check health endpoint (if actuator is enabled)
curl http://localhost:8080/actuator/health
```

---

## Docker Compose

### Development Setup

For local development with PostgreSQL:

```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f app

# Stop all services
docker-compose down

# Stop and remove volumes
docker-compose down -v
```

### Production Setup

```bash
# Create .env file with production values
cat > .env << EOF
POSTGRES_DB=effintrak
POSTGRES_USER=effintrak_user
POSTGRES_PASSWORD=your_secure_password_here
POSTGRES_PORT=5432
APP_PORT=8080
SPRING_JPA_DDL_AUTO=validate
LOG_LEVEL=INFO
EOF

# Start with production compose file
docker-compose -f docker-compose.prod.yml up -d

# View logs
docker-compose -f docker-compose.prod.yml logs -f
```

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `POSTGRES_DB` | Database name | `effintrak` |
| `POSTGRES_USER` | Database user | `effintrak_user` |
| `POSTGRES_PASSWORD` | Database password | Required |
| `POSTGRES_PORT` | Database port | `5432` |
| `APP_PORT` | Application port | `8080` |
| `SPRING_JPA_DDL_AUTO` | JPA DDL mode | `validate` (prod) / `update` (dev) |
| `LOG_LEVEL` | Logging level | `INFO` |

---

## Kubernetes Deployment

### Prerequisites

- Kubernetes cluster (1.20+)
- `kubectl` configured
- Access to container registry (Docker Hub, GCR, ECR, etc.)

### Step 1: Build and Push Image

```bash
# Build image
docker build -t your-registry/effintrak:latest .

# Push to registry
docker push your-registry/effintrak:latest
```

### Step 2: Update Image in Deployment

Edit `k8s/deployment.yaml` and update the image:

```yaml
image: your-registry/effintrak:latest
```

### Step 3: Create Secrets

```bash
# Create secrets
kubectl create secret generic effintrak-secrets \
  --from-literal=db-username=effintrak_user \
  --from-literal=db-password=your_secure_password \
  --from-literal=jwt-secret=your_jwt_secret

# Or use the secret.yaml file (update values first!)
kubectl apply -f k8s/secret.yaml
```

### Step 4: Create ConfigMap

```bash
kubectl apply -f k8s/configmap.yaml
```

### Step 5: Create Persistent Volume Claim

```bash
kubectl apply -f k8s/persistent-volume-claim.yaml
```

### Step 6: Deploy Application

```bash
# Deploy PostgreSQL and Application
kubectl apply -f k8s/deployment.yaml

# Create Services
kubectl apply -f k8s/service.yaml

# Create Ingress (optional, for external access)
kubectl apply -f k8s/ingress.yaml
```

### Step 7: Verify Deployment

```bash
# Check pods
kubectl get pods

# Check services
kubectl get services

# Check logs
kubectl logs -f deployment/effintrak-app

# Port forward for local testing
kubectl port-forward service/effintrak-app-service 8080:80
```

### Updating the Application

```bash
# Build and push new image
docker build -t your-registry/effintrak:v1.1.0 .
docker push your-registry/effintrak:v1.1.0

# Update deployment
kubectl set image deployment/effintrak-app app=your-registry/effintrak:v1.1.0

# Or update the deployment.yaml and reapply
kubectl apply -f k8s/deployment.yaml

# Check rollout status
kubectl rollout status deployment/effintrak-app
```

### Scaling

```bash
# Scale application
kubectl scale deployment effintrak-app --replicas=3

# Or update replicas in deployment.yaml
kubectl apply -f k8s/deployment.yaml
```

---

## Production Considerations

### Security

1. **Secrets Management**
   - Never commit secrets to Git
   - Use Kubernetes Secrets or external secret management (HashiCorp Vault, AWS Secrets Manager)
   - Rotate passwords regularly

2. **Network Security**
   - Use NetworkPolicies to restrict pod-to-pod communication
   - Enable TLS/SSL for all external traffic
   - Use Ingress with TLS termination

3. **Container Security**
   - Run containers as non-root user (already configured)
   - Regularly update base images
   - Scan images for vulnerabilities

### Performance

1. **Resource Limits**
   - Adjust CPU and memory limits based on load
   - Monitor resource usage with Prometheus/Grafana
   - Use Horizontal Pod Autoscaler (HPA) for auto-scaling

2. **Database**
   - Use connection pooling
   - Consider read replicas for high read loads
   - Regular backups

3. **Caching**
   - Enable Redis for session management
   - Cache frequently accessed data

### Monitoring

1. **Health Checks**
   - Liveness and readiness probes are configured
   - Monitor `/actuator/health` endpoint

2. **Logging**
   - Centralize logs with ELK stack or similar
   - Use structured logging (JSON format)

3. **Metrics**
   - Enable Spring Boot Actuator metrics
   - Integrate with Prometheus

### Backup & Recovery

1. **Database Backups**
   ```bash
   # Backup PostgreSQL
   kubectl exec effintrak-postgres-xxx -- pg_dump -U effintrak_user effintrak > backup.sql
   
   # Restore
   kubectl exec -i effintrak-postgres-xxx -- psql -U effintrak_user effintrak < backup.sql
   ```

2. **Persistent Volume Backups**
   - Regular snapshots of PVC
   - Off-site backup storage

### High Availability

1. **Multi-Region Deployment**
   - Deploy to multiple availability zones
   - Use database replication

2. **Load Balancing**
   - Use Kubernetes Service with multiple replicas
   - Configure session affinity if needed

### Example HPA (Horizontal Pod Autoscaler)

```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: effintrak-hpa
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: effintrak-app
  minReplicas: 2
  maxReplicas: 10
  metrics:
  - type: Resource
    resource:
      name: cpu
      target:
        type: Utilization
        averageUtilization: 70
  - type: Resource
    resource:
      name: memory
      target:
        type: Utilization
        averageUtilization: 80
```

Apply with:
```bash
kubectl apply -f k8s/hpa.yaml
```

---

## Troubleshooting

### Container Won't Start

```bash
# Check logs
docker logs effintrak-app
# or
kubectl logs deployment/effintrak-app

# Check container status
docker ps -a
# or
kubectl get pods
```

### Database Connection Issues

```bash
# Test database connectivity
kubectl exec -it effintrak-app-xxx -- wget -O- http://effintrak-postgres-service:5432

# Check database logs
kubectl logs deployment/effintrak-postgres
```

### Out of Memory

```bash
# Check resource usage
kubectl top pods

# Increase memory limits in deployment.yaml
```

### Image Pull Errors

```bash
# Check image pull secrets
kubectl get secrets

# Create image pull secret if using private registry
kubectl create secret docker-registry regcred \
  --docker-server=your-registry.com \
  --docker-username=your-username \
  --docker-password=your-password
```

---

## Quick Reference

### Docker Commands

```bash
# Build
docker build -t effintrak:latest .

# Run
docker run -d -p 8080:8080 effintrak:latest

# Stop
docker stop effintrak-app

# Remove
docker rm effintrak-app

# View logs
docker logs -f effintrak-app
```

### Kubernetes Commands

```bash
# Apply all resources
kubectl apply -f k8s/

# Delete all resources
kubectl delete -f k8s/

# Get status
kubectl get all -l app=effintrak

# Describe resource
kubectl describe deployment effintrak-app

# Execute command in pod
kubectl exec -it effintrak-app-xxx -- /bin/sh
```

---

## CI/CD Integration

### GitHub Actions Example

```yaml
name: Build and Deploy

on:
  push:
    branches: [main]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Build Docker image
        run: docker build -t effintrak:${{ github.sha }} .
      - name: Push to registry
        run: |
          echo "${{ secrets.DOCKER_PASSWORD }}" | docker login -u "${{ secrets.DOCKER_USERNAME }}" --password-stdin
          docker push effintrak:${{ github.sha }}
      - name: Deploy to Kubernetes
        run: |
          kubectl set image deployment/effintrak-app app=effintrak:${{ github.sha }}
```

---

## Support

For issues or questions:
1. Check application logs
2. Review Kubernetes events: `kubectl get events`
3. Check resource quotas: `kubectl describe quota`

