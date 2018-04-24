(ns number-base-converter.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defmulti digits
  (fn [based-number]
    (:base based-number)))

(defmethod digits :default
  [{_ :base number :value}]
  (loop [quotient number digits-coll []]
    (if (= quotient 0N)
      digits-coll
      (recur (bigint (/ quotient 10N))
             (conj digits-coll (rem quotient 10N))))))

(defmethod digits 16
  [{_ :base hex-string :value}]
  (reverse (map #(read-string (str "0x" %)) (str/split hex-string #""))))

(defn- char-range 
  [start end]
  (map char (range (int start) (int end))))

(def hex-digits
  (into 
   {}
   (map (fn [digit char] [digit char])
        (range 10 16)
        (char-range \A \G))))

(defn ->str
  [digit]
  (if (< digit 10)
    (str digit)
    (str (get hex-digits digit))))

(defn digits->str
  [digits]
  (reduce str (map ->str digits)))

(defn ->decimal
  [{base :base value :value}]
  (let [digits (digits value)]
    (reduce + (map-indexed
               (fn [index digit]
                 (* digit (Math/pow base index))) 
               digits))))

(defn decimal->anybase
  [base decimal-number]
  (loop [quotient (int decimal-number) digits []]
    (if (< quotient base)
      (conj digits quotient)
      (recur (int (/ quotient base)) 
             (conj digits (rem quotient base))))))

