(ns {{namespace}}
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [ring.util.response :as ring-resp])
  (:gen-class))

(defn hello-world
  [request]
  (ring-resp/response "Hello World!"))

(defroutes routes
  [[["/" {:get hello-world}]]])

(def service {::http/routes routes
              ::http/resource-path "/public"
              ::http/type :jetty
              ::http/host (get (System/getenv) "HOST" "0.0.0.0")
              ::http/port (Integer/parseInt (get (System/getenv) "PORT" "8080"))})

(defn -main [& args]
  (-> service
      http/create-server
      http/start))
