package demo

import future.keywords.if
import future.keywords.in

default allow := false

# Employees of a company have full access to the data of that company (and only that company)
allow if {
    input.method in {"GET", "PUT", "DELETE"}
    input.path == ["companies", input.company_id]
}

# Users with "view" rights can view the data of all endpoints
allow if {
    input.method == "GET"
    "api-read" in input.roles
}

# Users with "full" rights can create, update, and delete data on all endpoints
allow if {
    input.method in {"POST", "PUT", "DELETE"}
    "api-full" in input.roles
}
