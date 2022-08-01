/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.tipos;

public class STyFunc {
    private SType[] retorno;
    private SType[] parametro;
    private String id;
    public SType[] getRetorno() {
        return retorno;
    }
    public SType[] getParametro() {
        return parametro;
    }
    public String getId() {
        return id;
    }
    public STyFunc( String id, SType[] p, SType[] retorno) {
        this.id = id;
        this.retorno = retorno;
        this.parametro = p;
    }
}
