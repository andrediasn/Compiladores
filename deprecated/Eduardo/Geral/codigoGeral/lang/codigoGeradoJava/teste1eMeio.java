
import java.util.ArrayList;
import java.util.Scanner;
public class teste1eMeio {
    private static ArrayList<Object> _retornos = new ArrayList<Object>();

    static void main() {
        int q = 0;
        int w = 0;
        int z = 0;
        int n = 0;
        n = 13;
        q = 5;
        w = ((int)divMod(String.valueOf(1), n, q));
        z = ((2 * w) + 1);
        System.out.print('Z');
        System.out.print(':');
        System.out.print(z);
        System.out.print('\n');
    }



    static void divMod(int  n, int  q) {
         ArrayList<Object> _aux = new ArrayList<Object>();
        _aux.add((n / q));
        _aux.add((n % q));
        for(Object o : _aux)
        {
            _retornos.add(o);
        }
        return;
    }

     
    static Object divMod(String _i, int  n, int  q) {
        divMod(n, q); 
        Object _aux = _retornos.get(Integer.parseInt(_i));
        _retornos.clear();
        return _aux;
    }


    public static void main(String args[]) {
        main();
    }
}