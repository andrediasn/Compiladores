
import java.util.ArrayList;
import java.util.Scanner;
public class teste5 {
    private static ArrayList<Object> _retornos = new ArrayList<Object>();

    static class Ponto {
        int  x;
        int  y;
    }
    static void main() {
        Ponto[] p = null;
        int i = 0;
        int k = 0;
        k = 5;
        p = new Ponto[k];
        i = 0;
        for(int _x17_3 = k; _x17_3 > 0; _x17_3--) {

            p[i] = new Ponto();
            p[i].x = (12 + i);
            p[i].y = (12 - i);
            i = (i + 1);
        }
        System.out.print('\n');
    }



    public static void main(String args[]) {
        main();
    }
}