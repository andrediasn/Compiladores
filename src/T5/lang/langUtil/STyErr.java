/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.langUtil;

public class STyErr extends SType {
     
      private static STyErr st = new STyErr();
     
     private STyErr(){}
     
     public static STyErr newSTyErr(){ 
          return st; 
     }
     
     public boolean match(SType v){
          return true;
     }
     
     public String toString(){
         return "TyError";
     }
}
