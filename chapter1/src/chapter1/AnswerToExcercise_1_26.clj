;; compute (base ** exp) mod m
(defn expmod [base exp m]
  ((fn [x n result]
     (if (= n 0) result
       (recur
         (mod (* x x) m)
         (quot n 2)
         (if (even? n) result (mod (* result x) m)))))
    base exp 1))

(defn test_fermat? [a n]
  (= a (expmod a n n)))

;;; For some reason I do not comprehend, this does not work:
;; (defn is_Carmichael? [n]
;;   ((fn [a_min n]
;;      (cond ((= a_min n) true)
;;            ((test_fermat? a_min n) (recur (inc a_min) n))
;;            (true false))) 1 n))
 
(defn is_Carmichael? [n]
  ((fn [a_min n]
     (if (= a_min n) true
       (if (test_fermat? a_min n) (recur (inc a_min) n)
         false))) 1 n))

(def result (map (fn [x] [x (is_Carmichael? x)]) '(561 1105 1729 2465 2821 6601)))

;;; Bonus material: Find all Carmichael numbers up to, e.g., 10000000:

(defn prime? [a]
  (= a
  ((fn [test-divisor]
     (cond (< a (* test-divisor test-divisor)) a
           (= 0 (rem a test-divisor)) test-divisor
           true (recur (inc test-divisor))))
    2)))

(defn all_Carmichael_numbers_upto [max]
  ((fn from_start[start]
     (cond
       (<= max start) nil
       (and (not (prime? start)) (is_Carmichael? start))
       (do (prn start) (lazy-seq (cons start (from_start (inc start)))))
       true (recur (inc start)))) 2))

(defn eat-silently [x] 
  (if (empty? x) nil (recur (rest x))))


(defn duration [f]
  (let [start (System/nanoTime)
        result (f)
        finish (System/nanoTime)]
    (* 1e-9 (- finish start))))

(defn print-all-Carmichaels [max]
  (duration (fn [] (eat-silently (all_Carmichael_numbers_upto max)))))

; => (print-all-Carmichaels 10000000)
; 561
; 1105
; 1729
; 2465
; 2821
; 6601
; 8911
; 10585
; 15841
; 29341
; 41041
; 46657
; 52633
; 62745
; 63973
; 75361
; 101101
; 115921
; 126217
; 162401
; 172081
; 188461
; 252601
; 278545
; 294409
; 314821
; 334153
; 340561
; 399001
; 410041
; 449065
; 488881
; 512461
; 530881
; 552721
; 656601
; 658801
; 670033
; 748657
; 825265
; 838201
; 852841
; 997633
; 1024651
; 1033669
; 1050985
; 1082809
; 1152271
; 1193221
; 1461241
; 1569457
; 1615681
; 1773289
; 1857241
; 1909001
; 2100901
; 2113921
; 2433601
; 2455921
; 2508013
; 2531845
; 2628073
; 2704801
; 3057601
; 3146221
; 3224065
; 3581761
; 3664585
; 3828001
; 4335241
; 4463641
; 4767841
; 4903921
; 4909177
; 5031181
; 5049001
; 5148001
; 5310721
; 5444489
; 5481451
; 5632705
; 5968873
; 6049681
; 6054985
; 6189121
; 6313681
; 6733693
; 6840001
; 6868261
; 7207201
; 7519441
; 7995169
; 8134561
; 8341201
; 8355841
; 8719309
; 8719921
; 8830801
; 8927101
; 9439201
; 9494101
; 9582145
; 9585541
; 9613297
; 9890881
; 809.4752721110001