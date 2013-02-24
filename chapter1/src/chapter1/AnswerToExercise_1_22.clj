(defn prime? [a]
  (= a
  ((fn [test-divisor]
     (cond (< a (* test-divisor test-divisor)) a
           (= 0 (rem a test-divisor)) test-divisor
           true (recur (inc test-divisor))))
    2)))

(defn duration [f]
  (let [start (System/nanoTime)
        result (f)
        finish (System/nanoTime)]
    [ result (* 1e-9 (- finish start))]))

(defn find-lowest-prime-and-measure-duration [x]
  (let [[is-prime dur] (duration (fn [] (prime? x)))]
    (if is-prime [x dur] (recur (inc x)))))

(def measurements (map find-lowest-prime-and-measure-duration
     (let [max_search_start (/ (Long/MAX_VALUE) 1000)]
       ((fn search_starts[start]
          (if (< start max_search_start)
            (lazy-seq (cons start (search_starts (* 10 start))))
            nil)) 10))))

;; Use a logarithmic scale, adjusted such that
;; the number should increase by 1 from one pair to the next.
(map (fn [v] [(v 0) (/ (Math/log (v 1)) (/ (Math/log 10) 2))]) measurements)

;; Results seen:
;;
;;  (             [11 -9.678282938640015] ; Forget about this one
;;    
;;               [101 -9.101845821473367] ; In this range, 
;;              [1009 -8.133025424302588] ; performance behaves pretty much as expected.
;;             [10007 -7.093926250062645]
;;            [100003 -6.066664535924919]
;;           [1000003 -4.9819542974160305]
;;           
;;           
;;          [10000019 -4.482590785408046]  ; In this middle range,
;;         [100000007 -3.7813377446418728] ; from one line to the next pretty much the same
;;        [1000000007 -4.093334649548482]  ; amount of time is taken - across 4 orders of magnitude!
;;       [10000000019 -4.479374922565967]  ; I think I witness Java's JIT-compiler at work here.
;;
;;      [100000000003 -3.30821136617993]    ; In this range,
;;     [1000000000039 -2.380850971876542]   ; performance again behaves mostly as expected.
;;    [10000000000037 -1.4553412407029054]  ; Looking more closely, things become slightly slower
;;   [100000000000031 -0.5026559933244604]  ; than would be expected. 
;;  [1000000000000037  0.4703238287691126]) ; Possibly, a Long operation takes longer 
;;                                          ; if more bits are non-zero?
