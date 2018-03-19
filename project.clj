(defproject number-base-converter "0.1.0-SNAPSHOT"
  :description "A simple number base converter program written in Clojure. Built for a college project."
  :url "https://github.com/pogist/number-base-converter"
  :license {:name "The MIT License (MIT)"
            :url "https://mit-license.org/"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot number-base-converter.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
