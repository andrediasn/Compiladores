/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
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
