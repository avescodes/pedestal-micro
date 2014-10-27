(ns {{namespace}}.bootstrap
  (:gen-class))

(def main-ns "{{namespace}}")

(defn -main []
  (require (symbol main-ns))
  ((resolve (symbol main-ns "-main"))))
