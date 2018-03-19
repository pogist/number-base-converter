(ns number-base-converter.core-test
  (:require [clojure.test :refer :all]
            [number-base-converter.core :as core]))

(deftest digits
  (testing "The splitting of a number into its digits (reversed)."
    (let [num-digits (core/digits 1234)]
      (is (= num-digits [4N 3N 2N 1N]))))
  (testing "With zero number."
    (let [num-digits (core/digits 0)]
      (is (empty? num-digits)))))

(deftest anybase->decimal
  (testing "Conversion of a number in any base to the decimal base."
    (testing "With a binary number."
      (let [decimal-number (core/anybase->decimal 2 1010)]
        (is (= decimal-number 10.0))))
    (testing "With an octal number."
      (let [decimal-number (core/anybase->decimal 8 47)]
        (is (= decimal-number 39.0))))))
