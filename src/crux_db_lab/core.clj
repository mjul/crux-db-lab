(ns crux-db-lab.core
  (:require [crux.api :as crux])
  (:import [crux.api ICruxAPI]))


(defn start-system
  "Start an in-memory Crux databse server"
  []
  (crux/start-standalone-system {:kv-backend "crux.kv.memdb.MemKv"
                                 :db-dir "data/db-dir-1"}))

(defn stop-system
  "Stop the Crux database server."
  []
  (.close system))

(def ^crux.api.ICruxAPI system
  (start-system))


(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
