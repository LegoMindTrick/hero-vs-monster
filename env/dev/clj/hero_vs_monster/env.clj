(ns hero-vs-monster.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [hero-vs-monster.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[hero-vs-monster started successfully using the development profile]=-"))
   :middleware wrap-dev})
