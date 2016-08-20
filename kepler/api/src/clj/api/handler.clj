(ns api.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [api.routes.services :refer [service-routes]]
            [compojure.route :as route]
            [api.env :refer [defaults]]
            [mount.core :as mount]
            [api.middleware :as middleware]))

(mount/defstate init-app
                :start ((or (:init defaults) identity))
                :stop  ((or (:stop defaults) identity)))

(def app-routes
  (routes
    #'service-routes
    (route/not-found
      "page not found check your url")))


(defn app [] (middleware/wrap-base #'app-routes))

