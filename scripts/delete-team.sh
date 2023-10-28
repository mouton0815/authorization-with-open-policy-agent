#!/bin/bash

user=${1:-inge}
source "$(dirname "$0")"/get-access-token.sh $user

curl -X DELETE "http://localhost:8090/teams/2" \
     -H "Authorization: Bearer ${TOKEN}"