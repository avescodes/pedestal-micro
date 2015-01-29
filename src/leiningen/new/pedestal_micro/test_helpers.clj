(ns {{namespace}}.test-helpers
  (:require [datomic.api :as d]
            [io.pedestal.http :as http]
            [io.pedestal.test :refer [response-for]]
            [cheshire.core :as json]
            [{{namespace}}]
            [{{namespace}}.db :as db]))

(defonce ^:private service-fn (atom nil))

(defn test-service
  "Return a service-fn for use with Pedestal's `response-for` test helper."
  []
  (db/bootstrap! db/uri)
  (swap! service-fn #(or % (::http/service-fn (http/create-servlet {{namespace}}/service)))))

(defn with-seeds
  "Return a db with seed tx-data applied"
  ([tx-data] (with-seeds (d/db (d/connect db/uri)) tx-data))
  ([db tx-data]
   (:db-after (d/with db tx-data))))
