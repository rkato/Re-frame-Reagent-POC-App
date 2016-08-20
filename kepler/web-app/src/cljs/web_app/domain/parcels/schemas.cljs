(ns web.app.domain.parcels.schemas
  (:require [schema.core :as s]))


;todo: move this to central schema

(def parcels-schema   {:parcels   [{ :id   s/Num
                                   :name  s/Str
                                   :description  s/Str
                                   (s/optional-key :isdelivered) s/Bool
                                   :createdby s/Str
                                   (s/optional-key :createdon)  s/Any
                                   (s/optional-key :updatedby) s/Str
                                   (s/optional-key :updatedon)  s/Any }]
                       :active-panel s/Any
                       :parcel-dialog-state s/Bool})
