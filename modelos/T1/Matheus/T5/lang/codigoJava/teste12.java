import java.util.ArrayList;
import java.util.Scanner;

public class teste12 {

	private static ArrayList<Object> _vReturnables = new ArrayList<Object>();

    static void even(int  n) {
         ArrayList<Object> _retAux = new ArrayList<Object>();
        	if((n == 0)) {

        		_retAux.add(true);
        		for(Object o : _retAux){
        			_vReturnables.add(o);
        		}
        		return;
        	} else {

        		_retAux.add(((boolean)odd(String.valueOf(0), (n - 1))));
        		for(Object o : _retAux){
        			_vReturnables.add(o);
        		}
        		return;
        	}

    }

     
    static Object even(String _i, int  n) {
        even(n); 
        Object _retAux = _vReturnables.get(Integer.parseInt(_i));
        _vReturnables.clear();
        return _retAux
    }


    static void odd(int  n) {
         ArrayList<Object> _retAux = new ArrayList<Object>();
        	if((n == 0)) {

        		_retAux.add(false);
        		for(Object o : _retAux){
        			_vReturnables.add(o);
        		}
        		return;
        	} else {

        		_retAux.add(((boolean)even(String.valueOf(0), (n - 1))));
        		for(Object o : _retAux){
        			_vReturnables.add(o);
        		}
        		return;
        	}

    }

     
    static Object odd(String _i, int  n) {
        odd(n); 
        Object _retAux = _vReturnables.get(Integer.parseInt(_i));
        _vReturnables.clear();
        return _retAux
    }


    static void main() {
        boolean r = false;
        r = ((boolean)even(String.valueOf(0), 3));
        	if(r) {

        		System.out.println('P');
        		System.out.println('A');
        		System.out.println('R');
        	} else {

        		System.out.println('I');
        		System.out.println('M');
        		System.out.println('P');
        		System.out.println('A');
        		System.out.println('R');
        	}

    }



    public static void main(String args[]) {
        main();
    }
}