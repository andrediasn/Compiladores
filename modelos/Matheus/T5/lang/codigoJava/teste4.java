

import java.util.ArrayList;
import java.util.Scanner;

public class teste4 {

	private static ArrayList<Object> _vReturnables = new ArrayList<Object>();

    static class Ponto {
    	float  x;
    	float  y;
    }
    static void main() {
        Ponto p = null;
        p = p;
        p.x = 10.0f;
        p.y = 10.0f;
        System.out.println('(');
        System.out.println(p.x);
        System.out.println(',');
        System.out.println(' ');
        System.out.println(p.y);
        System.out.println(')');
        System.out.println('\n');
    }


    public static void main(String args[]) {
        main();
    }
}