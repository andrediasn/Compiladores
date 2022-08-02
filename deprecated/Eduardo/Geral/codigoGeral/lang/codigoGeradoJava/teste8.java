
import java.util.ArrayList;
import java.util.Scanner;
public class teste8 {
    private static ArrayList<Object> _retornos = new ArrayList<Object>();

    static void fat(int  n) {
         ArrayList<Object> _aux = new ArrayList<Object>();
        if((n == 0)) {

            _aux.add(1);
            for(Object o : _aux)
            {
                _retornos.add(o);
            }
            return;
        } 
        _aux.add((n * ((int)fat(String.valueOf(0), (n - 1)))));
        for(Object o : _aux)
        {
            _retornos.add(o);
        }
        return;
    }

     
    static Object fat(String _i, int  n) {
        fat(n); 
        Object _aux = _retornos.get(Integer.parseInt(_i));
        _retornos.clear();
        return _aux;
    }


    static void spook(int  n) {
         ArrayList<Object> _aux = new ArrayList<Object>();
        if((n == 1)) {

            _aux.add((2 * n));
            for(Object o : _aux)
            {
                _retornos.add(o);
            }
            return;
        } 
        if((n == 2)) {

            _aux.add(((2 * n) + 1));
            for(Object o : _aux)
            {
                _retornos.add(o);
            }
            return;
        } 
        _aux.add((n - 1));
        for(Object o : _aux)
        {
            _retornos.add(o);
        }
        return;
    }

     
    static Object spook(String _i, int  n) {
        spook(n); 
        Object _aux = _retornos.get(Integer.parseInt(_i));
        _retornos.clear();
        return _aux;
    }


    static void main() {
        int k = 0;
        k = ((int)spook(String.valueOf(0), 2));
        System.out.print(k);
        System.out.print('\n');
    }



    public static void main(String args[]) {
        main();
    }
}