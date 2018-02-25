; Author: Srizan Gangol CSCE4430
; 1b Perfect: determines if an integer is perfect and if so, return it's list of factors,
; otherwise return nil


(defun perfect (x)
	(prog (xfactors)
		(cond
			((= (mod x 2) 0) (setq xfactors (factors x (/ x 2))))
			(t (setq xfactors (factors x (/ (- x 1) 2))))
		)
		(cond 
			((= x (sumlist xfactors)) (return xfactors))
			(t nil)
		)
	)
)

; function (mod x y)

(defun factors (x y)
	(cond
		((= y 1) (list 1))
		((= (mod x y) 0) (append (factors x (- y 1)) (list y)))
		(t (factors x (- y 1)))
	)
)

; sumlist computes the sum of a list of numbers.

(defun sumlist (numbers)
	(eval (cons '+ numbers))
)
