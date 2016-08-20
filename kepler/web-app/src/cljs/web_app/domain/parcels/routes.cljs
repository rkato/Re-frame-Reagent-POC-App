(ns web.app.domain.parcels.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:require [re-frame.core :as re-frame]))

(defn add-parcel-routes []
  "define all parcel related routes here"

  (defroute "/parcels" [] 
    (re-frame/dispatch [:set-active-panel :parcels-panel]))

  (defroute "/add-parcel" []
    (re-frame/dispatch [:set-active-panel :add-parcel-panel])))
