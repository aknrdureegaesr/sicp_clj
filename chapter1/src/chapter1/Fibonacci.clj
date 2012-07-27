;; Copied from http://en.wikibooks.org/wiki/Clojure_Programming/Examples/Lazy_Fibonacci

(defn fib []
  ((fn inner_fib[x y] (lazy-seq (cons x (inner_fib y (+ x y))))) 1 1))