(ns chapter1.BonusMaterial)

; Call the standard Java implementation just to see the test works.
;
; Incidently, all calls take a total of 1.0 seconds on my machine.
; Omitting Clojure and just calling Math.sqrt directly takes
; almost precisely the same, if I still load this script.
;
; Omitting the script loading reduces the time to 0.4 seconds.
(defn mysqrt [x] (Math/sqrt x ))