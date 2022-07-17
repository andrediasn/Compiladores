import java.util.ArrayList;
import java.util.Scanner;

public class teste3 {

	private static ArrayList<Object> _vReturnables = new ArrayList<Object>();

    static void main() {
        int[] x = null;
        int i = 0;
        int k = 0;
        k = 4;
        x = k;
        x[0] = 0;
        x[3] = 15;
        System.out.println(x[3]);
        System.out.println('\n');
        i = 0;
        for(int _i_17_3 = 0; _i_17_3 < k; _i_17_3++){

        		if(((i % 2) == 0)) {

        			x[i] = (2 * i);
        		} else {

        			x[i] = ((2 * i) + 1);
        		}

        	i = (i + 1);
        }
        i = 0;
        System.out.println('{');
        	if((0 < k)) {

        		System.out.println(x[0]);
        		for(int _i_31_6 = 0; _i_31_6 < (k - 1); _i_31_6++){

        			System.out.println(',');
        			System.out.println(x[(i + 1)]);
        			i = (i + 1);
        		}
        	} 
        System.out.println('}');
        System.out.println('\n');
    }



    public static void main(String args[]) {
        main();
    }
}