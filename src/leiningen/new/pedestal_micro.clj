(ns leiningen.new.pedestal-micro
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files sanitize-ns project-name]]
            [leiningen.core.main :as main]))

(def render (renderer "pedestal-micro"))

(defn pedestal-micro
  "Generate a new Pedestal micro-service"
  [name]
  (let [sanitized-ns (sanitize-ns name)
        data {:raw-name name
              :name (project-name name)
              :namespace sanitized-ns
              :sanitized (name-to-path sanitized-ns)
              :namespace-set (str "'#{" namespace "}")}]
    (main/info "Generating fresh 'lein new' pedestal-micro project.")
    (->files data
             ["README.md" (render "README.md" data)]
             ["project.clj" (render "project.clj" data)]
             ["build.boot" (render "build.boot" data)]
             [".gitignore" (render ".gitignore" data)]
             ["Dockerfile" (render "Dockerfile" data)]
             ["src/{{sanitized}}.clj" (render "service.clj" data)]
             ["src/{{sanitized}}/routes.clj" (render "routes.clj" data)]
             ["src/{{sanitized}}/db.clj" (render "db.clj" data)]
             ["test/{{sanitized}}_test.clj" (render "service_test.clj" data)]
             ["test/{{sanitized}}/test_helpers.clj" (render "test_helpers.clj" data)]
             ["resources/logback.xml" (render "logback.xml" data)])))
