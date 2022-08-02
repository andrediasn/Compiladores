
import java.util.ArrayList;
import java.util.Scanner;
public class Fat {
    private static ArrayList<Object> _retornos = new ArrayList<Object>();

    static void main() {
        System.out.print(((int)fat(String.valueOf(0), 10)));
    }



    static void fat(int num) {
         ArrayList<Object> _aux = new ArrayList<Object>();
        if(num < 1) {
            _aux.add(1);
            for(Object o : _aux)
            {
                _retornos.add(o);
            }
            return;
        } else {
            _aux.add(num * ((int)fat(String.valueOf(0), num - 1)));
            for(Object o : _aux)
            {
                _retornos.add(o);
            }
            return;
        }

    }

     
    static Object fat(String _i, int num) {
        fat(num); 
        Object _aux = _retornos.get(Integer.parseInt(_i));
        _retornos.clear();
        return _aux;
    }


    public static void main(String args[]) {
        main();
    }
}