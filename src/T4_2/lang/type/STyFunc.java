/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.tipos;

public class STyFunc {
    private STipo[] retorno;
    private STipo[] parametro;
    private String id;
    public STipo[] getRetorno() {
        return retorno;
    }
    public STipo[] getParametro() {
        return parametro;
    }
    public String getId() {
        return id;
    }
    public STyFunc( String id, STipo[] p, STipo[] retorno) {
        this.id = id;
        this.retorno = retorno;
        this.parametro = p;
    }
}
