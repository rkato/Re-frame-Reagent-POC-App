(ns web-app.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [web-app.core-test]))

(doo-tests 'web-app.core-test)
