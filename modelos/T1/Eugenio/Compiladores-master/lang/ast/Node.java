/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;


public abstract class Node extends SuperNode{
      
      protected int line,col;
      
      public Node(int l, int c){
           line = l;
           col = c;
      }
      
      public int getLine(){
            return line;
      }

      public int getColumn(){
            return col;
      }
}
