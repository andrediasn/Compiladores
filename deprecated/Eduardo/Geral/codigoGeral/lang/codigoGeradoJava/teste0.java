
import java.util.ArrayList;
import java.util.Scanner;
public class teste0 {
    private static ArrayList<Object> _retornos = new ArrayList<Object>();

    static void main() {
        int nlines = 0;
        int i = 0;
        nlines = 5;
        i = nlines;
        for(int _x16_3 = nlines; _x16_3 > 0; _x16_3--) {

            for(int _x17_5 = i; _x17_5 > 0; _x17_5--) {

                System.out.print('*');
            }
            i = (i - 1);
            System.out.print('\n');
        }
    }



    public static void main(String args[]) {
        main();
    }
}