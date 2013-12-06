(ns leiningen.incise
  (:require [leiningen.core.eval :refer [eval-in-project]]))

(defn ^:no-project-needed incise
  "Call incise's main method and execute it in the scope of the project if it
  exists."
  [project & args]
  (if (:root project)
    (eval-in-project project
                     `(incise.core/-main ~@args)
                     '(require 'incise.core))
    (do
      (require 'incise.core)
      (apply incise.core/-main args))))
