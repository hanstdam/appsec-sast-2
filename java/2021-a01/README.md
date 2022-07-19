# OWASP Top 10, 2021, A01, Broken Access Control

This application is a Java Spring application, which contains a number of
OWASP Top 10, 2021, A01, Broken Access Control vulnerabilities.

Specifically
* Insecure ID
* Path Traversal/File Permission

## Insecure ID
The insecure id vulnerability is demonstrable in the `/profile` endpoint
which takes a mandatory `id` query string parameter of the type `integer`.

The `id` parameter corresponds to a user id. There is no restrictions
regarding which ids can be queried.

## Path Traversal/File Permission
The path traversal vulnerability is demonstrable in the `/profile-image`
endpoint. This endpoint takes a `fileName` parameter. Currently the
application is shipped with a profile image called `profile.jpg` which
can be used as the parameter i.e. `/profile-image?fileName=profile.jpg`.

To demonstrate the vulnerability, call
`/profile-image?fileName=../../application.properties`. A json payload
will be returned with the content of the `application.properties` file.

## Build

Windows
```
.\mvnw.cmd clean install
```