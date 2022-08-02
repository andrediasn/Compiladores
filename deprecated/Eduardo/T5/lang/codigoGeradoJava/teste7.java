
import java.util.ArrayList;
import java.util.Scanner;
public class teste7 {
    private static ArrayList<Object> _retornos = new ArrayList<Object>();

    static void main() {
        int[][] x = null;
        int i = 0;
        int z = 0;
        int k = 0;
        k = 5;
        x = new int[k][];
        i = 0;
        for(int _x13_4 = k; _x13_4 > 0; _x13_4--) {

            x[i] = new int[k];
            i = (i + 1);
        }
        i = 0;
        for(int _x18_4 = k; _x18_4 > 0; _x18_4--) {

            x[i][i] = ((2 * i) + 1);
            i = (i + 1);
        }
        z = x[3][3];
    }



    public static void main(String args[]) {
        main();
    }
}