(ns api.domain.schemas.common
  (:require [schema.core :as s]))



(def Parcel  {(s/optional-key :id)  s/Num 
              :name  s/Str
              :description  s/Str
              :isdelivered Boolean
              :createdby s/Str
              (s/optional-key :createdon)  s/Any
              (s/optional-key :updatedby) s/Str
              (s/optional-key :updatedon)  s/Any })

(def Location {:id s/Num
               :parcel_id s/Num 
               :name s/Str
               :geom s/Any
               :createdby s/Str
               (s/optional-key :createdon)  s/Any
               (s/optional-key :updatedby) s/Str
               (s/optional-key :updatedon)  s/Any} )


(def User {:id s/Num })



(def parcels-schema   {:items   [{ :id   s/Num
                                   :name  s/Str
                                   :description  s/Str
                                   (s/optional-key :isdelivered) Boolean
                                   :createdby s/Str
                                   (s/optional-key :createdon)  s/Any
                                   (s/optional-key :updatedby) s/Str
                                   (s/optional-key :updatedon)  s/Any }]})
