
import java.util.ArrayList;
import java.util.Scanner;
public class teste11 {
    private static ArrayList<Object> _retornos = new ArrayList<Object>();

    static void main() {
        int i = 0;
        int nlines = 0;
        nlines = 5;
        for(int _x15_3 = nlines; _x15_3 > 0; _x15_3--) {

            i = nlines;
            for(int _x17_5 = i; _x17_5 > 0; _x17_5--) {

                System.out.print('*');
            }
            nlines = (nlines - 1);
            System.out.print('\n');
        }
    }



    public static void main(String args[]) {
        main();
    }
}