import java.util.ArrayList;
import java.util.Scanner;

public class teste9 {

	private static ArrayList<Object> _vReturnables = new ArrayList<Object>();

    static void f(int  x) {
         ArrayList<Object> _retAux = new ArrayList<Object>();
        int y = 0;
        y = ((2 * x) + 1);
        	if((y < 10)) {

        		_retAux.add(y);
        		_retAux.add(1.5f);
        		for(Object o : _retAux){
        			_vReturnables.add(o);
        		}
        		return;
        	} else {

        		_retAux.add(y);
        		_retAux.add(1.5f);
        		for(Object o : _retAux){
        			_vReturnables.add(o);
        		}
        		return;
        	}

    }

     
    static Object f(String _i, int  x) {
        f(x); 
        Object _retAux = _vReturnables.get(Integer.parseInt(_i));
        _vReturnables.clear();
        return _retAux
    }


    static void main() {
        int h = 0;
        float z = 0f;
        z = ((float)f(String.valueOf(1), 10));
        h = (2 * ((int)f(String.valueOf(0), 10)));
    }



    public static void main(String args[]) {
        main();
    }
}