
import java.util.ArrayList;
import java.util.Scanner;
public class chainIf {
    private static ArrayList<Object> _retornos = new ArrayList<Object>();

    static void main() {
        int x = 0;
        int y = 0;
        x = -1;
        if(x < 0) {
            if(x < 2) {
                y = 10;
            } else {
                y = 20;
            }

        } 
        System.out.print(x);
        System.out.print(y);
    }



    public static void main(String args[]) {
        main();
    }
}