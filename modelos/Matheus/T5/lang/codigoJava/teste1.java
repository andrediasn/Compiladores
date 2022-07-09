import java.util.ArrayList;
import java.util.Scanner;

public class teste1 {

	private static ArrayList<Object> _vReturnables = new ArrayList<Object>();

    static void main() {
        int q = 0;
        int res = 0;
        int quo = 0;
        int n = 0;
        n = 13;
        q = 5;

        divMod(n, q);
        quo = (int) _vReturnables.get(0);
        _vReturnables.remove(0);
        res = (int) _vReturnables.get(0);
        _vReturnables.remove(0);


        System.out.println('Q');
        System.out.println(':');
        System.out.println(quo);
        System.out.println('\n');
        System.out.println('R');
        System.out.println(':');
        System.out.println(res);
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