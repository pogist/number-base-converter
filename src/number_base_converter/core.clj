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

(defn decimal-digits->hex-digits
  [decimal-digits]

  (map (fn [decimal-digit]
         (cond
           (= decimal-digit 10) "A"
           (= decimal-digit 11) "B"
           (= decimal-digit 12) "C"
           (= decimal-digit 13) "D"
           (= decimal-digit 14) "E"
           (= decimal-digit 15) "F"
           :else (str decimal-digit)))
       decimal-digits))

(defn anybase->decimal
  [base number-digits]
  (reduce 
   + 
   (map-indexed 
    (fn [indx digit]
      (* digit (Math/pow base indx))) number-digits)))

(defn decimal->anybase
  [base decimal-number]
  (loop [quo (int decimal-number) digits []]
    (if (< quo base)
      (conj digits quo)
      (recur (int (/ quo base)) 
             (conj digits (rem quo base))))))

