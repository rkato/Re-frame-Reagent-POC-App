(ns web.app.domain.parcels.subs
  (:require [reagent.ratom :refer [make-reaction]]
            [re-frame.core :as re-frame]
            [clairvoyant.core :refer-macros [trace-forms]]
            [re-frame-tracer.core :refer [tracer]]))

(trace-forms {:tracer (tracer :color "brown")}

             (re-frame/register-sub
              :parcels 
              (fn 
                [db]
                (make-reaction (fn parcels-subscription
                                 []
                                 (:parcels  @db)))))

             (re-frame/register-sub
              :parcel-dialog-state
              (fn
                [db]
                (make-reaction (fn add-parcels-dialog-state
                                 []
                                 (:parcel-dialog-state @db))))))

