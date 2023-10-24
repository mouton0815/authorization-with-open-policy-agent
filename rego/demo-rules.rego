package demo

import future.keywords.if
import future.keywords.in

# The default value is used when all of the rules sharing the same name are undefined.
default allow := false

# Members of a team have read and write access to the data of that team (and only that team)
allow if {
    input.method in {"GET", "PUT"}
    trim_prefix(input.path, "/teams/") == input.teamId
}

# Users with "api-read" rights can read the data of all endpoints with prefix "/teams"
allow if {
    input.method == "GET"
    startswith(input.path, "/teams")
    "api-read" in input.roles
}

# Users with "api-full" rights have full access to all endpoints with prefix "/teams"
allow if {
    startswith(input.path, "/teams")
    "api-full" in input.roles
}
