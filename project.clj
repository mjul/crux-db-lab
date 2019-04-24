(defproject crux-db-lab "0.1.0-SNAPSHOT"
  :description "Playing around with the Crux database"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [juxt/crux "19.04-1.0.3-alpha-SNAPSHOT"]]
  :repl-options {:init-ns crux-db-lab.core})
