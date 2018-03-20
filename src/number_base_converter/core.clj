(ns number-base-converter.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn digits
  [number]
  (loop [quo number coll []]
    (if (= quo 0N)
      coll
      (let [remaining (rem quo 10N)
            new-quo (bigint (/ quo 10N))]
        (recur new-quo (conj coll remaining))))))

;Not pure nor safe at all.
(defn parse-hex-digits
  [hex-string]
  (reverse (map #(read-string (str "0x" %)) (str/split hex-string #""))))

(defn anybase->decimal
  [base number-digits]
  (reduce 
   + 
   (map-indexed 
    (fn [indx digit]
      (* digit (Math/pow base indx))) number-digits)))

(def binary->decimal
  (partial anybase->decimal 2))

(def octal->decimal
  (partial anybase->decimal 8))

(def hex->decimal
  (partial anybase->decimal 16))
