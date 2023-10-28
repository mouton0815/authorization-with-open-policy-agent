#!/bin/bash

user=${1:-lars}
source "$(dirname "$0")"/get-access-token.sh $user

curl "http://localhost:8090/teams/2" \
     -H "Accept: application/json" \
     -H "Authorization: Bearer ${TOKEN}"