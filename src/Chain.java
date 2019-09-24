import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Chain {

    private List<Block> chain;
    private String filePath;
    private int magicNumber;
    private List<String> messages;
    private List<String> newMessages = new ArrayList<>();

    public Chain(String filePath) {
        magicNumber = 0;
        this.filePath = filePath;
        readFile();
    }

    public boolean validateChain() {
        if (chain == null) return false;
        for (int i = 1; i < chain.size(); i++) {
            if (!chain.get(i).getHashPrev().equals(chain.get(i - 1).getHash()))
                return false;
        }
        return true;
    }

    public boolean validateBlock(Block block) {
        if (chain == null) return false;
        return getLastHash().equals(block.getHashPrev());
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
        String messages = "no messages";
        if (block.getMessages() != null && block.getMessages().size() > 0) {
            messages = "";
            for (String msg :
                    block.getMessages()) {
                messages = messages.concat("\n" + msg);
            }
        }
        System.out.println(
                "\nBlock:" +
                        "\nCreated by miner # " + block.getMinerId() +
                        "\nId: " + block.getId() +
                        "\nTimestamp: " + block.getTimestamp() +
                        "\nMagic number: " + block.getMagicNumber() +
                        "\nHash of the previous block:\n" + block.getHashPrev() +
                        "\nHash of the block:\n" + block.getHash() +
                        "\nBlock data: " + messages +
                        "\nBlock was generating for " + block.getComputationTime() + " seconds" +
                        "\n" + block.getnChange()
        );
    }

    private void readFile() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(
                    new FileInputStream(filePath));
            chain = (List<Block>) inputStream.readObject();
            if (chain != null && chain.size() > 0) {
                Block lastBlock = chain.get(chain.size() - 1);
            }
        } catch (IOException | ClassNotFoundException ignored) {}
        if (!validateChain()) {
//            System.out.println("Creating new chain!");
            chain = new ArrayList<>();
        }
    }

    private void writeFile() {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(chain);
        } catch (IOException ignored) {}
    }

    public String getLastHash() {
        return chain.size() == 0 ? "0" : chain.get(chain.size() - 1).getHash();
    }

    public int getNumOfZeros() {return magicNumber;}

    public synchronized boolean addBlock(Block block) {
        if (validateBlock(block)) {
            synchronized (newMessages) {
                messages = newMessages;
                newMessages = new ArrayList<>();
            }
            block.setId(chain.size());
            if (block.getComputationTime() > 60 && magicNumber > 0) {
                magicNumber--;
                block.setnChange("N was decreased to " + magicNumber);
            }
            else if (block.getComputationTime() < 30) {
                magicNumber++;
                block.setnChange("N was increased to " + magicNumber);
            } else {
                block.setnChange("N stays the same");
            }
            chain.add(block);
            writeFile();
            return true;
        }
        return false;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void sendMessage(String sender, String message) {
        newMessages.add(sender.concat(": " + message));
    }
}
