import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Block implements Serializable {

    private int id;
    private static int lastId = 0;
    private String hash;
    private String hashPrev;
    private long timestamp;
    private int magicNumber;
    private long computationTime;

    public Block(String hashPrev, int numberOfZeros) {
        this.id = ++lastId;
        this.hashPrev = hashPrev;
        this.timestamp = new Date().getTime();
        calculateHash(numberOfZeros);
    }

    private void calculateHash(int numberOfZeros) {
        long startTime = new Date().getTime();
        do {
            magicNumber = new Random().nextInt() & Integer.MAX_VALUE;
            this.hash = StringUtil.applySha256(id + timestamp + hashPrev + magicNumber);
        } while(!hash.startsWith("0".repeat(Math.max(0, numberOfZeros))));
        long endTime = new Date().getTime();
        computationTime = (endTime - startTime) / 1000;
    }

    public int getId() {
        return id;
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

    public void setLastId(int id) {
        lastId = id;
    }
}
