(ns chapter1.Answers)

(defn sos [x, y] (+ (* x x) (* y y)))

(defn ex1_3 [^Integer a, ^Integer b, ^Integer c]
  (cond 
    (and (<= a b) (<= a c)) (sos b c)
    (and (<= b a) (<= b c)) (sos a c)
    true (sos a b)))
       