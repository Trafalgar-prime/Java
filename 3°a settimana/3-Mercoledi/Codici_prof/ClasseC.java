package md.corsojava;

public class ClasseC {

  public static void metodo1c() {
      
      ClasseB obj_classeb4 = new ClasseB();
      
      System.out.println("ClasseC metodo1c");
      System.out.println("*                        obj_classeb4.var_static:" + obj_classeb4.var_static + "<");                 
      System.out.println("*                             ClasseB.var_static:" + ClasseB.var_static + "<");                 
      System.out.println("*                    obj_classeb4.var_NON_static:" + obj_classeb4.var_NON_static + "<");                 
      System.out.println("FINE ClasseC metodo1c");
      System.out.println("*");
  }
}