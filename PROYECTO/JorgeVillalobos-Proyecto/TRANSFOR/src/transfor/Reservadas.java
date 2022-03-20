/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfor;

/**
 *
 * @author Jorge
 */
public class Reservadas {
    
    public String [] palabrasTransfor = {"PROGRAM","DOUBLE","PRINT","READ","IF","THEN","STOP","GOTO","ENDIF","DO","ENDDO","ENDPROGRAM"};
    
    public boolean esReservadaTransfor (String palabraAnalizar) {
        boolean palabraEncontrada = false;
        for (String palabra : palabrasTransfor) {//Guardar en palabra cada palabra del vector palabrasTransfor
            if (palabra.equalsIgnoreCase(palabraAnalizar)) {
                //Si palabra es igual a la palabra que recibe por referencia, es reserveda
                palabraEncontrada = true;//Si se cumple que retorne verdadero
            }
        }
        return palabraEncontrada;//Devuele el valor en true, si se cumplio la condición.
    }
    
    public String [] tiposDatos = {"INTEGER","REAL","CHARACTER"};
    
    public boolean esTiposDatos(String palabraAnalizar) {
        boolean palabraEncontrada = false;
        for (String palabra : tiposDatos) {
            if (palabraAnalizar.equalsIgnoreCase(palabra)) {
                palabraEncontrada = true;
            }
        }
        return palabraEncontrada;
    }
    
    public String [] palabrasFortran = {"ABORT","ABS","ACCESS","ACHAR","ACOS","ACOSD","ACOSH","ACTION","ADJUSTL","ADJUSTR","ADVANCE","AIMAG","AINT","ALARM","ALL","ALLOCATABLE","ALLOCATE","ALLOCATED","AND","ANINT","ANY","ASIN","ASIND","ASINH","ASSIGN",
        "ASSIGNMENT","ASSOCIATED","ATAN","ATAN2","ATAN2D","ATAND","ATANH","ATOMIC_ADD","ATOMIC_AND","ATOMIC_CAS","ATOMIC_DEFINE","ATOMIC_FETCH_ADD","ATOMIC_FETCH_AND","ATOMIC_FETCH_OR","ATOMIC_FETCH_XOR","ATOMIC_OR","ATOMIC_REF","ATOMIC_XOR", 
        "BACKSPACE","BACKTRACE","BESSEL_J0","BESSEL_J1","BESSEL_JN","BESSEL_Y0","BESSEL_Y1","BESSEL_YN","BGE","BGT","BIT_SIZE","BLANK","BLE","BLOCK","BLT","BTEST",
        "CALL","CASE","CEILING","CHAR","CHDIR","CHMOD","CLOSE","CMPLX","COMMAND_ARGUMENT_COUNT","COMMON","COMPILER_OPTIONS","COMPILER_VERSION","COMPLEX","CONJG","CONTAINS","CONTINUE","COS","COSD","COSH", 
        "COTAN","COTAND","COUNT","CO_BROADCAST","CO_MAX","CO_MIN","CO_REDUCE","CO_SUM","CPU_TIME","CSHIFT","CTIME","CYCLE","C_ASSOCIATED","C_FUNLOC","C_F_POINTER","C_F_PROCPOINTER","C_LOC","C_SIZEOF",
        "DATA","DATE_AND_TIME","DBLE","DCMPLX","DEALLOCATE","DEFAULT","DELIM","DIGITS","DIM","DIMENSION","DINQUIRE","DIRECT","DOT_PRODUCT","DPROD","DREAL","DSHIFTL","DSHIFTR","DTIME",
        "ELSE","ELSEWHERE","END","ENDFILE","ENTRY","EOR","EOSHIFT","EPSILON","EQUIVALENCE","ERF","ERFC","ERFC_SCALED","ERR","ETIME","EVENT_QUERY","EXECUTE_COMMAND_LINE","EXIST","EXIT","EXP","EXPONENT","EXTENDS_TYPE_OF","EXTERNAL",
        "FDATE", "FGET", "FGETC", "FILE", "FINDLOC", "FLOOR", "FLUSH", "FMT", "FNUM", "FORM", "FORMAT", 
        "FORMATTED", "FPUT", "FPUTC", "FRACTION", "FREE", "FSEEK", "FSTAT", "FTELL", "FUNCTION", "GAMMA", "GERROR",
        "GETARG", "GETCWD", "GETENV", "GETGID", "GETLOG", "GETPID", "GETUID", "GET_COMMAND", "GET_COMMAND_ARGUMENT",
        "GET_ENVIRONMENT_VARIABLE", "GMTIME", "GO", "HOSTNM", "HUGE", "HYPOT", "IACHAR", "IALL", "IAND","IANY",
        "IARGC", "IBCLR", "IBITS", "IBSET", "ICHAR", "IDATE", "IEOR", "IERRNO", "IMAGE_INDEX", "IMPLICIT", "IN",
        "INDEX", "INOUT", "INQUIRE", "INT", "INT2", "INT8", "INTENT", "INTERFACE", "INTRINSIC", "IOLENGTH",
        "IOR", "IPARITY", "IRAND", "ISATTY", "ISHFT", "ISHFTC", "ISNAN", "IS_CONTIGUOUS", "IS_IOSTAT_END", "IS_IOSTAT_EOR",
        "ITIME", "KILL", "KIND", "LBOUND", "LCOBOUND", "LEADZ", "LEN", "LEN_TRIM", "LGE", "LGT", "LINK", "LLE", "LLT",
        "LNBLNK", "LOC", "LOG", "LOG10", "LOGICAL", "LOG_GAMMA", "LONG", "LSHIFT", "LSTAT", "LTIME", "MALLOC", "MASKL",
        "MASKR", "MATMUL", "MAX", "MAXEXPONENT", "MAXLOC", "MAXVAL", "MCLOCK", "MCLOCK8", "MERGE", "MERGE_BITS", "MIN",
        "MINEXPONENT", "MINLOC", "MINVAL", "MOD", "MODULE", "MODULO", "MOVE_ALLOC", "MVBITS", "NAME", "NAMED", "NAMELIST",
        "NEAREST", "NEW_LINE", "NEXTREC", "NINT", "NML", "NONE", "NORM2", "NOT", "NULL", "NULLIFY", "NUMBER", "NUM_IMAGES",
        "ONLY", "OPEN", "OPENED", "OPERATOR", "OPTIONAL", "OR", "OUT", "PACK", "PAD", "PARAMETER", "PARITY", "PAUSE",
        "PERROR", "POINTER", "POPCNT", "POPPAR", "POSITION","PRESICION", "PRESENT", "PRIVATE", "PROCEDURE",
        "PRODUCT", "PUBLIC", "RADIX", "RAN", "RAND", "RANDOM_INIT", "RANDOM_NUMBER", "RANDOM_SEED", "RANGE",
        "RANK", "READWRITE", "REC", "RECURSIVE", "RECl", "RENAME", "REPEAT", "RESHAPE", "RESULT", "RETURN",
        "REWIND", "RRSPACING", "RSHIFT", "SAME_TYPE_AS", "SAVE", "SCALE", "SCAN", "SECNDS", "SECOND", "SELECT",
        "SELECTED_CHAR_KIND", "SELECTED_INT_KIND", "SELECTED_REAL_KIND", "SEQUENCE", "SEQUENTIAL", "SEQUENTIAl", 
        "SET_EXPONENT", "SHAPE", "SHIFTA", "SHIFTL", "SHIFTR", "SIGN", "SIGNAL", "SIN", "SIND", "SINH", "SIZE", "SIZEOF",
        "SLEEP", "SPACING", "SPREAD", "SQRT", "SRAND", "STAT", "STATUS", "STORAGE_SIZE", "SUBROUTINE", "SUM",
        "SYMLNK", "SYSTEM", "SYSTEM_CLOCK",
        "TAN","TAND","TANH","TARGET","THIS_IMAGE","TIME","TIME8","TINY","TO","TRAILZ","TRANSFER","TRANSPOSE","TRIM","TTYNAM","TYPE","UBOUND","UCOBOUND","UMASK","UNFORMATTED","UNIT","UNLINK","UNPACK","USE","VERIFY","WHERE","WHILE","WRITE","XOR"};

    public boolean esReservadaFortran(String palabraAnalizar) {
        boolean palabraEncontrada = false;
        for (String palabra : palabrasFortran) {
            if (palabraAnalizar.equalsIgnoreCase(palabra)) {
               //Si la expresion en el token cero es iguag alguna de las palabrasanalizar
                palabraEncontrada = true;//Si se cumple que retorne verdadero
            }
        }
        return palabraEncontrada;
    }
}