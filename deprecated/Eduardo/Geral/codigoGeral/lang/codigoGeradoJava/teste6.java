
import java.util.ArrayList;
import java.util.Scanner;
public class teste6 {
    private static ArrayList<Object> _retornos = new ArrayList<Object>();

    static class Ponto {
        int  x;
        int  y;
    }
    static void printP(Ponto  p) {
        System.out.print('(');
        System.out.print(p.x);
        System.out.print(',');
        System.out.print(p.y);
        System.out.print(')');
    }



    static void printV(Ponto [] v, int  n) {
        int i = 0;
        System.out.print('{');
        if((0 < n)) {

            printP(v[0]);
            _retornos.clear();

            i = 1;
            for(int _x30_7 = (n - 1); _x30_7 > 0; _x30_7--) {

                System.out.print(',');

                printP(v[i]);
                _retornos.clear();

                i = (i + 1);
            }
        } 
        System.out.print('}');
    }



    static void sort(Ponto [] v, int  n) {
        Ponto aux = null;
        int i = 0;
        int j = 0;
        i = 0;
        for(int _x42_4 = (n - 1); _x42_4 > 0; _x42_4--) {

            j = (i + 1);
            for(int _x44_7 = (n - (i + 1)); _x44_7 > 0; _x44_7--) {

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
        p = new Ponto[k];
        i = 0;
        for(int _x60_3 = k; _x60_3 > 0; _x60_3--) {

            p[i] = new Ponto();
            p[i].x = (12 + i);
            p[i].y = (12 - i);
            i = (i + 1);
        }

        printV(p, k);
        _retornos.clear();

        sort(p, k);
        _retornos.clear();

        System.out.print('\n');

        printV(p, k);
        _retornos.clear();

        System.out.print('\n');
    }



    public static void main(String args[]) {
        main();
    }
}