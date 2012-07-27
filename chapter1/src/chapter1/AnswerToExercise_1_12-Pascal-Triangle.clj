;; I'm afraid this is not a recursive, but only an iterative process.
(defn triangle[]
  "Produce a lazy sequence of rows of Pascal's triangle."
  (defn next-row[row] (map + (cons 0 row) (concat row `(0))))
  ((fn rows[row] (lazy-seq (cons row (rows (next-row row))))) `(1)))