(ns api.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [api.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[api started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[api has shutdown successfully]=-"))
   :middleware wrap-dev})
