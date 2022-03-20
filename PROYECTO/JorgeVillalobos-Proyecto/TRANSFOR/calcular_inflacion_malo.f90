      PROGRAM CALCULAR_INFLACION
                            
      !***********************************************************************
      !* Asumiendo tasas de inflacion anual de 7%, 8%, y 10%,                *
      !* encontrar el FACTOR por el cual cualquier moneda, tales como        *
      !* el franco, el dolar, la libra esterlina, el marco, el rublo, el yen *
      !* o el florin han sido devaluadas en 1, 2, ...., N anos.              *
      !***********************************************************************
                            
      INTEGER  MAX_ANOS121234567890012345678999, ANO
      REAL      FACTOR1, FACTOR2, FACTOR3
      CHARACTER NOSEUSA
      DOUBLE PRECISION SUMA_NO_USADA
                            
      ! Inicio del programa CALCULAR_INFLACION
                            
      ! Entrada de datos
10000      PRINT*
10060      PRINT*
10080      PRINT*, 'POR FAVOR, indique la cantidad maxima de anos:'
10050      READ*, MAX_ANOS
                            
      IF ( MAX_ANOS . LE. 0 ) THEN
         STOP 
         GOTO 999999
      ENDIF
      ! Inicializacion de variables
                            
      ANO     = 0
      FACTOR1 = 1.0
      FACTOR2 = 1.0
      FACTOR3 = 1.0
                            
      ! Calculos y salida de datos
                            
      PRINT*
      PRINT*, '        ANO   7%               8%              10%'
      PRINT*
      DO ANO = FACTOR1, MAX_ANOS
        FACTOR1 = ( FACTOR1 *                                           &
     &                    1.07 )
        FACTOR2 = ( FACTOR2 ) * 1.08
        FACTOR3 = FACTOR3 * ( 1.10 )
        PRINT*, ANO, FACTOR1, FACTOR2, FACTOR3
      ENDDO
      PRINT*
      STOP '*** FIN DEL PROGRAMA NORMAL ***
      PRINT*
99999 ENDPROGRAM
                            
      ! Fin del programa CALCULAR_INFLACION
