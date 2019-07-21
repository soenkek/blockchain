import java.util.ArrayList;
import java.util.List;

public class Chain {

    private List<Block> chain;

    public Chain() {
        this.chain = new ArrayList<>();
    }

    public void generateNewBlock() {
        String hashPrev = chain.size() == 0 ? "0" : chain.get(chain.size() - 1).getHash();
        chain.add(new Block(hashPrev));
    }

    public boolean validateChain() {
        for (int i = 1; i < chain.size(); i++) {
            if (!chain.get(i).getHashPrev().equals(chain.get(i - 1).getHash()))
                return false;
        }
        return true;
    }

    public void printAll() {
        for (Block block :
                chain) {
            printBlock(block);
        }
    }

    public void printByPosition(int i) {
        if(i >= chain.size()) {
            System.out.println("Invalid id!");
            return;
        }
        printBlock(chain.get(i));
    }

    private void printBlock(Block block) {
        System.out.println(
                "\nBlock:\nId: " +
                        block.getId() +
                        "\nTimestamp: " +
                        block.getTimestamp() +
                        "\nHash of the previous block:\n" +
                        block.getHashPrev() +
                        "\nHash of the block:\n" +
                        block.getHash()
        );
    }
}
