(ns web.app.domain.parcels.components.parcels
  (:require [re-com.core :as re-com]
            [re-frame.core :as re-frame]
            [web.app.domain.parcels.subs]
            [clairvoyant.core :refer-macros [trace-forms]]
            [re-frame-tracer.core :refer [tracer]]
            [cljsjs.material-ui]
            [cljs-react-material-ui.core :as ui]
            [cljs-react-material-ui.reagent :as rui]
            [cljs-react-material-ui.icons :as ic]
            [reagent.core :as reagent]))

(trace-forms {:tracer (tracer :color "orange")}

             (defn parcel-item
               "Create view per  parcel / package items"
               [parcel]
               [:li 
                [:label (:name parcel)]
                [:label (:description parcel)]])


             (defn parcel-list
               "Create list view of parcels / packages"
               [parcels]
               [:div 
                [:ul.parcel-list 
                 (for [parcel @parcels] 
                   ^{:key (:id parcel)} [parcel-item parcel])]])
             
             (def new-package (atom {:name "test" :description "" :createdon ""}))
             (def package-name (reagent/atom ""))

             (defn add-parcel-form
               "Create input view for capturing parcel / package details"
               []
               [:div
                [rui/text-field
                 {:floating-label-text "Name"
                  :value @package-name
                  :on-change #(reset! package-name (-> % .-target .-value))}]
                [:br]
                [rui/text-field
                 {:floating-label-text "Description"}]
                [:br]
                [rui/date-picker
                 {:hint-text "Expected delivery date"}]
                [:br]
                [rui/raised-button
                 {:label "Enrol package"
                  :primary true
                  :label-position :true
                  :icon (ic/content-add-circle)
                  :on-touch-tap #(re-frame/dispatch [:enrol-package])}]])

             (defn add-parcel-dialog
               "Create dialog view to add new parcel"
               [parcel-dialog-state]
               [rui/dialog
                {:title "Enrol new package"
                 :key "parcel-dialog"
                 :modal false
                 :open (boolean @parcel-dialog-state)
                 :actions []
                 :on-request-close #(re-frame/dispatch [:show-parcel-dialog? false])}
                [:hr]
                (add-parcel-form)])


             (defn add-parcel-button
               []
               [:div
                {:className "row pad-10 reverse"}
                [rui/raised-button
                 {:label "Add Package"
                  :primary        true
                  :label-position :before
                  :icon (ic/content-add-circle)
                  :on-touch-tap #(re-frame/dispatch [:show-parcel-dialog? true])}]])

             (defn parcel-table-body
               [parcels]
               (for [parcel @parcels]
                 ^{:key (:id parcel)}
                 [rui/table-row
                  [rui/table-row-column (:name parcel)]
                  [rui/table-row-column (:description parcel)]
                  [rui/table-row-column]
                  [rui/table-row-column]]))

             (defn parcel-table
               [parcels parcel-dialog-state]
               [rui/paper
                [rui/mui-theme-provider
                 {:mui-theme (ui/get-mui-theme
                              {:table-header-column
                               {:text-color (ui/color :deep-orange500)}})}
                 [rui/table 
                  {:height "200px"}
                  [rui/table-header
                   {:display-select-all false
                    :adjust-for-checkbox false}
                   [rui/table-row
                    [rui/table-header-column "Name"]
                    [rui/table-header-column "Description"]
                    [rui/table-header-column "Status"]
                    [rui/table-header-column "Current location"]]]
                  [rui/table-body
                   (parcel-table-body parcels)]]]
                (add-parcel-button)
                (add-parcel-dialog parcel-dialog-state)])

             (defn system-app-bar
               [parcels parcel-dialog-state]
               [:div
                [rui/app-bar {:title              "Kepler parcel/package managment system"
                              :icon-element-right (ui/icon-button)}]
                (parcel-table parcels parcel-dialog-state)])


             (defn home-page 
               [parcels parcel-dialog-state]
               [rui/mui-theme-provider
                {:mui-theme (ui/get-mui-theme
                             {:palette {:text-color (ui/color :green600)}})}
                (system-app-bar parcels parcel-dialog-state)])
             
             (defn parcels-panel 
               []
               (let [parcels (re-frame/subscribe [:parcels])
                     parcel-dialog-state (re-frame/subscribe [:parcel-dialog-state])]
                 (fn render-home-page 
                   []
                   (home-page parcels parcel-dialog-state)))))




