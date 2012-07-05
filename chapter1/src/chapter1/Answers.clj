(ns chapter1.Answers)
(defn hello [who] (str "Hello " who "!"))
(defn abs1 [x]
  (cond (< x 0) (- x)
        true x ))
(defn abs2 [x]
  (if (< x 0) (- x) x))

(defn p [] (p))
(defn testi [x, y]
  (if (= x 0)
    0
    y))

(defn a-plus-abs-b [a, b] 
  ((if (< 0 b) + -) a b))

(defn sos [x, y] (+ (* x x) (* y y)))

(defn ex1_4 [^Integer a, ^Integer b, ^Integer c]
  (cond 
    (and (<= a b) (<= a c)) (sos b c)
    (and (<= b a) (<= b c)) (sos a c)
    true (sos a b)))
       