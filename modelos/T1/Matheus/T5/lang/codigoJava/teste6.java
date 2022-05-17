import java.util.ArrayList;
import java.util.Scanner;

public class teste6 {

	private static ArrayList<Object> _vReturnables = new ArrayList<Object>();

    static class Ponto {
    	int  x;
    	int  y;
    }
    static void printP(Ponto  p) {
        System.out.println('(');
        System.out.println(p.x);
        System.out.println(',');
        System.out.println(p.y);
        System.out.println(')');
    }



    static void printV(Ponto  v, int  n) {
        int i = 0;
        System.out.println('{');
        	if((0 < n)) {

        		printP(v[0]);
        		_vReturnables.clear();

        		i = 1;
        		for(int _i_30_7 = 0; _i_30_7 < (n - 1); _i_30_7++){

        			System.out.println(',');

        			printP(v[i]);
        			_vReturnables.clear();

        			i = (i + 1);
        		}
        	} 
        System.out.println('}');
    }



    static void sort(Ponto  v, int  n) {
        Ponto aux = null;
        int i = 0;
        int j = 0;
        i = 0;
        for(int _i_42_4 = 0; _i_42_4 < (n - 1); _i_42_4++){

        	j = (i + 1);
        	for(int _i_44_7 = 0; _i_44_7 < (n - (i + 1)); _i_44_7++){

        			if((v[i].x < v[j].x)) {

        				aux = v[i];
        				v[i] = v[j];
        				v[j] = aux;
        			} 
        		j = (j + 1);
        	}
        	i = (i + 1);
        }
    }



    static void main() {
        Ponto[] p = null;
        int i = 0;
        int k = 0;
        k = 5;
        p = k;
        i = 0;
        for(int _i_60_3 = 0; _i_60_3 < k; _i_60_3++){

        	p[i] = p[i];
        	p[i].x = (12 + i);
        	p[i].y = (12 - i);
        	i = (i + 1);
        }

        printV(p, k);
        _vReturnables.clear();

        sort(p, k);
        _vReturnables.clear();

        System.out.println('\n');

        printV(p, k);
        _vReturnables.clear();

        System.out.println('\n');
    }



    public static void main(String args[]) {
        main();
    }
}