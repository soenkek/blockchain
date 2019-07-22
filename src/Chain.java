import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Chain {

    private List<Block> chain;
    private String filePath;

    public Chain(String filePath) {
        this.filePath = filePath;
        readFile();
    }

    public void generateNewBlock(int numberOfZeros) {
        String hashPrev = chain.size() == 0 ? "0" : chain.get(chain.size() - 1).getHash();
        chain.add(new Block(hashPrev, numberOfZeros));
        writeFile();
    }

    public boolean validateChain() {
        if (chain == null) return false;
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
                        "\nMagic number: " +
                        block.getMagicNumber() +
                        "\nHash of the previous block:\n" +
                        block.getHashPrev() +
                        "\nHash of the block:\n" +
                        block.getHash() +
                        "\nBlock was generating for " +
                        block.getComputationTime() +
                        " seconds"
        );
    }

    private void readFile() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(
                    new FileInputStream(filePath));
            chain = (List<Block>) inputStream.readObject();
            if (chain != null && chain.size() > 0) {
                Block lastBlock = chain.get(chain.size() - 1);
                lastBlock.setLastId(lastBlock.getId());
            }
        } catch (IOException | ClassNotFoundException ignored) {}
        if (!validateChain()) {
            System.out.println("Invalid chain!");
            chain = new ArrayList<>();
        }
    }

    private void writeFile() {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(chain);
        } catch (IOException ignored) {}
    }
}
