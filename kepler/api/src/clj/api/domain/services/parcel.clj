(ns api.domain.services.parcel
  (:require [api.db.core :as db]))


(defn find-parcel-by-id [params]
  "Given an :id return the corresponding parcel"
  (db/find-parcel-by-id params))


(defn enrol-parcel! [parcel]
  "Add a parcel to the Kepler system"
  (db/add-parcel! parcel))


(defn get-all-parcels []
  "Get all active parcels"
  (db/get-parcels))


