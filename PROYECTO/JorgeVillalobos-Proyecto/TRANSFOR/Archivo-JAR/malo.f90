      !Tarea 1. El identificador del program es incorrecto no cumple con las regla del nombre
      PROGRAM 1CALCULAR_INFLACION 
      !! Tarea 1. definicion de comentario 
      !***********************************************************************
      !* Asumiendo tasas de inflacion anual de 7%, 8%, y 10%,                *
      INTEGER   MAX_ANOS, ANO
      REAL      FACTOR1, FACTOR2, FACTOR3
 ! declaracion de variables
      CHARACTER NOSEUSA
      DOUBLE PRECISION SUMA_NO_USADA
      !Tarea 1. No deberia dar error porque es una palabra reservada de FORTRAN
	  LOGICAL X 
	  !Tarea 1. Error: No tiene tipo de dato
	  TOTAL 
      
10000      PRINT*
10060      PRINT*
      !!Tarea 1. Error: hay error 112 lineas  
10080      PRINT*, 'POR FAVOR, indique la cantidad maxima de anos que desea que se calculen con este programa:'   
       
      !Tarea 2.Error: al siguiente linea le falta la coma debe dar error
10050      READ* MAX_ANOS
     
      IF ( MAX_ANOS .LE. 0 ) THEN 
         STOP '*** FIN DEL PROGRAMA PORQUE INDICO 0 ANOS ***'
         GOTO 99999
      ENDIF
        
      !Proyecto. Error falta un parentesis y la palabra THEN pt6
      IF ( MAX_ANOS .LE. 0
        
          PRINT*
      !Proyecto. Error no hay una linea de comando luego del ELSE
      ELSE	  
      ENDIF
        	  
      !Tarea 1. Error: la variable esta en una posicion incorrecta
ANO     = 0
      FACTOR1 = 1.0
      FACTOR2 = 1.0
      FACTOR3 = 1.0
      !Tarea 2. Error:La siguiente linea no debe dar error porque es un comando de FORTRAN
      READ 
      !Proyecto. Error porque la linea no tiene fin de comilla pt4
      STOP 'DETENER LA LECTURA
      PRINT*
      PRINT*, '        ANO   7%               8%              10%'
      PRINT*
      DO ANO = 1, MAX_ANOS
       !Tarea 1. Error: falta el simbo ampersam en multilinea
        FACTOR1 = ( FACTOR1 *                                          &
                         1.07 )
        FACTOR2 = ( FACTOR2 ) * 1.08
        FACTOR3 = FACTOR3 * ( 1.10 )
        PRINT*, ANO, FACTOR1, FACTOR2, FACTOR3
      ENDDO
      	  
      !Proyecto. Error la variable limite no existe pt5
      DO ANO = 1, LIMITE
        PRINT*	  
      ENDDO
      		
      !Proyecto. Error no existe la etiqueta 15000 pt3
      GOTO 15000
      !Tarea 2. Validación: La siguiente linea no debe dar error porque es un comando de FORTRAN
      PRINT
15000 STOP '*** FIN DEL PROGRAMA NORMAL ***'
      PRINT*
      !Tarea 2. Error: la siguiente linea debe dar error porque la variable de la derecha no existe
      TOTAL = SALARIO
      !Tarea 2. Error: La siguiente linea tiene error por la ubicacion de la etiqueta
    99999 ENDPROGRAM
      	
     ! Tarea 2. Error: este endprogram sale sobrando debe dar error.
      ENDPROGRAM
      	  
