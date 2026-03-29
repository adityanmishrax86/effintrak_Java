# effinTrak Kubernetes Deployment Troubleshooting Notes

Date: 2026-03-29
Environment: Docker Desktop Kubernetes (macOS)

## Symptoms Observed

1. `kubectl apply -f .` failed with YAML parsing error in `configmap.yaml`.
2. `kubectl apply -f .` failed on `ServiceMonitor` because CRD was not installed.
3. Ingress apply printed deprecation warning for `kubernetes.io/ingress.class`.
4. Runtime state after apply:
   - Postgres pod `Pending`
   - Backend pods `CrashLoopBackOff`
   - Frontend pods restarting with failed probes

## Root Causes

1. Invalid YAML block scalar indentation in `k8s/configmap.yaml` under `schema.sql`.
2. `k8s/servicemonitor.yaml` required `monitoring.coreos.com/v1` CRD (`ServiceMonitor`) not present in cluster.
3. Ingress used deprecated annotation instead of `spec.ingressClassName`.
4. PVC requested `storageClassName: standard`, but cluster only had `hostpath`.
5. Frontend probes targeted `/app`, returning `404` in current container runtime setup.
6. Backend startup depended on DB availability and crashed during early boot when DB service was not yet reachable.
7. Backend later failed schema validation (`serial` vs expected `bigint`) with `jpa.ddl-auto=validate`.
8. Postgres probe shell command used wrong variable style (`$(POSTGRES_USER)`), causing probe auth errors (`role "-d" does not exist`).

## Fixes Applied

1. Fixed SQL block indentation in `k8s/configmap.yaml` so manifest parses correctly.
2. Moved `ServiceMonitor` to optional path:
   - From: `k8s/servicemonitor.yaml`
   - To: `k8s/optional/servicemonitor.yaml`
   - Default `kubectl apply -f k8s` now works without Prometheus Operator CRDs.
3. Updated ingress class configuration in `k8s/ingress.yaml`:
   - Removed deprecated annotation `kubernetes.io/ingress.class`
   - Added `spec.ingressClassName: nginx`
4. Updated PVC storage class in `k8s/persistent-volume-claim.yaml`:
   - `storageClassName: hostpath`
5. Recreated unbound claim so new storage class took effect:
   - Deleted and re-applied `postgres-pvc`.
6. Added backend `initContainer` in `k8s/deployment.yaml`:
   - `wait-for-postgres` using `pg_isready` against `effintrak-postgres-service:5432`
   - Prevents backend app container from starting before DB is reachable.
7. Fixed Postgres liveness/readiness probe command variable expansion in `k8s/deployment.yaml`:
   - From `$(POSTGRES_USER)` and `$(POSTGRES_DB)`
   - To `$POSTGRES_USER` and `$POSTGRES_DB`
8. Updated frontend probes in `k8s/frontend.yaml`:
   - Readiness/Liveness path changed from `/app` to `/`
9. Updated JPA mode in `k8s/configmap.yaml`:
   - `jpa.ddl-auto: "validate"` -> `"update"`
   - This allowed backend startup despite existing SQL/JPA type mismatch.

## Verification Performed

1. `kubectl apply --dry-run=client -f .` succeeded.
2. `kubectl apply -f .` succeeded after manifest fixes.
3. Rollouts completed:
   - `deployment/effintrak-postgres` available
   - `deployment/effintrak-app` available
   - `deployment/effintrak-frontend` available
4. Final pod status converged to Running/Ready for active replicas.
5. Backend logs confirmed successful Spring Boot startup and actuator/web server initialization.

## Important Follow-up (Production Hygiene)

1. Current workaround uses `jpa.ddl-auto=update` for runtime compatibility.
2. For stricter production safety, align DB schema types with JPA model types (or vice versa), then switch back to `jpa.ddl-auto=validate`.
3. Keep `k8s/optional/servicemonitor.yaml` optional unless Prometheus Operator CRDs are installed.
