package demo

import future.keywords.if
import future.keywords.in

# The default value is used when all rules with the same name are undefined
default allow := false

# Members of a team have read+write access to the data of (only) that team
# Function trim_prefix returns the suffix of input.path that follows "/teams/"
allow if {
    input.method in {"GET", "PUT"}
    trim_prefix(input.path, "/teams/") == input.teamId
}

# Users with "api-read" rights can read the data of all endpoints
allow if {
    input.method == "GET"
    "api-read" in input.roles
}

# Users with "api-full" rights have full access to all endpoints
allow if {
    "api-full" in input.roles
}
