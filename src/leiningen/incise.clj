(ns leiningen.incise
  (:require (leiningen.core [eval :refer [eval-in-project]]
                            [project :refer [merge-profiles]])))

(def default-profile {:dependencies [['incise "0.1.0-SNAPSHOT"]]})

(defn ^{:no-project-needed true
        :help-arglists []}
  incise
  [project & args]
  (let [profile (or (get-in project [:profiles :incise])
                    default-profile)]
    (eval-in-project (merge-profiles project [profile])
                     `(incise.core/-main ~@args)
                     '(require 'incise.core))))

(defn help
  "Use help built into incise instead of leiningen's."
  []
  (incise {} "--help"))
