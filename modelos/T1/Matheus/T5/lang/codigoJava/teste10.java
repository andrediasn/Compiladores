import java.util.ArrayList;
import java.util.Scanner;

public class teste10 {

	private static ArrayList<Object> _vReturnables = new ArrayList<Object>();

    static void fibonacci(int  n) {
         ArrayList<Object> _retAux = new ArrayList<Object>();
        	if((n < 1)) {

        		_retAux.add(n);
        		for(Object o : _retAux){
        			_vReturnables.add(o);
        		}
        		return;
        	} 
        	if((n == 1)) {

        		_retAux.add(n);
        		for(Object o : _retAux){
        			_vReturnables.add(o);
        		}
        		return;
        	} 

        _retAux.add((((int)fibonacci(String.valueOf(0), (n - 1))) + ((int)fibonacci(String.valueOf(0), (n - 2)))));
        for(Object o : _retAux){
        	_vReturnables.add(o);
        }
        return;
    }

     
    static Object fibonacci(String _i, int  n) {
        fibonacci(n); 
        Object _retAux = _vReturnables.get(Integer.parseInt(_i));
        _vReturnables.clear();
        return _retAux
    }


    static void main() {
        int v = 0;
        v = ((int)fibonacci(String.valueOf(0), 5));
        System.out.println(v);
    }



    public static void main(String args[]) {
        main();
    }
}