(defn smallest-divisor [a]
  ((fn [test-divisor]
     (cond (< a (* test-divisor test-divisor)) a
           (= 0 (rem a test-divisor)) test-divisor
           true (recur (inc test-divisor))))
    2))

(defn show-smallest-factors [vec]
  (dorun (map prn (map (fn [x] (list x ":" (smallest-divisor x))) vec))))

(show-smallest-factors [199 1999 19999])

(show-smallest-factors [561 1105 1729 2465 2821 6601])

(defn factorize [a]
  ((fn [unfactorized-part factors]
     (let [new-factor (smallest-divisor unfactorized-part)
           new-unfactorized-part (/ unfactorized-part new-factor)
           new-factors (concat factors (list new-factor))]
       (if (= 1 new-unfactorized-part) new-factors
         (recur new-unfactorized-part new-factors))))
    a `()))

(defn show-factorizations [vec]
  (dorun (map prn (map (fn [x] (list x ":" (factorize x))) vec))))

(show-factorizations [199 1999 19999])

(show-factorizations [561 1105 1729 2465 2821 6601])