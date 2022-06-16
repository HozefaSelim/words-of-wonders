package finalprojesi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class kelimeler {

    static String[] str;
    static char[] kar;
    public static ArrayList<String> Kelimeler = new ArrayList<>();
    public static ArrayList<String> karakterler = new ArrayList<String>();
    File f1 = new File("kelimeler.txt");
    File f2 = new File("karakterler.txt");
    Scanner scan;
    static int j = 0;

    public kelimeler() throws FileNotFoundException {
        scan = new Scanner(f1);
        while (scan.hasNextLine()) {
            Kelimeler.add(scan.nextLine());
        }
        scan.close();
        scan = new Scanner(f2);
        while (scan.hasNextLine()) {
            karakterler.add(scan.nextLine());
        }
        scan.close();
        kelimeler.setdata();
    }

    public static void main(String[] args) throws FileNotFoundException {

    }

    public static void setdata() { // rastgele bir sekilde dosyadan kelimeler gitirmek metodu 
        Random rand = new Random();
        int R = rand.nextInt(4);
        String temp = "";
        str = kelimeler.Kelimeler.get(R).split(",");
        temp += kelimeler.karakterler.get(R);
        kar = temp.toCharArray();
    }
}
