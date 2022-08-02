
import java.util.ArrayList;
import java.util.Scanner;
public class DivMod {
    private static ArrayList<Object> _retornos = new ArrayList<Object>();

    static void main() {
        int x = 0;
        int y = 0;
        x = 4;
        y = 2;

        divmod(x, y);
        x = (int) _retornos.get(0);
        _retornos.remove(0);
        y = (int) _retornos.get(0);
        _retornos.remove(0);


        System.out.print(x);
        System.out.print(y);
    }



    static void divmod(int num, int div) {
         ArrayList<Object> _aux = new ArrayList<Object>();
        int q = 0;
        int r = 0;
        q = num / div;
        r = num % div;
        _aux.add(q);
        _aux.add(r);
        for(Object o : _aux)
        {
            _retornos.add(o);
        }
        return;
    }

     
    static Object divmod(String _i, int num, int div) {
        divmod(num, div); 
        Object _aux = _retornos.get(Integer.parseInt(_i));
        _retornos.clear();
        return _aux;
    }


    public static void main(String args[]) {
        main();
    }
}