(ns business.logic
  (:require-macros
    [javelin.core :refer [defc defc=]]))

(defc person "Bobak")
(defc= greeting (str "Hello " person))
