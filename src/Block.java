import java.util.Date;

public class Block {

    private int id;
    private static int lastId = 0;
    private String hash;
    private String hashPrev;
    private long timestamp;

    public Block(String hashPrev) {
        this.id = ++lastId;
        this.hashPrev = hashPrev;
        this.timestamp = new Date().getTime();
        this.hash = StringUtil.applySha256(id + timestamp + hashPrev);
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
}
