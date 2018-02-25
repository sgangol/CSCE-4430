; Author: Srizan Gangol CSCE4430
; 1a.lsp: Convert a list of hexadecimal digits into the equivalent decimal number
; Define Hexadecimal

(defun hexdigittodec (hexdigit)
	(cond
		((eq hexdigit 'a) 10)
		((eq hexdigit 'b) 11)
		((eq hexdigit 'c) 12)
		((eq hexdigit 'd) 13)
		((eq hexdigit 'e) 14)
		((eq hexdigit 'f) 15)
		((and (>= hexdigit 0) (<= hexdigit 9)) hexdigit)
	)
)

; Hex to Digit converter

(defun hextodec (hexdigits)
	(cond
		((null (cdr hexdigits)) (hexdigittodec (car hexdigits)))
		(t (+ (* (hexdigittodec (car hexdigits)) 
			(expt 16 (- (length hexdigits) 1)))
			
			(hextodec (cdr hexdigits))))
	)
)
