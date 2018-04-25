(ns number-base-converter.main
  (:gen-class)
  (:require [number-base-converter.core :refer :all]
            [number-base-converter.input-utils :as in]
            [clojure.string :as str]))

(defmacro defoption
  [name option-number value text]
  `(let [prompt# (str ~option-number ") " ~text)]
     (def ~name {:option ~option-number :value ~value :prompt prompt#})))

(defoption binary  1 2  "Binary")
(defoption octal   2 8  "Octal")
(defoption decimal 3 10 "Decimal")
(defoption hex     4 16 "Hexadecimal")

(defn present-options
  [options]
  (doseq [option options]
    (println (:prompt option))))

