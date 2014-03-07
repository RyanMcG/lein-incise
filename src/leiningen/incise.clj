(ns leiningen.incise
  (:require (leiningen.core [eval :refer [eval-in-project]]
                            [project :refer [merge-profiles]])))

(def default-profile {:dependencies [['incise "0.2.0"]]})

(defn ^:no-project-needed ^:pass-through-help incise
  [project & args]
  (let [profile (or (get-in project [:profiles :incise])
                    default-profile)]
    (eval-in-project (merge-profiles project [profile])
                     `(incise.core/-main ~@args)
                     '(require 'incise.core))))
