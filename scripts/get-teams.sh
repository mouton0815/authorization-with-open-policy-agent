#!/bin/bash

user=${1:-inge}
source get-access-token.sh $user

curl "http://localhost:8090/teams" \
     -H "Accept: application/json" \
     -H "Authorization: Bearer ${TOKEN}"