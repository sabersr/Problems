import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("tourist1.txt");
        Scanner scnr = new Scanner(file);
        PrintStream ps = new PrintStream("tourist1_output.txt");
        System.setOut(ps);
        int visittimes = Integer.parseInt(scnr.nextLine());
        for (int m = 0; m < visittimes; m++) {
            String campus = scnr.nextLine();
            String[] campusData = campus.split(" ");
            Integer n = Integer.parseInt(campusData[0]);
            Integer k = Integer.parseInt(campusData[1]);
            Long v = Long.parseLong(campusData[2]);
            String[] attractions = new String[n];
            boolean[] visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                attractions[i] = scnr.nextLine();
            }
            int startpt = (int) ((v - 1) % (n / gcd(n, k)) * k % n);
            for (int i = 0; i < k; i++) {
                visited[(startpt + i) % n] = true;
            }
            System.out.print("Case #"+(m+1)+": ");
            for (int i = 0; i < n; i++) {
                if (visited[i])
                    System.out.print(attractions[i] + " ");
            }
            System.out.println();
        }
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

}
