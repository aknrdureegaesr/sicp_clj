(ns chapter1.Answers)

(defn sos [^Long x, ^Long y] (+ (* x x) (* y y)))

(defn ex1_3 [^Long a, ^Long b, ^Long c]
  (cond 
    (and (<= a b) (<= a c)) (sos b c)
    (and (<= b a) (<= b c)) (sos a c)
    true (sos a b)))
       