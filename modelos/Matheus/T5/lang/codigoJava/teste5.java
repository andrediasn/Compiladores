import java.util.ArrayList;
import java.util.Scanner;

public class teste5 {

	private static ArrayList<Object> _vReturnables = new ArrayList<Object>();

    static class Ponto {
    	int  x;
    	int  y;
    }
    static void main() {
        Ponto[] p = null;
        int i = 0;
        int k = 0;
        k = 5;
        p = k;
        i = 0;
        for(int _i_17_3 = 0; _i_17_3 < k; _i_17_3++){

        	p[i] = p[i];
        	p[i].x = (12 + i);
        	p[i].y = (12 - i);
        	i = (i + 1);
        }
        System.out.println('\n');
    }



    public static void main(String args[]) {
        main();
    }
}