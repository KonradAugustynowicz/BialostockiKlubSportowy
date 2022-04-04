import java.util.*;

public class Zad1 {
    public static ArrayList<Integer> LFSR(String wielomian, String ziarno) {
        ArrayList<Integer> wynik = new ArrayList<>();
        ArrayList<String> losowe = new ArrayList<>();
        final int iteracje= (int) Math.pow(2,wielomian.length())-1;
        int[] ziarnoArray = Arrays.stream(ziarno.split(""))
                .mapToInt(Integer::valueOf)
                .toArray();

        int[] wielomianArray = Arrays.stream(wielomian.split(""))
                .mapToInt(Integer::valueOf)
                .toArray();

        List<Integer> kolejkaDoXor = new LinkedList<Integer>();

        for (int i = 0; i < ziarnoArray.length; i++) {
            if (ziarnoArray[i] == 1) {
                kolejkaDoXor.add(i);
            }
        }

        for (int i = 0; i < iteracje; i++) {

            wynik.add(wielomianArray[wielomianArray.length - 1]);

            int xor_result = wielomianArray[kolejkaDoXor.get(0)];
            for (int j = 1; j < kolejkaDoXor.size(); j++) {
                xor_result = xor_result ^ wielomianArray[kolejkaDoXor.get(j)];
            }

            for (int j = wielomianArray.length - 1; j > 0; j--) {
                wielomianArray[j] = wielomianArray[j - 1];
            }

            wielomianArray[0] = xor_result;
        }
        return wynik;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Wielomian: ");
        String wielomian = sc.nextLine();

        System.out.println("Ziarno: ");
        String ziarno = sc.nextLine();

        ArrayList<Integer> losowe = LFSR(wielomian, ziarno);
        for (int s : losowe) {
            System.out.println(s);
        }
    }
}