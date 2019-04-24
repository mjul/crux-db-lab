(ns crux-db-lab.core
  (:require [crux.api :as crux])
  (:import [crux.api ICruxAPI]))


;;; ----------------------------------------------------------------
;;; For Documentation:
;;; See https://juxt.pro/crux/docs/configuration.html#standalone
;;; ----------------------------------------------------------------

(defn ^crux.api.ICruxAPI start-system
  "Start an in-memory Crux database server"
  []
  (crux/start-standalone-system {:kv-backend "crux.kv.memdb.MemKv"
                                 :db-dir "data/db-dir-1"}))

(defn stop-system
  "Stop the Crux database server."
  [system]
  (.close system))

(comment
  (def ^crux.api.ICruxAPI system (start-system))
  )

;;; ----------------------------------------------------------------

;;; Documentation:
;;; See https://juxt.pro/crux/docs/get_started.html


(defn picasso-example
  "The Picasso example from the Get Started guide."
  [^crux.api.ICruxAPI system]
  (let [doc-id :dbpedia.resource/Pablo-Picasso
        put-result (crux/submit-tx system [[:crux.tx/put doc-id ; id for Kafka
                                            {:crux.db/id doc-id ; id for Crux
                                             :name "Pablo"
                                             :last-name "Picasso"}
                                            #inst "2019-04-24T14:10:00"]])
        all-pablos (crux/q (crux/db system)
                           '{:find [e]
                             :where [[e :name "Pablo"]]})
        just-picasso (crux/entity (crux/db system) doc-id)]
    (println "Transaction completed:" put-result)
    (println "All Pablos:" all-pablos)
    (println "Picasso entity:" just-picasso)))

 (picasso-example system)

(comment
  ;; This works

  (let [doc-id :dbpedia.resource/Pablo-Picasso
        put-result (crux/submit-tx system [[:crux.tx/put doc-id ; id for Kafka
                             {:crux.db/id doc-id ; id for Crux
                              :name "Pablo"
                              :last-name "Picasso"}
                                            #inst "2019-04-24T14:10:00"]])
        all-pablos (crux/q (crux/db system)
                           '{:find [e]
                             :where [[e :name "Pablo"]]})
        just-picasso (crux/entity (crux/db system) doc-id)]
    (println doc-id)
    (println put-result)
    (println all-pablos)
    (println just-picasso)
    )

  )
