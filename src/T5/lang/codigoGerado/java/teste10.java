
import java.util.ArrayList;
import java.util.Scanner;
public class teste10 {
    private static ArrayList<Object> _retornos = new ArrayList<Object>();

    static void fibonacci(int  n) {
         ArrayList<Object> _aux = new ArrayList<Object>();
        if((n < 1)) {
            _aux.add(n);
            for(Object o : _aux)
            {
                _retornos.add(o);
            }
            return;
        } 
        if((n == 1)) {
            _aux.add(n);
            for(Object o : _aux)
            {
                _retornos.add(o);
            }
            return;
        } 
        _aux.add((((int)fibonacci(String.valueOf(0), (n - 1))) + ((int)fibonacci(String.valueOf(0), (n - 2)))));
        for(Object o : _aux)
        {
            _retornos.add(o);
        }
        return;
    }

     
    static Object fibonacci(String _i, int  n) {
        fibonacci(n); 
        Object _aux = _retornos.get(Integer.parseInt(_i));
        _retornos.clear();
        return _aux;
    }


    static void main() {
        int v = 0;
        v = ((int)fibonacci(String.valueOf(0), 5));
        System.out.print(v);
    }



    public static void main(String args[]) {
        main();
    }
}