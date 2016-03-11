(ns hero-vs-monster.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[hero-vs-monster started successfully]=-"))
   :middleware identity})
