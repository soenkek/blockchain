import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter how many zeros the hash must starts with: ");
        Scanner scanner = new Scanner(System.in);
        int numberOfZeros = scanner.nextInt();
        scanner.close();

        Chain chain = new Chain("./chains/chain");
        for (int i = 0; i < 10; i++) {
            chain.generateNewBlock(numberOfZeros);
        }
        if (!chain.validateChain()) {
            System.out.println("Invalid chain!");
            return;
        }
        for (int i = 0; i < 5; i++) {
            chain.printByPosition(i);
        }
    }
}
