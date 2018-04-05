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

(defn decimal->anybase
  [base decimal-number]
  (loop [quo decimal-number digits []]
    (if (< quo base)
      (conj digits quo)
      (recur (int (/ quo base)) 
             (conj digits (rem quo base))))))

(def decimal->binary
  (partial decimal->anybase 2))

(def decimal->octal
  (partial decimal->anybase 8))

(def decimal->hex
  (partial decimal->anybase 16))
