package md.corsojava;

import java.util.*;

//import java.util.Scanner;

class IntWrap {
    
    void cast_mode() {
       
        double var_num_double = 3.699;
        //float var_num_float = 42.129f;
           int var_num_int;
        //char var_char = 'a';
        
        //var_num_int = var_num_double;
        //var_num_int = (int) var_num_double;
        //var_num_int = (int) var_num_float;
        
        //int var_num_int2 = (int) '2';
        //int var_num_int2 = (int) var_char;
        //int var_num_int2 = (int) "23";
         
        System.out.println("cast_mode");
        //System.out.println("*     Valore di var_num_double è:" + var_num_double + "<");
        //System.out.println("* Valore di var_num_float:" + var_num_float + "<");
        //System.out.println("* Valore di var_num_int:" + var_num_int + "<");
        //System.out.println("* Valore di var_num_int2:" + var_num_int2 + "<");
        System.out.println("*******************");
        
        //float var_num_float = (float) var_num_double;
        //long var_num_long = (long) var_num_double;
        //byte var_num_byte = (byte) var_num_double;
    }
    
    void double_to_int_fisso () {
        
           int var_numero_intero;
        Double var_numDouble = 88.9903123;
        
        //var_numero_intero = var_numDouble;
        var_numero_intero = var_numDouble.intValue();
        
        System.out.println("double_to_int_fisso");
        System.out.println("*     Valore di var_numDouble:" + var_numDouble + "<");
        System.out.println("* Valore di var_numero_intero:" + var_numero_intero + "<");
        System.out.println("*******************");
        
    }
    
    void dblstr_to_int () {
        
        String var_input = Richiedi_Input();
                        
        double var_numero_double = Double.parseDouble(var_input);
        
        //int var_numero_intero;
        //var_numero_intero = (int) var_numero_double;
                
        var_numero_double = var_numero_double * 2;
        
        System.out.println("double_to_int_dinamico");
        System.out.println("*         Valore di var_input:" + var_input + "<");
        //System.out.println("* Valore di var_numero_intero:" + var_numero_intero + "<");
        System.out.println("*      Valore di var_numero_double:" + var_numero_double + "<");
        System.out.println("*********************");

    }
        
    void str_to_int (){
        
        String var_input = Richiedi_Input();
        Integer var_numero = Integer.valueOf(var_input); //restituisce un oggetto, in questo caso la classe è integer
        int var_numero_int = Integer.parseInt("33"); //restituisce una primitiva cioè un int in questo caso
        
        int var_provo_numero = var_numero * 2;
        //int var_provo_numero = var_numero_int * 2;
        
        System.out.println("str_to_int");
        System.out.println("*         Valore di var_input:" + var_input + "<");
        System.out.println("*        Valore di var_numero:" + var_numero + "<");
        System.out.println("*     Valore di var_numero_int:" + var_numero_int + "<");
        System.out.println("*      Valore di provo_numero:" + var_provo_numero + "<");
        System.out.println("*********************");

    }
    
    private String Richiedi_Input () {
        
        Scanner obj_input = new Scanner(System.in);
        
        System.out.println("Si prega di inserire un numero:");

        return obj_input.nextLine();

    }
    
    
    private void Cosa_Provare () {
        // classi wrapper
        Character var_character;
          Integer var_integer;
           String var_string;
           Double var_double;
            Float var_float;
          Boolean var_boolean;            
           Number var_number;                       
    }
    
}

public class EsempiWrapper {

    public static void main(String[] args) {
        
        IntWrap mio_int_wrap = new IntWrap();
          
        //mio_int_wrap.cast_mode();
        
        //mio_int_wrap.double_to_int_fisso();
                
        //mio_int_wrap.dblstr_to_int();
        
        //mio_int_wrap.str_to_int();
        
        formatta_numero();
        
    }
    
    static void formatta_numero (){
        
        //Locale.setDefault(Locale.ITALY); //-> da spiegare!
               
        double importo = 11.258;
                
        //int imp_intero = 11000;
        
        String str_importo;
        
        //str_importo = String.format("%10.2f", importo);
                
        //System.out.println("L'importo formattato è:" + str_importo + "<");
        
        System.out.println("Altra modalità:" + String.format("%-16.3f", importo) + "<");
        
        System.out.println("L'importo nella variabile è:" + importo + "<");
        
    }
}