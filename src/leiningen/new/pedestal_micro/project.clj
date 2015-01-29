(defproject {{raw-name}} "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "All Rights Reserved"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [io.pedestal/pedestal.service "0.3.1"]
                 [io.pedestal/pedestal.jetty "0.3.1"]

                 [ns-tracker "0.2.2"]
                 [environ "1.0.0"]

                 ;; Datomic, if your heart desires it
                 [com.datomic/datomic-free "0.9.5130" :exclusions [joda-time
                                                                   org.slf4j/slf4j-nop
                                                                   org.slf4j/slf4j-log4j12]]
                 [io.rkn/conformity "0.3.4" :exclusions [com.datomic/datomic-free]]

                 ;; Logging
                 [ch.qos.logback/logback-classic "1.1.2" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.7"]
                 [org.slf4j/jcl-over-slf4j "1.7.7"]
                 [org.slf4j/log4j-over-slf4j "1.7.7"]]
  :min-lein-version "2.0.0"
  :resource-paths ["resources"]
  :aot [{{namespace}}]
  :main {{namespace}})
