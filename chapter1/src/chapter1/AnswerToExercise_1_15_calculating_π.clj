;;; a) Four times.
;;; b) It grows with the logarithm of a.
;;
;;; And now let's have some fun and calculate π !
;;
;; Well known mathematical background:
;;
;; Start with a value of 3 (or any positive number
;; less than 2π) as your initial guess g_0 for π.
;;
;; To obtain the new guess g_(n+1), simply add
;; sin(g_n) of the previous guess g_n to g_n.
;;
;; This produces a sequence that approximates π.
;;
;; Quite rapidly indeed:
;; When the guess is already quite close,
;; so it the first N digits are correct already,
;; adding sin will result in a value
;; that has the first 3*N digits correct
;; (approximately; the higher N is to start with, the better).
;;
;; When the initial guess is anything positive below π,
;; all other guesses will be below π as well.
;;
;; When the inital guess is anywhere
;; between π and strictly smaller than 2π,
;; all other guesses will be above π as well.
;;
;; This is not a very practical method to calculate π,
;; as it is computationally rather expensive to
;; compute sin(x) if x is near π .
;;
;; While it may be expensive, as exercise 1.15 shows,
;; it can be done (without prior knowledge of π).
;;
;; Exercise 1.15 gives us a sequence of approximative
;; functions sin_n, that approximate sin.
;;
;; One could try something like the straightforward iteration
;;
;; h_0 = 3
;; h_(n+1) = h_n + sin_n(h_n)
;;
;; This does not work very well at first,
;; as sin_0(3) = 3, so h_1 comes out as 6.
;;
;; So let us instead use
;;
;; h_(n+a) = h_n + sin_(n+3)(h_n).

(defn sin_n[n x]
  "n-th approximation of sin"
  (defn div_x_3**n [x three**k n-k] ; first multiply 3**n, I think this gives better precision.
    (if (= n-k 0) (/ x three**k)
      (recur x (* three**k 3) (- n-k 1))))
  (defn repeat_p_n_times [x n]
    (if (= n 0) x
      (recur (- (* 3 x) (* x x x 4)) (- n 1))))
  (repeat_p_n_times (div_x_3**n x 1.0 n) n))

(defn guesses_for_π []
  "generate an infinite sequence of guesses for π"
  (defn h_tail [h_n n+3]
    (lazy-seq (cons h_n (h_tail (+ h_n (sin_n (+ n+3 1) h_n)) (+ 1 n+3)))))
  (h_tail 3.0 3))

(defn prn_first_n_guesses [n]
  "prn the first n guesses for π."
  (
    (fn show [n s ignore]
      (if (seq s) (recur (+ n 1) (rest s) (prn n (first s)))))
    0 (take n (guesses_for_π)) nil))

