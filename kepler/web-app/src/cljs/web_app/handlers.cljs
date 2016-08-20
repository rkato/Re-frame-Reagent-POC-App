(ns web-app.handlers
  (:require [re-frame.core :as re-frame]
            [web-app.db :as db]
            [web.app.domain.parcels.handlers :refer [check-schema-mw]]))

(re-frame/register-handler
 :initialize-db
 check-schema-mw ;We need to ensure that we don't put gabbage into app-db
 (fn  [_ _]
   db/default-db))


(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))
