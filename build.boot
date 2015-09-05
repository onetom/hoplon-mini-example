(set-env!
  :project 'hoplon-mini-example
  :version "0.1.0-SNAPSHOT"
  :dependencies
  '[
    [hoplon/boot-hoplon "0.1.7"]
    [hoplon "6.0.0-alpha9"]
    [pandeiro/boot-http "0.6.3"]
    [adzerk/boot-reload "0.3.1"]
    ; Without explicit ClojureScript dependency, we would get an error like:
    ;   java.io.FileNotFoundException:
    ;     Could not locate cljs/analyzer__init.class or
    ;     cljs/analyzer.clj on classpath.
    [org.clojure/clojurescript "1.7.48"]
    [adzerk/boot-cljs "1.7.48-3"]
    [cljsjs/boot-cljsjs "0.5.0" :scope "test"]]
  :source-paths #{"src"}
  :resource-paths #{"assets"})

(require
  '[hoplon.boot-hoplon :refer [hoplon prerender]]
  '[adzerk.boot-reload :refer [reload]]
  '[pandeiro.boot-http :refer [serve]]
  '[adzerk.boot-cljs :refer [cljs]]
  '[cljsjs.boot-cljsjs :refer [from-cljsjs]])

(task-options!
  from-cljsjs {:profile :development}
  hoplon {:pretty-print true}
  cljs {:optimizations :none
        :source-map true
        :compiler-options {:pseudo-names false}})

(deftask dev []
  (comp
    (watch) (serve)
    (from-cljsjs) (hoplon) (reload) (cljs)
    (prerender) (speak)))
