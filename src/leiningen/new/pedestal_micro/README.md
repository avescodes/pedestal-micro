# {{name}}

TODO: Description

## TODO: Template Notes

The generated project includes both a
`build.boot` file (for [boot](http://boot-clj.com/), my preferred project manager) and,
`project.cl` (for [Leiningen](http://leiningen.org/)).

The two are roughly equivalent, but I suggest you pick one, and discard the
other.

## Tasks

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

## Template Notes

See the
[pedestal-micro README](https://github.com/rkneufeld/pedestal-micro)
for more information on how to use this template.

## License

Copyright © 2014 FIXME
