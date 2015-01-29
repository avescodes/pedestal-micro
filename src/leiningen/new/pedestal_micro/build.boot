(set-env! :source-paths   #{"src"}
          :test-paths     #{"test"}
          :resource-paths #{"resources" "config"}
          :dependencies   '[[org.clojure/clojure "1.6.0"]
                            [io.pedestal/pedestal.service "0.4.0-SNAPSHOT"]
                            [io.pedestal/pedestal.jetty "0.4.0-SNAPSHOT"]

                            [ns-tracker "0.2.2"]
                            [environ "1.0.0"]

                            ;; Datomic, if your heart desires it
                            [com.datomic/datomic-free "0.9.5130" :exclusions [joda-time
                                                                              org.slf4j/slf4j-nop
                                                                              org.slf4j/slf4j-log4j12]]
                            [io.rkn/conformity "0.3.4" :exclusions [com.datomic/datomic-free]]

                            ;; Logging
                            [ch.qos.logback/logback-classic "1.1.2"
                             :exclusions [org.slf4j/slf4j-api]]
                            [org.slf4j/jul-to-slf4j "1.7.7"]
                            [org.slf4j/jcl-over-slf4j "1.7.7"]
                            [org.slf4j/log4j-over-slf4j "1.7.7"]])

(def version "0.0.1-SNAPSHOT")
(task-options! pom {:project '{{name}}
                    :version (str version "-standalone")
                    :description "FIXME: write description"
                    :license {"License Name" "All Rights Reserved"}})

;; == Datomic =============================================
(load-data-readers!)

(deftask bootstrap
  "Bootstrap the Datomic database"
  []
  (require '[{{namespace}}.db :as db])
  ((resolve 'db/bootstrap!) @(resolve '{{namespace}}.db/uri)))

;; == Testing tasks ========================================

(deftask with-test
  "Add test to source paths"
  []
  (set-env! :source-paths #(clojure.set/union % (get-env :test-paths)))
  identity)

;; Include test/ in REPL sources
(replace-task!
  [r repl] (fn [& xs] (with-test) (apply r xs)))

(require '[clojure.test :refer [run-tests]])

(deftask test
  "Run project tests"
  []
  (with-test)
  (bootstrap)
  (set-env! :dependencies #(conj % '[org.clojure/tools.namespace "0.2.8"]))
  (require '[clojure.tools.namespace.find :refer [find-namespaces-in-dir]])
  (let [find-namespaces-in-dir (resolve 'clojure.tools.namespace.find/find-namespaces-in-dir)
        test-nses              (->> (get-env :test-paths)
                                    (mapcat #(find-namespaces-in-dir (clojure.java.io/file %)))
                                    distinct)]
    (doall (map require test-nses))
    (apply clojure.test/run-tests test-nses)))

;; == Server Tasks =========================================

(deftask build
  "Build my project."
  []
  (comp (aot :namespace '#{ {{namespace}} })
        (pom)
        (uber)
        (jar :main '{{namespace}})))

(require '[{{namespace}} :as {{namespace}}])

(deftask server
  "Run a web server"
  []
  ({{namespace}}/start :io.pedestal.http/join? true))

