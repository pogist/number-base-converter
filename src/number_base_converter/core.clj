(ns number-base-converter.core
  (:gen-class))

(defn digits
  [number]
  (loop [quo number coll []]
    (if (= quo 0N)
      coll
      (let [remaining (rem quo 10N)
            new-quo (bigint (/ quo 10N))]
        (recur new-quo (conj coll remaining))))))

(defn anybase->decimal
  [base number]
  (let [number-digits (digits number)]
    (reduce 
     + 
     (map-indexed 
      (fn [indx digit]
        (* digit (Math/pow base indx)))
      number-digits))))

(def binary->decimal
  (partial anybase->decimal 2))

(def octal->decimal
  (partial anybase->decimal 8))
