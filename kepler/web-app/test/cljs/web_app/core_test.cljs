(ns web-app.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [web-app.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
