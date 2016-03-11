(ns hero-vs-monster.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [hero-vs-monster.layout :refer [error-page]]
            [hero-vs-monster.routes.home :refer [home-routes]]
            [compojure.route :as route]
            [hero-vs-monster.middleware :as middleware]))

(def app-routes
  (routes
    (wrap-routes #'home-routes middleware/wrap-csrf)
    (route/not-found
      (:body
        (error-page {:status 404
                     :title "page not found"})))))

(def app (middleware/wrap-base #'app-routes))
