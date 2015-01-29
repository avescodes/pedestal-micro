(set-env! :source-paths   #{"src" "test"}
          :resource-paths #{"resources" "config"}
          :dependencies   '[[org.clojure/clojure "1.6.0"]
                            [io.pedestal/pedestal.service "0.3.1"]
                            [io.pedestal/pedestal.jetty "0.3.1"]

                            ;; Logging
                            [ch.qos.logback/logback-classic "1.1.2"
                             :exclusions [org.slf4j/slf4j-api]]
                            [org.slf4j/jul-to-slf4j "1.7.7"]
                            [org.slf4j/jcl-over-slf4j "1.7.7"]
                            [org.slf4j/log4j-over-slf4j "1.7.7"]])


(task-options! pom {:project '{{namespace}}
                    :version "0.0.1-SNAPSHOT"
                    :description "FIXME: write description"
                    :license {"License Name" "All Rights Reserved"}}
               aot {:namespace #{'{{namespace}}.bootstrap}}
               jar {:main '{{namespace}}.bootstrap})


(require '[{{raw-name}} :as {{raw-name}}])

(deftask run
  "Main task"
  []
  (comp
   (watch)
   ({{raw-name}}/-main)))
