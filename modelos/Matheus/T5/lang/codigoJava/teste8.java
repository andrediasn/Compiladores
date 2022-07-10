import java.util.ArrayList;
import java.util.Scanner;

public class teste8 {

	private static ArrayList<Object> _vReturnables = new ArrayList<Object>();

    static void fat(int  n) {
         ArrayList<Object> _retAux = new ArrayList<Object>();
        	if((n == 0)) {


        		_retAux.add(1);
        		for(Object o : _retAux){
        			_vReturnables.add(o);
        		}
        		return;
        	} 

        _retAux.add((n * ((int)fat(String.valueOf(0), (n - 1)))));
        for(Object o : _retAux){
        	_vReturnables.add(o);
        }
        return;
    }

     
    static Object fat(String _i, int  n) {
        fat(n); 
        Object _retAux = _vReturnables.get(Integer.parseInt(_i));
        _vReturnables.clear();
        return _retAux
    }


    static void spook(int  n) {
         ArrayList<Object> _retAux = new ArrayList<Object>();
        	if((n == 1)) {


        		_retAux.add((2 * n));
        		for(Object o : _retAux){
        			_vReturnables.add(o);
        		}
        		return;
        	} 
        	if((n == 2)) {


        		_retAux.add(((2 * n) + 1));
        		for(Object o : _retAux){
        			_vReturnables.add(o);
        		}
        		return;
        	} 

        _retAux.add((n - 1));
        for(Object o : _retAux){
        	_vReturnables.add(o);
        }
        return;
    }

     
    static Object spook(String _i, int  n) {
        spook(n); 
        Object _retAux = _vReturnables.get(Integer.parseInt(_i));
        _vReturnables.clear();
        return _retAux
    }


    static void main() {
        int k = 0;
        k = ((int)fat(String.valueOf(0), 6));
        k = ((int)spook(String.valueOf(0), 2));
        System.out.println(k);
        System.out.println('\n');
    }



    public static void main(String args[]) {
        main();
    }
}