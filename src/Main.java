public class Main {
    public static void main(String[] args) {
        Chain chain = new Chain();
        for (int i = 0; i < 10; i++) {
            chain.generateNewBlock();
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
