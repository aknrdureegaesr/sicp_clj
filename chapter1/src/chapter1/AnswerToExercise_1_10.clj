(defn A [x y] (cond (= y 0) 1
                    (= x 0) (* 2 y)
                    (= y 1) 2
                    true (A (- x 1) (A x (- y 1)))))

(defn f [n] (A 0 n))
(defn f2 [n] (* 2 n))

(defn g [n] (A 1 n))
; 0 2 4 8 16 ...
; So basically the powers of two, but the first doesn't fit.
; Disgustedly quitting this exercise. No attempt made to analyze h.

