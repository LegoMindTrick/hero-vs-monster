(ns user
  (:require [mount.core :as mount]
            hero-vs-monster.core))

(defn start []
  (mount/start-without #'hero-vs-monster.core/repl-server))

(defn stop []
  (mount/stop-except #'hero-vs-monster.core/repl-server))

(defn restart []
  (stop)
  (start))


