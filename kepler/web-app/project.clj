(defproject web-app "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]
                 [reagent "0.6.0-rc" :exclusions [org.clojure/tools.reader cljsjs/react]]
                 [re-frame "0.7.0"]
                 [re-com "0.8.3"]
                 [secretary "1.2.3"]
                 [garden "1.3.2"]
                 [compojure "1.5.0"]
                 [yogthos/config "0.8"]
                 [ring "1.4.0"]
                 [prismatic/schema "1.1.2"]
                 [cljs-ajax "0.5.6"]
                 [day8/re-frame-tracer "0.1.1-SNAPSHOT"]
                 [org.clojars.stumitchell/clairvoyant "0.2.0"]
                 [hiccup "1.0.5"]
                 [cljs-react-material-ui "0.2.16"]]

  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-garden "0.2.6"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "test/js"
                                    "resources/public/css"]

  :figwheel {:css-dirs ["resources/public/css"]
             :ring-handler web-app.handler/dev-handler}

  :garden {:builds [{:id           "screen"
                     :source-paths ["src/clj"]
                     :stylesheet   web-app.css/screen
                     :compiler     {:output-to     "resources/public/css/screen.css"
                                    :pretty-print? true}}]}

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.6.1"]
                   [lein-figwheel "0.5.4-3"]
                   [com.cemerick/piggieback "0.2.1"]]

    :plugins      [[lein-figwheel "0.5.4-3"]
                   [lein-doo "0.1.6"]
                   [cider/cider-nrepl "0.13.0-SNAPSHOT"]]
    }}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "web-app.core/mount-root"}
     :compiler     {:main                 web-app.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :closure-defines {"clairvoyant.core.devmode" true}}}

    {:id           "min"
     :source-paths ["src/cljs"]
     :jar true
     :compiler     {:main            web-app.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}
    {:id           "test"
     :source-paths ["src/cljs" "test/cljs"]
     :compiler     {:output-to     "resources/public/js/compiled/test.js"
                    :main          web-app.runner
                    :optimizations :none}}
    ]}

  :main web-app.server

  :aot [web-app.server]

  :prep-tasks [["cljsbuild" "once" "min"] "compile"]
  )
