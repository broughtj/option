(ns option.core
  (:gen-class))

(defrecord Option[strike expiry payoff-type])

(defmulti payoff :payoff-type)

(defmethod payoff :call [option spot]
  (max (- spot (:strike option)) 0.0))

(defmethod payoff :put [option spot]
  (max (- (:strike option) spot) 0.0))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (do 
    (def the-call (map->Option {:strike 40.0 :expiry 1.0 :payoff-type :call}))
    (def the-put (map->Option {:strike 40.0 :expiry 1.0 :payoff-type :put}))
    (println (payoff the-call 41.0))
    (println (payoff the-put 39.0))))
