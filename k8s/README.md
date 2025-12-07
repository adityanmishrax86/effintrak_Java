# Kubernetes Deployment Files

This directory contains Kubernetes manifests for deploying EffinTrak to a Kubernetes cluster.

## Files Overview

- `deployment.yaml` - Application and PostgreSQL deployments
- `service.yaml` - Services for application and database
- `configmap.yaml` - Configuration data
- `secret.yaml` - Secret data (passwords, tokens)
- `persistent-volume-claim.yaml` - Storage for PostgreSQL
- `ingress.yaml` - Ingress configuration for external access

## Quick Start

1. **Update secrets** in `secret.yaml` with your production values
2. **Update image** in `deployment.yaml` to point to your container registry
3. **Apply all resources**:
   ```bash
   kubectl apply -f .
   ```

## Deployment Order

1. ConfigMap and Secrets
2. Persistent Volume Claim
3. Deployments
4. Services
5. Ingress (optional)

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

