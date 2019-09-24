import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Chain chain = new Chain("./chains/chain");
        List<Miner> miners = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Miner miner = new Miner(chain, i);
            miners.add(miner);
            miner.start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Can't sleep :(");
        }
        for (Miner miner : miners) {
            miner.stop();
        }
        if (!chain.validateChain()) {
            System.out.println("Invalid chain!");
            return;
        } else {
            for (int i = 0; i < 5; i++) {
//                chain.printByPosition(i);
            }
            chain.printAll();
        }
    }
}
