(ns chapter1.BonusMaterial)

; Heron's formula:
; If approx is an approximation for sqrt(x),
; then (* 0.5 (+ approx ( / x approx))
; is an even better approximation for sqrt(x)
; (for positive x, and positive approx)
(defn heron [x approx] (* 0.5 (+ approx ( / x approx))))

; Little known fact:
; In theory, if one keeps re-applying Heron's formula,
; the values will become lower and lower.
; They approach sqrt(x) from above.
; Only the very first value, the start value,
; could potentially be below sqrt(x) - all else
; will be above that.

; But we are in finite-precision arithmetic here.

; So the values cannot become lower and lower forever
; and still stay above sqrt(x).

; When the calculated approximation value remains equal,
; or rises ever so slightly,
; compared to the previous approximation value,
; that is the precise moment when the maximal precision
; has been reached
; and further approximations will no longer yield
; better precision.
;
; So this is an excellent criterion when to stop.
;
(defn keep_improving_until_precision_is_reached
  ([x approx better_approx]
    (if (< better_approx approx)
      (recur x better_approx (heron x better_approx))
      better_approx))
  ([x approx]
    (keep_improving_until_precision_is_reached x approx (heron x approx))))

; To make this work, one needs a start value
; that is certainly higher than sqrt(x).

; For starters, I brutally used something slightly above
; Math.sqrt(Double.MAX_VALUE)

; (defn upper_start_value [x] 1.35E154)

; This gives precise results, but performance is far from ideal.

; Here is a more complicated implementation.

; First I need a power function.

; I could have used the one from Math,
; (defn power [x n] (Math/pow x n))
; but, for the fun of it, here is the pedestrian way:

(defn power [x n]
  (cond
    (< n 0) (recur (/ 1.0 x) (- n))
    (= n 0) 1.0
    (= 1 (rem n 2)) (* x (power (* x x) (quot n 2)))
    true (recur (* x x) (quot n 2))))

; To find some approximate start value
; that is higher than the true value,
; first use the bit representation of the number
; to quickly find some reasonable initial value,
; then use one iteration of Heron's formula
; to improve on that initial value
; and (more importantly here) make sure
; the improvement is above the true value.
(defn upper_start_value [x]
  (heron x (power 1.4142 (Math/getExponent x))))

(defn mysqrt [x] (keep_improving_until_precision_is_reached x (upper_start_value x)))

; Alternatively, call the standard Java implementation
; just to see the test works.
;
; Incidently, all calls take a total of 1.0 seconds on my machine.
; Omitting Clojure and just calling Math.sqrt directly takes
; almost precisely the same, if I still load this script.
;
; Omitting the script loading reduces the time to 0.4 seconds.
; (defn mysqrt [x] (Math/sqrt x ))
; That implementation is more precise than mine.
; They probably use higher precision available inside the CPU's FPU
; than is available in the programming environment.
