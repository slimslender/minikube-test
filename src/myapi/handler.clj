(ns myapi.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [ring.server.standalone :as standalone])
  (:gen-class))

(s/defschema Pizza
  {:name s/Str
   (s/optional-key :description) s/Str
   :size (s/enum :L :M :S)
   :origin {:country (s/enum :FI :PO)
            :city s/Str}})

(def app
  (api
   {:swagger
    {:ui "/"
     :spec "/swagger.json"
     :data {:basePath "/ANBD24ZEC/production/slimslender/minikube-test"
            :info {:title "My-api"
                   :description "Compojure Api example"}
            :tags [{:name "api", :description "some apis"}]}}}

   (GET "/health" []
     (ok))

   (GET "/info" []
     (ok))

   (context "/api" []
     :tags ["api"]

     (GET "/plus" []
       :return {:result Long}
       :query-params [x :- Long, y :- Long]
       :summary "adds two numbers together"
       (ok {:result (+ x y)}))

     (POST "/echo" []
       :return Pizza
       :body [pizza Pizza]
       :summary "echoes a Pizza"
       (ok pizza)))))

(defn ^:skip-aot -main [& args]
  (standalone/serve app {:port 8080
                         :join? true
                         :open-browser? false}))