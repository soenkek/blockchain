import java.io.Serializable;
import java.util.Date;
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

    public Block(String hashPrev, int numberOfZeros, int minerId) {
        this.hashPrev = hashPrev;
        this.timestamp = new Date().getTime();
        this.minerId = minerId;
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
}
