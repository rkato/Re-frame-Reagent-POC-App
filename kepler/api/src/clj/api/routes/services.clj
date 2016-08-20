(ns api.routes.services
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [api.domain.services.parcel :as services]
            [api.domain.schemas.common :as data]
            [api.cors :refer [cors-mw]]))


(defapi service-routes
  {:swagger {:ui "/swagger-ui"
             :spec "/swagger.json"
             :data {:info {:version "1.0.0"
                           :title "Kepler API"
                           :description "Kepler services define all interactions with 
the backend and are independent of client side app (web or mobile)"}}}}
  (context "/api" []
           :tags ["Parcel Context"]

           (GET "/parcel/:id" []
                :return [data/Parcel]
                :path-params [id :- Long]
                :middleware [cors-mw]
                :summary "return a parcel with associated id "
                (ok (services/find-parcel-by-id {:id id})))

           (GET "/parcels" [] 
                :return data/parcels-schema
                :summary "returns all active parcels on the Kepler System"
                :middleware [cors-mw]
                (ok {:items (services/get-all-parcels)}))
           

           (POST "/parcel" []
                 :return Long
                 :body [parcel data/Parcel]
                 :middleware [cors-mw]
                 :summary "Enrol new parcel"
                 (ok (services/enrol-parcel! parcel)))))

