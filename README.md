```shell
https://github.com/eugenp/tutorials/tree/master/spring-security-modules/spring-security-opa
```

# Evaluation
```shell
./opa eval -i demo-input.json -d demo-rules.rego data.demo.allow
```

# Run Server
```shell
./opa run --server ./demo-rules.rego
```
or
```shell
docker run --platform linux/amd64 --rm -p 8181:8181 -v ${PWD}/rego:/rego openpolicyagent/opa run --server --addr :8181 /rego/demo-rules.rego
```

# API Requests
```shell
curl -H 'Content-Type: application/json' localhost:8181/v1/data/demo/allow -d @demo-body.json
```