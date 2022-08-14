
import java.util.ArrayList;
import java.util.Scanner;
public class teste12 {
    private static ArrayList<Object> _retornos = new ArrayList<Object>();

    static void even(int  n) {
         ArrayList<Object> _aux = new ArrayList<Object>();
        if((n == 0)) {
            _aux.add(true);
            for(Object o : _aux)
            {
                _retornos.add(o);
            }
            return;
        } else {
            _aux.add(((boolean)odd(String.valueOf(0), (n - 1))));
            for(Object o : _aux)
            {
                _retornos.add(o);
            }
            return;
        }

    }

     
    static Object even(String _i, int  n) {
        even(n); 
        Object _aux = _retornos.get(Integer.parseInt(_i));
        _retornos.clear();
        return _aux;
    }


    static void odd(int  n) {
         ArrayList<Object> _aux = new ArrayList<Object>();
        if((n == 0)) {
            _aux.add(false);
            for(Object o : _aux)
            {
                _retornos.add(o);
            }
            return;
        } else {
            _aux.add(((boolean)even(String.valueOf(0), (n - 1))));
            for(Object o : _aux)
            {
                _retornos.add(o);
            }
            return;
        }

    }

     
    static Object odd(String _i, int  n) {
        odd(n); 
        Object _aux = _retornos.get(Integer.parseInt(_i));
        _retornos.clear();
        return _aux;
    }


    static void main() {
        boolean r = false;
        r = ((boolean)even(String.valueOf(0), 3));
        if(r) {

            System.out.print('P');
            System.out.print('A');
            System.out.print('R');
        } else {

            System.out.print('I');
            System.out.print('M');
            System.out.print('P');
            System.out.print('A');
            System.out.print('R');
        }

    }



    public static void main(String args[]) {
        main();
    }
}