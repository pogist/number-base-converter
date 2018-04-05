(ns number-base-converter.main
  (:gen-class)
  (:require [number-base-converter.core :refer :all]
            [clojure.string :as str]))

(defn- option->base
  [option]
  (cond
    (= option "1") 2
    (= option "2") 8
    (= option "3") 10
    (= option "4") 16))

(defn- base->digits-fn
  [base]
  (if (= base 16)
    parse-hex-digits
    digits))

(defn- parse-input-for-base
  [base raw-input]
  (if (not= base 16)
    (Integer. raw-input)
    raw-input))

(defn- parse-partial-result-for-base
  [base partial-result]
  (if (= base 16)
    (decimal-digits->hex-digits partial-result)
    partial-result))

(defn -main
  []
  (do 
    (println "From which base would you like to convert? \n\n1) Binary\n2) Octal\n3) Decimal\n4) Hexadecimal\n")
    (print "Base: ")
    (flush)

    (let [origin-base (option->base (read-line))]
      (println "To which base would you like to go? \n\n1) Binary\n2) Octal\n3) Decimal\n4) Hexadecimal\n")
      (print "Base: ")
      (flush)

      (let [destination-base (option->base (read-line))
            conversion-function (comp (partial decimal->anybase destination-base)
                                      (partial anybase->decimal origin-base))]
        (print "The number you want to convert: ")
        (flush)
        
        (let [raw-input (read-line)
              digits-fn (base->digits-fn origin-base)
              parsed-number (parse-input-for-base origin-base raw-input)
              partial-result (conversion-function (digits-fn parsed-number))
              final-result (parse-partial-result-for-base destination-base partial-result)]
          
          (println (str "\nResult: "
                        (->> (reverse final-result)
                             (map str)
                             (str/join)))))))))
