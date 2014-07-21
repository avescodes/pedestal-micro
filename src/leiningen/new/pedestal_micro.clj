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
              :sanitized (name-to-path sanitized-ns)}]
    (main/info "Generating fresh 'lein new' pedestal-micro project.")
    (->files data
             ["README.md" (render "README.md" data)]
             ["project.clj" (render "project.clj" data)]
             [".gitignore" (render ".gitignore" data)]
             ["src/{{sanitized}}.clj" (render "service.clj" data)]
             ["test/{{sanitized}}_test.clj" (render "service_test.clj" data)]
             ["config/logback.xml" (render "logback.xml" data)])))
