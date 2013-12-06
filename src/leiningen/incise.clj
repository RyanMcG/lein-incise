(ns leiningen.incise
  (:require (leiningen.core [eval :refer [eval-in-project]]
                            [project :refer [merge-profiles]])))

(def default-profile {:dependencies [['incise "0.1.0-SNAPSHOT"]]})

(defn ^:no-project-needed incise
  "Call incise's main method and execute it in the scope of the project if it
  exists."
  [project & args]
  (let [profile (or (get-in project [:profiles :incise])
                    default-profile)]
    (eval-in-project (merge-profiles project [profile])
                     `(incise.core/-main ~@args)
                     '(require 'incise.core))))
