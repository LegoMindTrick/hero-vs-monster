(ns hero-vs-monster.routes.home
  (:require [hero-vs-monster.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]))

(defn speech1 [hero]
  (println (str (hero :name)
                " pulls out his "
                (hero :weapon)
                "!")))

(def monstertypes ["ogre" "troll" "blastended skrewt"])

(defn mkhero [name weapon] {:name name
                            :weapon weapon
                            :hp 10
                            :strength (+ 1(rand-int 60))})

(defn mkmonster [] {:strength (+ 1(rand-int 60))
                    :hp 5
                    :type (get monstertypes (rand-int 4))})

(defn fight [hero monster] (if
                             (> (hero :strength) (monster :strength))
                             "hero wins!"
                             "monster wins"))


(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  ;(GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/" [] (layout/render "hvm.html"))
  (GET "/fight" [heroname heroweapon] (fight (mkhero heroname heroweapon) (mkmonster))))

