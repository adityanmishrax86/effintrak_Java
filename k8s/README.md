# Kubernetes Deployment Files

This directory contains Kubernetes manifests for deploying EffinTrak to a Kubernetes cluster.

## Files Overview

- `deployment.yaml` - Application and PostgreSQL deployments
- `service.yaml` - Services for application and database
- `frontend.yaml` - Frontend deployment and service
- `configmap.yaml` - Configuration data
- `secret.yaml` - Secret data (passwords, tokens)
- `persistent-volume-claim.yaml` - Storage for PostgreSQL
- `ingress.yaml` - Ingress configuration for external access
- `prometheus.yaml` - Prometheus deployment/service/config for scraping app metrics
- `optional/servicemonitor.yaml` - Prometheus Operator scrape definition (optional)

## Quick Start

1. **Update secrets** in `secret.yaml` with your production values (`db-password`, `jwt-secret`, `nvidia-api-key`)
2. **Update image** in `deployment.yaml` to point to your container registry
3. **Apply all resources**:
   ```bash
   kubectl apply -f .
   ```

## Helper Script

Use the helper script for one-command operations:

```bash
cd k8s
chmod +x effintrak-k8s.sh
./effintrak-k8s.sh help
```

Common commands:

```bash
./effintrak-k8s.sh up
./effintrak-k8s.sh status
./effintrak-k8s.sh logs app
./effintrak-k8s.sh pf frontend
./effintrak-k8s.sh pf prometheus
./effintrak-k8s.sh down
./effintrak-k8s.sh nuke
```

## Deployment Order

1. ConfigMap and Secrets
2. Persistent Volume Claim
3. Deployments
4. Services
5. Ingress (optional)
6. Frontend deployment/service

## Frontend Routing

The frontend is exposed on the same host under `/app` via `ingress.yaml`.

- Frontend URL: `https://api.effintrak.com/app`
- Backend API URL: `https://api.effintrak.com/api/*`

Apply frontend resources:

```bash
kubectl apply -f k8s/frontend.yaml
kubectl apply -f k8s/ingress.yaml
```

## Customization

### Update Image

Edit `deployment.yaml`:
```yaml
image: your-registry/effintrak:tag
```

### Scale Replicas

Edit `deployment.yaml`:
```yaml
spec:
  replicas: 3  # Change this number
```

### Resource Limits

Edit `deployment.yaml` under `resources` section:
```yaml
resources:
  requests:
    memory: "1Gi"
    cpu: "1000m"
  limits:
    memory: "4Gi"
    cpu: "4000m"
```

### Storage Size

Edit `persistent-volume-claim.yaml`:
```yaml
resources:
  requests:
    storage: 50Gi  # Change size
```
