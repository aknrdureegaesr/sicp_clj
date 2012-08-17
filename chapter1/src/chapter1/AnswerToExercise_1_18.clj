(defn mul [a, b]
  (if (< a b) (recur b a) ; a heuristical optimization
    (let [
          even? (fn [x] (= 0 (rem x 2)))
          half (fn [x] (quot x 2))]
      ((fn [x y p] 
        (if
          (= 0 y) p
          (recur (+ x x) (half y) (if (even? y) p (+ p x)))))
        a b 0))))