(defn long-random [max]
  ((fn [x] (rem (if (< x 0) (- x) x) max))
    (.. (java.util.Random.) (nextLong))))

;; compute (base ** exp) mod m
(defn expmod [base exp m]
  ((fn [x n result]
     (if (= n 0) result
       (recur
         (mod (* x x) m)
         (quot n 2)
         (if (even? n) result (mod (* result x) m)))))
    base exp 1))

(defn fermat-test? [n]
  ((fn [a] (= (expmod a n n) a))
  (long-random n)))

(defn prime? [a]
  (= a
  ((fn [test-divisor]
     (cond (< a (* test-divisor test-divisor)) a
           (= 0 (rem a test-divisor)) test-divisor
           true (recur (inc test-divisor))))
    2)))

(defn find-lowest-prime [x]
    (if (prime? x) x (recur (inc x))))

;; We cannot go quite as far with this as we could with the real prime impl,
;; as squares of numbers we look at must still fit into a long.
(def primes (map find-lowest-prime
       ((fn search_starts[start]
          (if (< start (/ (Long/MAX_VALUE) start))
            (lazy-seq (cons start (search_starts (* 10 start))))
            nil)) 10)))


(defn duration [f]
  (let [start (System/nanoTime)
        result (f)
        finish (System/nanoTime)]
    (* 1e-9 (- finish start))))


;; in µs
(def fermat-test-on-primes-duration
  (map (fn [d] (* d 1e6)) (map (fn [x] (duration (fn [] (fermat-test? x)))) primes)))

;; Results:
;; (427.33700000000005
;;   59.843
;;   89.31099999999999
;;  114.98500000000001
;;  133.675
;;   87.853
;;   85.572
;;  101.57600000000001
;;   75.44200000000001)

;; Differences:

(defn diffs [v]
     ((fn diffs[f r]
        (if (empty? r) nil
          (lazy-seq (cons (- (first r) f) (diffs (first r) (rest r)))))) (first v) (rest v)))

;; (-367.494
;;    29.46799999999999
;;    25.67400000000002
;;    18.689999999999998
;;   -45.82200000000002
;;    -2.2809999999999917
;;    16.004000000000005
;;   -26.134)
;;
;; We would expect more or less the same value in the differences.
;;
;; Interpretation of results seen: Again JIT shows muscles.
;;
;; The first calculation pays startup time, so it is way too slow,
;; resulting in a negative difference.
;; Then, we see three times more or less the same value in the differences,
;; but there is already some speedup visible here.
;; Then, I guess, the JIT functionality of Java sets in, and,
;; instead of some 20 µs longer, the functionality takes
;; 46 µs _less_ time, the next one even 2 µs less than that.
;; The JIT does not seem to settle down during the next two measurements.