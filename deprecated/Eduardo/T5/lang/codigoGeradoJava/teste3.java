
import java.util.ArrayList;
import java.util.Scanner;
public class teste3 {
    private static ArrayList<Object> _retornos = new ArrayList<Object>();

    static void main() {
        int[] x = null;
        int i = 0;
        int k = 0;
        k = 4;
        x = new int[k];
        x[0] = 0;
        x[3] = 15;
        System.out.print(x[3]);
        System.out.print('\n');
        i = 0;
        for(int _x17_3 = k; _x17_3 > 0; _x17_3--) {

            if(((i % 2) == 0)) {

                x[i] = (2 * i);
            } else {

                x[i] = ((2 * i) + 1);
            }

            i = (i + 1);
        }
        i = 0;
        System.out.print('{');
        if((0 < k)) {

            System.out.print(x[0]);
            for(int _x31_6 = (k - 1); _x31_6 > 0; _x31_6--) {

                System.out.print(',');
                System.out.print(x[(i + 1)]);
                i = (i + 1);
            }
        } 
        System.out.print('}');
        System.out.print('\n');
    }



    public static void main(String args[]) {
        main();
    }
}