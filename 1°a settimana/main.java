public class main 
{
    public static void main(String[] args){
        float numeroFloat = 43.56f;
        int numero = 17;
        char variabile = 'A'; // se metto più di un carattere mi da errore
        double numero_double = 56.798;
        String testo = "Stampa qualcosa:";
        System.out.println("Stampa qualcosa:" + variabile);
        System.out.println("Stampa qualcosa:" + numero);
        System.out.println("Stampa qualcosa:" + numeroFloat);
        System.out.println("Stampa qualcosa:" + numero_double);
        System.out.println("Stampa qualcosa:" + testo);
        /** 
        char variabile_1 = 'A'; // se metto più di un carattere mi da errore
        double numero_double_1 = 56.798;
        */
       int nome_array [];
       nome_array = new int[12];
       int[] name_array = {31,28,31,30,31,30,31,31,30,31,30,31};
       name_array[0] = 62;
       for (int i = 0; i < 12; i ++){
        System.out.println(name_array[i] + "\n");
       }
       System.out.print("Lunghezza dell'array: " + name_array.length + "\n");
       float[] valori = {1.2f, 3.4f, 5.6f};
       int nome_array_1 [] = new int[12];
       System.out.print("Valore: " + valori[0] + "\n");
       //numeri = {1, 2, 3};   // ❌ ERRORE
       //numeri = new int[]{1, 2, 3}; // ❌ ERRORE deve essere già dichiarato numeri
       System.out.print("-------------------------------");
       System.out.print("--------------ESERCIZIO---------");
       System.out.print("-------------------------------");

       /*String [] miChiamo;
       miChiamo = {"Lorenzo" , "Tabolacci"};*/

       char [] mi_chiamo;
       mi_chiamo = new char []{'L', 'o', 'r', 'e', 'n', 'z', 'o'};
       System.out.println("\nMi chiamo :  " + mi_chiamo[0]);
       System.out.println("Mi chiamo :  " + mi_chiamo[1]);
       System.out.println("Mi chiamo :  " + mi_chiamo[2]);
       System.out.println("Mi chiamo :  " + mi_chiamo[3]);
       System.out.println("Mi chiamo :  " + mi_chiamo[4]);
       System.out.println("Mi chiamo :  " + mi_chiamo[5]);
       System.out.println("Mi chiamo :  " + mi_chiamo[6]);


       System.out.print("Stampo il mio nome: ");
       for (int i = 0 ; i < mi_chiamo.length ; i++ ){
        System.out.print(mi_chiamo[i]);
       }
       System.out.print("\n");

       char []nome;
       nome = new char[]{'M','i','c','h','i','a','m','o','L','o','r','e','n','z','o'};

       System.out.println("\n" +  nome[0] + nome[1] + " " + nome[2] + nome[3] + nome[4] + nome[5] + nome[6] + nome[7]  + " " + nome[8]  + nome[9] + nome[10] + nome[11] + nome[12] + nome[13] + nome[14]);

       String numero2 = "2";
       System.out.print(numero2 + "\n");

       System.out.print(1785 % 17 + "\n");
       int var_test = 1786;
       var_test %= 17;  // come fare risultato = risultato % 17;
       System.out.print("Risultato: " + var_test + "\n");
       
       int x = 1, y = 5 , z= 10;
       boolean varTest = x > y && x++ < z; // con il doppio && se trova un false il codice si ferma e non va avanti, quindi un'operazione successiva non la legge
       System.out.println("Stampo varTest: " + varTest + "\n");
       System.out.println("Stampo x1: " + x + "\n");

       boolean var_Test = x > y & x++ < z;  // con il singolo & anche se trova un false il codice prosegue lo stesso
       System.out.println("Stampo var_Test: " + var_Test + "\n");
       System.out.println("Stampo x0: " + x + "\n");

       boolean variabile_test = ! (y > x);
       System.out.print(" Provo il not ! : " + variabile_test + "\n");

       boolean variabile_Test = ! (x > y);
       System.out.print(" Provo il not ! : " + variabile_Test + "\n");





    }

}