# pedestal-micro

A Leiningen template for building micro-services in Pedestal.

## Creating a project

```sh
$ lein new pedestal-micro com.example/web-service
```

The generated project includes both a
`build.boot` file (for [boot](http://boot-clj.com/), my preferred project manager) and,
`project.cl` (for [Leiningen](http://leiningen.org/)).

The two are roughly equivalent, but I suggest you pick one, and discard the
other.

### Tasks

| Task                   |     Boot      |   Leiningen    |
|------------------------|---------------|----------------|
| Launch a REPL          | `boot repl`   | `lein repl`    |
| Run Tests              | `boot test`   | `lein test`    |
| Launch a server        | `boot server` | `lein run`     |
| Build a deployable JAR | `boot build`  | `lein uberjar` |

## Building a Docker container

```sh
# With Leiningen
$ lein uberjar

# With Boot
$ boot build

$ sudo docker build .
```

## What's next?

* Scripts for building, tagging and deploying Docker containers (this includes
  automatically building an uberjar).
* Better [12 Factor App](http://12factor.net/logs) logging support (currently
  only supervisord prints to STDOUT).
