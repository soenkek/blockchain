import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Block implements Serializable {

    private int id;
    private String hash;
    private String hashPrev;
    private long timestamp;
    private int magicNumber;
    private long computationTime;
    private int minerId;
    private String nChange;
    private List<String> messages;

    public Block(String hashPrev, int numberOfZeros, int minerId, List<String> messages) {
        this.hashPrev = hashPrev;
        this.messages = messages;
        this.timestamp = new Date().getTime();
        this.minerId = minerId;
        calculateHash(numberOfZeros);
    }

    private void calculateHash(int numberOfZeros) {
        long startTime = new Date().getTime();
        String messageString = "";
        if (messages != null)
            for (String msg : messages)
                messageString = messageString.concat(msg);
        do {
            magicNumber = new Random().nextInt() & Integer.MAX_VALUE;
            this.hash = StringUtil.applySha256(id + timestamp + hashPrev + magicNumber + messageString);
        } while(!hash.startsWith("0".repeat(Math.max(0, numberOfZeros))));
        long endTime = new Date().getTime();
        computationTime = (endTime - startTime) / 1000;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public String getHashPrev() {
        return hashPrev;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public long getComputationTime() {
        return computationTime;
    }

    public int getMinerId() {
        return minerId;
    }

    public String getnChange() {
        return nChange;
    }

    public void setnChange(String nChange) {
        this.nChange = nChange;
    }

    public List<String> getMessages() {
        return messages;
    }
}
