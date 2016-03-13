(ns hero-vs-monster.routes.home
  (:require [hero-vs-monster.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]
            [clj-http.client :as client]
            [clojure.data.json :as json]))

(defn speech1 [hero]
  (println (str (hero :name)
                " pulls out his "
                (hero :weapon)
                "!")))

(defn randitem [vec] (vec (rand-int (count vec))))

(def monstertypes ["ogre" "troll" "skrewt" "dragon" "lizard"])

(def adjs ["brave" "stinky" "cowardly" "magnifacent" "cool" "fat" "lazy" "weird"])

(defn mkhero [name weapon] {:name name
                            :weapon weapon
                            :hp 10
                            :strength (+ 1(rand-int 60))})

(defn mkmonster [] {:strength (+ 1(rand-int 60))
                    :hp 5
                    :type (randitem monstertypes)})

(defn getgiphy [tag] (get-in (json/read-str((client/get
                       (str "http://api.giphy.com/v1/gifs/random?api_key=dc6zaTOxFJmzC&rating=pg&tag=" tag))
                      :body)) ["data" "image_url"]))

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
  (GET "/fight" [heroname heroweapon] (do
                                        (def monster (mkmonster))
                                        (def monsteradj (randitem adjs))
                                        (def heroadj (randitem adjs))
                                        (layout/render "fight.html"
                                                     {:results (fight (mkhero heroname heroweapon) monster)
                                                      :heroname heroname
                                                      :monstertype (monster :type)
                                                      :heroadj heroadj
                                                      :monsteradj monsteradj
                                                      :heroimage (getgiphy heroadj)
                                                      :monsterimage (getgiphy (monster :type))
                                                      }))))

