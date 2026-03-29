#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

usage() {
  cat <<'EOF'
Usage: ./effintrak-k8s.sh <command> [options]

Commands:
  up                 Apply core manifests (including Prometheus)
  down               Delete manifests managed by this folder
  nuke               Delete all effintrak-labeled resources
  status             Show deploy, pods, svc, ingress, pvc
  logs <component>   Stream logs: app | frontend | postgres | prometheus | all
  pf <target>        Port-forward: frontend | backend | postgres | prometheus
  help               Show this help

Examples:
  ./effintrak-k8s.sh up
  ./effintrak-k8s.sh status
  ./effintrak-k8s.sh logs app
  ./effintrak-k8s.sh pf prometheus
  ./effintrak-k8s.sh down
EOF
}

require_kubectl() {
  if ! command -v kubectl >/dev/null 2>&1; then
    echo "kubectl is required but not installed/in PATH."
    exit 1
  fi
}

cmd_up() {
  kubectl apply -f "$SCRIPT_DIR/configmap.yaml"
  kubectl apply -f "$SCRIPT_DIR/secret.yaml"
  kubectl apply -f "$SCRIPT_DIR/persistent-volume-claim.yaml"
  kubectl apply -f "$SCRIPT_DIR/deployment.yaml"
  kubectl apply -f "$SCRIPT_DIR/service.yaml"
  kubectl apply -f "$SCRIPT_DIR/frontend.yaml"
  kubectl apply -f "$SCRIPT_DIR/ingress.yaml"
  kubectl apply -f "$SCRIPT_DIR/prometheus.yaml"
  echo "Applied all k8s manifests."
}

cmd_down() {
  kubectl delete -f "$SCRIPT_DIR/prometheus.yaml" --ignore-not-found=true
  kubectl delete -f "$SCRIPT_DIR/ingress.yaml" --ignore-not-found=true
  kubectl delete -f "$SCRIPT_DIR/frontend.yaml" --ignore-not-found=true
  kubectl delete -f "$SCRIPT_DIR/service.yaml" --ignore-not-found=true
  kubectl delete -f "$SCRIPT_DIR/deployment.yaml" --ignore-not-found=true
  kubectl delete -f "$SCRIPT_DIR/persistent-volume-claim.yaml" --ignore-not-found=true
  kubectl delete -f "$SCRIPT_DIR/secret.yaml" --ignore-not-found=true
  kubectl delete -f "$SCRIPT_DIR/configmap.yaml" --ignore-not-found=true
  echo "Deleted manifests managed in k8s folder."
}

cmd_nuke() {
  kubectl delete all,pvc,configmap,secret,ingress -l app=effintrak --ignore-not-found=true
  echo "Deleted all resources with label app=effintrak."
}

cmd_status() {
  kubectl get deploy
  echo "---"
  kubectl get pods -o wide
  echo "---"
  kubectl get svc
  echo "---"
  kubectl get ingress
  echo "---"
  kubectl get pvc
}

cmd_logs() {
  local component="${1:-}"
  case "$component" in
    app)
      kubectl logs -f deployment/effintrak-app
      ;;
    frontend)
      kubectl logs -f deployment/effintrak-frontend
      ;;
    postgres)
      kubectl logs -f deployment/effintrak-postgres
      ;;
    prometheus)
      kubectl logs -f deployment/effintrak-prometheus
      ;;
    all)
      kubectl logs -f -l app=effintrak --all-containers=true
      ;;
    *)
      echo "Unknown component: $component"
      echo "Use: app | frontend | postgres | prometheus | all"
      exit 1
      ;;
  esac
}

cmd_pf() {
  local target="${1:-}"
  case "$target" in
    frontend)
      kubectl port-forward svc/effintrak-frontend-service 3000:80
      ;;
    backend)
      kubectl port-forward svc/effintrak-app-service 8080:80
      ;;
    postgres)
      kubectl port-forward svc/effintrak-postgres-service 5432:5432
      ;;
    prometheus)
      kubectl port-forward svc/effintrak-prometheus-service 9090:9090
      ;;
    *)
      echo "Unknown target: $target"
      echo "Use: frontend | backend | postgres | prometheus"
      exit 1
      ;;
  esac
}

main() {
  require_kubectl
  local command="${1:-help}"
  case "$command" in
    up)
      cmd_up
      ;;
    down)
      cmd_down
      ;;
    nuke)
      cmd_nuke
      ;;
    status)
      cmd_status
      ;;
    logs)
      shift || true
      cmd_logs "${1:-}"
      ;;
    pf)
      shift || true
      cmd_pf "${1:-}"
      ;;
    help|-h|--help)
      usage
      ;;
    *)
      echo "Unknown command: $command"
      usage
      exit 1
      ;;
  esac
}

main "$@"
