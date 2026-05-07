# Kubernetes Deployment Manifests

This directory contains Kubernetes deployment manifests for the Student Management System.

## Components

### 1. PostgreSQL Database (`postgres-deployment.yaml`)
- **Image**: postgres:15-alpine
- **Service Name**: postgres-service
- **Port**: 5432 (ClusterIP)
- **Environment Variables**:
  - POSTGRES_DB: studentdb
  - POSTGRES_USER: student_user
  - POSTGRES_PASSWORD: student_pass
- **Resources**:
  - Memory Limit: 512Mi
  - CPU Limit: 500m

### 2. Student Service (`etudiant-deployment.yaml`)
- **Image**: amal878/etudiant-service:1.0
- **Service Name**: etudiant-service
- **Port**: 8080 (NodePort 30080)
- **Environment Variables**:
  - Database connection to postgres-service
  - Redis connection to redis-service
- **Resources**:
  - Memory Limit: 512Mi
  - CPU Limit: 500m
- **Health Checks**:
  - Readiness Probe: HTTP GET /api/etudiants
  - Liveness Probe: HTTP GET /api/etudiants

### 3. Redis Cache (`redis-deployment.yaml`)
- **Image**: redis:7-alpine
- **Service Name**: redis-service
- **Port**: 6379 (ClusterIP)
- **Resources**:
  - Memory Limit: 256Mi
  - CPU Limit: 250m

## Deployment Instructions

### Prerequisites
- Kubernetes cluster (K3S, Minikube, or any K8s cluster)
- kubectl configured to access your cluster

### Deploy All Services

1. **Deploy PostgreSQL**:
   ```bash
   kubectl apply -f postgres-deployment.yaml
   ```

2. **Deploy Redis**:
   ```bash
   kubectl apply -f redis-deployment.yaml
   ```

3. **Deploy Student Service**:
   ```bash
   kubectl apply -f etudiant-deployment.yaml
   ```

### Deploy All at Once
```bash
kubectl apply -f .
```

### Verify Deployments
```bash
# Check all pods
kubectl get pods

# Check all services
kubectl get services

# Check deployment status
kubectl get deployments
```

### Access the Application

The Student Service is exposed via NodePort on port 30080:

```bash
# Get the node IP
kubectl get nodes -o wide

# Access the application
curl http://<NODE_IP>:30080/api/etudiants

# Or access Swagger UI
http://<NODE_IP>:30080/swagger-ui.html
```

For Minikube:
```bash
minikube service etudiant-service --url
```

### View Logs
```bash
# Student Service logs
kubectl logs -f deployment/etudiant-service

# PostgreSQL logs
kubectl logs -f deployment/postgres

# Redis logs
kubectl logs -f deployment/redis
```

### Cleanup
```bash
# Delete all resources
kubectl delete -f .

# Or delete individually
kubectl delete -f etudiant-deployment.yaml
kubectl delete -f postgres-deployment.yaml
kubectl delete -f redis-deployment.yaml
```

## Notes

- **No Persistent Volumes**: These manifests do not include PersistentVolumeClaims. Data will be lost when pods are deleted.
- **Credentials**: The database and Redis credentials are hardcoded for development purposes. For production, use Kubernetes Secrets.
- **Resource Limits**: Adjust resource limits based on your cluster capacity and workload requirements.
- **Health Checks**: The readiness and liveness probes ensure the service is healthy before receiving traffic.

## Production Considerations

For production deployments, consider:

1. **Secrets Management**: Use Kubernetes Secrets for sensitive data
2. **Persistent Storage**: Add PersistentVolumeClaims for PostgreSQL
3. **High Availability**: Increase replica counts
4. **Ingress**: Use Ingress instead of NodePort for external access
5. **Resource Quotas**: Set appropriate resource requests and limits
6. **Monitoring**: Add Prometheus metrics and logging
7. **Network Policies**: Implement network policies for security
