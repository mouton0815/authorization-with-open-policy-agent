#!/bin/bash

user=${1:-fred}
source get-access-token.sh $user

curl -X POST "http://localhost:8090/teams" \
     -H "Accept: application/json" \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer ${TOKEN}" \
     -d '{"name":"Team D", "city": "Madrid"}'