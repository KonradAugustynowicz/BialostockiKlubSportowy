
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Zad2 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Wielomian: ");
        String wielomian = sc.nextLine();

        System.out.println("Ziarno: ");
        String ziarno = sc.nextLine();

        System.out.println("Tekst: ");
        String tekst = sc.nextLine();

        String output = processCoding(tekst, wielomian, ziarno);

        System.out.println("Wynik:");
        System.out.println(output);
    }

    public static String processCoding(String tekstString, String wielomianString, String ziarnoString) {
        char[] tekst = tekstString.toCharArray();

        int[] wielomian = Arrays.stream(wielomianString.split(""))
                .mapToInt(Integer::valueOf)
                .toArray();

        int[] ziarno = Arrays.stream(ziarnoString.split(""))
                .mapToInt(Integer::valueOf)
                .toArray();

        StringBuilder outputSb = new StringBuilder();

        // które indeksy bierzemy do XORowania
        List<Integer> listaBitowDoXORa = new ArrayList<>();


            for (int i = 0; i < ziarno.length; i++) {
                if (ziarno[i] == 1) {
                    listaBitowDoXORa.add(i);
                }
            }


        ArrayList<Integer> lfsred = Zad1.LFSR(wielomianString,ziarnoString);

        int lfsrIterator = 0;

        for (int i = 0; i < tekst.length; i++) {
            int output = 0;
            char c = tekst[i];
            int[] bits = new int[7];
            for (int j=0;j<bits.length;j++){
                if ((int)c%2==1){
                    bits[j] = 1;
                }
                bits[j] = bits[j] ^ lfsred.get(lfsrIterator);
                if (lfsrIterator < lfsred.size() - 1)lfsrIterator++;
                else lfsrIterator=0;
                c=(char)((int)c/2);
            }
            for (int j=6;j>=0;j--){
                outputSb.append(bits[j]);
                output += bits[j]*Math.pow(2,6-j);
            }

//            c = (char)output;

        }
        return outputSb.toString();


    }
}
