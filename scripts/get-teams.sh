#!/bin/bash

user=${1:-inge}
source "$(dirname "$0")"/get-access-token.sh $user

curl "http://localhost:8090/teams" \
     -H "Accept: application/json" \
     -H "Authorization: Bearer ${TOKEN}"