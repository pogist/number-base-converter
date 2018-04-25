(ns number-base-converter.input-utils
  (:gen-class)
  (:import [java.util Scanner]))

(def scanner 
  (Scanner. (System/in)))

(defn read-int
  []
  (. scanner nextInt))

(defn read-str
  []
  (. scanner next))
