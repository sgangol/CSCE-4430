; Author: Srizan Gangol CSCE4430
; 1c.lsp: A Lisp function (delete atom list) that returns the list with all occurrences,
;  no matter how deep, of atom deleted. The returned list cannot contain anything in place
; of the deleted atoms. For example,


(defun delete (atom list)
	(cond
		((null list) nil)
		((atom (car list))
			(cond
				((eq (car list) atom) (delete atom (cdr list)))
				(t (cons (car list) (delete atom (cdr list))))
			)
		)
		(t (cons (delete atom (car list)) (delete atom (cdr list))))
	)
)
