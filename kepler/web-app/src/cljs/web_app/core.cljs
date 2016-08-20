(ns web-app.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [devtools.core :as devtools]
              [web-app.handlers]
              [web-app.subs]
              [web-app.routes :as routes]
              [web-app.views :as views]
              [web-app.config :as config]))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")
    (devtools/install!)))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  ;(re-frame/dispatch-sync [:initialize-db])
  (re-frame/dispatch-sync [:load-parcels])
  (dev-setup)
  (mount-root))
