
import java.util.ArrayList;
import java.util.Scanner;
public class teste1 {
    private static ArrayList<Object> _retornos = new ArrayList<Object>();

    static void main() {
        int q = 0;
        int res = 0;
        int quo = 0;
        int n = 0;
        n = 13;
        q = 5;

        divMod(n, q);
        quo = (int) _retornos.get(0);
        _retornos.remove(0);
        res = (int) _retornos.get(0);
        _retornos.remove(0);


        System.out.print('Q');
        System.out.print(':');
        System.out.print(quo);
        System.out.print('\n');
        System.out.print('R');
        System.out.print(':');
        System.out.print(res);
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