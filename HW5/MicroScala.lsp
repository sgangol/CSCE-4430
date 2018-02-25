(defun programSem (program) 
   (statementSem program '(lambda (id) (cond (t 'undefined))))
;  (with-open-file (str "output.txt"
;                     :direction :output
;                     :if-exists :supersede
;                     :if-does-not-exist :create)
;  (princ "Output saved to output.txt ")
;  (format str "write anything ~%"))
)

(defun statementSem (statement store)
  (cond
    ((null statement) store)
    ((eq (car statement) ':) (prog (statement1 statement2)
      (setq statement1 (cadr statement))
      (setq statement2 (caddr statement))
      (return (statementSem statement2 (statementSem statement1 store)))))
    ((eq (car statement) '=) (prog (variableRef expression value)
      (setq variableRef (cadr statement))
      (setq expression (caddr statement))
      (return 
        (update store (variableRefSem variableRef) 
          (expressionSem expression store)))))
    ((eq (car statement) 'if) (prog (expression statement1 statement2)
      (setq expression (cadr statement))
      (setq statement1 (caddr statement))
      (setq statement2 (cadddr statement))
      (cond
        ((= (expressionSem expression store) 1)
          (return (statementSem statement1 store)))
        (t (return (statementSem statement2 store))))
      ))
    ((eq (car statement) 'while) (prog (expression statement1)
      (setq expression (cadr statement))
      (setq statement1 (caddr statement))
      (cond
        ((= (expressionSem expression store) 1)
          (return (statementSem statement (statementSem statement1 store))))
        (t (return store)))))
  )
)

(defun expressionSem (expression store) (prog (op expression1 expression2 id intValue)
  (setq op (car expression))
  (setq expression1 (cadr expression))
  (setq expression2 (caddr expression))
  (cond
    ((eq op '==) 
      (return 
        (= (expressionSem expression1 store) (expressionSem expression2 store))))
    ((eq op '!=) 
      (return (not 
        (= (expressionSem expression1 store) (expressionSem expression2 store)))))
    ((or (eq op '<) (eq op '<=) (eq op '>=) (eq op '>))
      (return 
        (cond
          ((apply op (list (expressionSem expression1 store) 
            (expressionSem expression2 store))) 1)
          (t 0)
        )
      )
    )
    ((or (eq op '+) (eq op '-) (eq op '*))
      (return 
        (apply op (list (expressionSem expression1 store) 
          (expressionSem expression2 store)))))
    ((eq op '/)
      (return 
        (truncate (/ (expressionSem expression1 store) 
          (expressionSem expression2 store)))))
    ((eq op '%)
      (return 
        (mod (expressionSem expression1 store) 
          (expressionSem expression2 store))))
    ((eq op 'id) 
      (setq id expression1)
      (setq intValue (access store id))
      (cond
        ((eq intValue 'undefined) (return 'error))
        (t (return intValue))))
;    )
    ((eq op 'intValue) (return expression1))
  )
))

(defun variableRefSem (variableRef) (cadr variableRef))

(defun access (store id) (apply store (list id)))

(defun update (store id n) (prog (currentenv newbinding newstore)
  (setq currentenv (cdar (cddr store)))
  (setq newbinding (cons (list 'eq 'id (list 'quote id)) (list N)))
  (setq newstore
    (list 'lambda (list 'id) (cons 'cond (cons newbinding currentenv))))
  (return newstore)))

