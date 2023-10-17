package demo

import future.keywords.if
import future.keywords.in

default allow := false

# Members of a team have full access to the data of that team (and only that team)
allow if {
    input.method in {"GET", "PUT", "DELETE"}
    regex.match(`^/teams/\d+$`, input.path)
    trim_prefix(input.path, "/teams/") == input.teamId
}

# Users with "view" rights can view the data of all endpoints
allow if {
    input.method == "GET"
    regex.match(`^/teams(/\d+)?$`, input.path)
    "api-read" in input.roles
}

# Users with "full" rights can create, update, and delete data on all endpoints
allow if {
    input.method in {"GET", "POST", "PUT", "DELETE"}
    regex.match(`^/teams(/\d+)?$`, input.path)
    "api-full" in input.roles
}
