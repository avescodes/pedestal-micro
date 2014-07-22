# pedestal-micro

A Leiningen template for building micro-services in Pedestal.

## Creating a project

```sh
$ lein new pedestal-micro com.example/web-service
```

## Building a Docker container

```sh
$ lein uberjar
$ sudo docker build .
```

## What's next?

* Scripts for building, tagging and deploying Docker containers (this includes
  automatically building an uberjar).
* Better [12 Factor App](http://12factor.net/logs) logging support (currently
  only supervisord prints to STDOUT).
