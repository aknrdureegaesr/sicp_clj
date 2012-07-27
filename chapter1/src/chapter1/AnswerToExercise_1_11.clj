(defn f-rec[n]
  (if (< n 3) n
      (+ (f-rec(- n 1)) (* 2 (f-rec(- n 2))) (* 3 (f-rec(- n 3))))))

(defn f-it[n]
  (cond (= n 0) 0
        (= n 1) 1
        (= n 2) 2
        true
        ((fn f-inner[f_m-3 f_m-2 f_m-1 m]
           (let [f_m (+ f_m-1 (* 2 f_m-2) (* 3 f_m-3))]
             (if (= m n) f_m
               (recur f_m-2 f_m-1 f_m (inc m))))) 0 1 2 3)))

(defn f-lazy[n]
  (nth
    ((fn fs [f_m-3 f_m-2 f_m-1]
       (lazy-seq (cons f_m-3 (fs f_m-2 f_m-1 (+ f_m-1 (* 2 f_m-2) (* 3 f_m-3)))))) 0 1 2)
    n))
           
    