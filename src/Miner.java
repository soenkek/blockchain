public class Miner extends Thread {

    private final Chain chain;
    private int id;

    public Miner(Chain chain, int id) {
        this.chain = chain;
        this.id = id;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            if (chain.addBlock(new Block(chain.getLastHash(), chain.getNumOfZeros(), id, chain.getMessages())))
                chain.sendMessage("Miner#" + id, "I did it!");
        }
    }
}
