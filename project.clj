(defproject lernin "0.1.0-SNAPSHOT"
  :description "A place for me to put code as I learn Clojure"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :main ^:skip-aot lernin.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
