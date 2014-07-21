(ns {{namespace}}
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [ring.util.response :as ring-resp]))

(defn hello-world
  [request]
  (ring-resp/response "Hello World!"))

(defroutes routes
  [[["/" {:get hello-world}]]])

(def service {::http/routes routes
              ::http/resource-path "/public"
              ::http/host (get (System/getenv) "HOST" "localhost")
              ::http/type :jetty
              ::http/port (int (get (System/getenv) "PORT" 8080))})

(defn -main [& args]
  (-> service
      http/create-server
      http/start))
