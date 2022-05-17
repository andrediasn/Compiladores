/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/
package lang.ast;

import lang.visitor.Visitor;

public class Not extends Exp {

		private int line, column;
		private Exp expression;


		public Not(int line, int column, Exp expression) {
			this.expression = expression;
			this.line = line;
			this.column = column;
		}

		public int getLine() {
			return line;
		}

		public int getColumn() {
			return column;
		}

		public Exp getExpression() {
			return expression;
		}


		public void accept(Visitor v) {
			v.visit(this);
		}

}
