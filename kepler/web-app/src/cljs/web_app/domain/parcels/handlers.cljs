(ns web.app.domain.parcels.handlers
  (:require [re-frame.core :as re-frame]
            [web.app.domain.parcels.schemas :refer [ parcels-schema ]]
            [schema.core :as s]
            [ajax.core :refer [GET]]
            [clairvoyant.core :refer-macros [trace-forms]]
            [re-frame-tracer.core :refer [tracer]]))

(trace-forms {:tracer (tracer :color "green")}

             ;;handler middleware. we need to validate our data in the db.

             (defn check-and-throw
  "throw an exception if db doesn't match the schema."
  [a-schema db]
  (if-let [problems  (s/check a-schema db)]
    (throw (js/Error. (str "schema check failed: " problems)))))


             (def check-schema-mw
  "Validate app-db"
  (re-frame/after (partial check-and-throw parcels-schema)))


             (def parcels-middleware 
  "Validate parcel values"
  [check-schema-mw (re-frame/path :parcels)])

             ;;debugger
             (defn handler [response]
               (.log js/console (str response)))

             (re-frame/register-handler
              :show-parcel-dialog?
              check-schema-mw
              (fn show-parcel-dialog-handler
                [db [_ value]]
                (assoc db :parcel-dialog-state value)))
             

             (re-frame/register-handler
              :process-parcels
              check-schema-mw 
              (fn process-parcels-handler
                [db [_ parcels]]
                (-> db 
                    (assoc :parcels (:items parcels))
                    (assoc :parcel-dialog-state false))))

             (re-frame/register-handler
              :bad-response
              (fn
                [db [_ response]]
                (-> db 
                    (assoc :error-parcel-service "Unable to fetch all active parcels"))))


             (re-frame/register-handler
              :load-parcels
              (fn fetch-parcels-from-server-handler
                [db _]
                                        ;(handler db)
                (ajax.core/GET
                 ;Todo: define app constants like api endpoints
                 "http://localhost:3000/api/parcels"
                 {:handler #(re-frame/dispatch [:process-parcels %1])
                  :error-handler #(re-frame/dispatch [:bad-response %1])})
                db)))
