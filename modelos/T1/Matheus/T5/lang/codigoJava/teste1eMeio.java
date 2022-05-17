import java.util.ArrayList;
import java.util.Scanner;

public class teste1eMeio {

	private static ArrayList<Object> _vReturnables = new ArrayList<Object>();

    static void main() {
        int q = 0;
        int w = 0;
        int z = 0;
        int n = 0;
        n = 13;
        q = 5;
        w = ((int)divMod(String.valueOf(1), n, q));
        z = ((2 * w) + 1);
        System.out.println('Z');
        System.out.println(':');
        System.out.println(z);
        System.out.println('\n');
    }



    static void divMod(int  n, int  q) {
         ArrayList<Object> _retAux = new ArrayList<Object>();

        _retAux.add((n / q));
        _retAux.add((n % q));
        for(Object o : _retAux){
        	_vReturnables.add(o);
        }
        return;
    }

     
    static Object divMod(String _i, int  n, int  q) {
        divMod(n, q); 
        Object _retAux = _vReturnables.get(Integer.parseInt(_i));
        _vReturnables.clear();
        return _retAux
    }


    public static void main(String args[]) {
        main();
    }
}