#!/bin/bash

user=${1:-inge}

export TOKEN=$(
curl -s -X POST "http://localhost:8080/realms/demo/protocol/openid-connect/token" \
     -H "Content-Type: application/x-www-form-urlencoded" \
     -d "username=${user}" \
     -d "password=${user}" \
     -d "grant_type=password" \
     -d "client_id=demo-client" \
     -d "client_secret=jzWiYwTEDNni0qYqpyp7VjezKeFzVdWg" \
| grep -oE '"access_token":"[^"]+"' \
| awk -F'"' '{print $4}'
)

#echo "=== ACCESS TOKEN: ${TOKEN}"