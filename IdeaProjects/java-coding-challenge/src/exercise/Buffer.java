package exercise;

public class Buffer {

    private PriceUpdate contents;
    private boolean empty = true;

    public synchronized void put (PriceUpdate priceUpdate) throws InterruptedException {
        while (empty == false) { 	//wait till the buffer becomes empty
            try { wait();
            }catch (InterruptedException e) {throw e;}
        }
        contents = priceUpdate;
        empty = false;
        System.out.println("Producer: put..." + priceUpdate);
        notify();
    }

    public synchronized PriceUpdate get () throws InterruptedException {
        while (empty == true)  {	//wait till something appears in the buffer
            try {
                wait();
            } catch (InterruptedException e) {throw e;}
        }
        empty = true;
        notify();
        PriceUpdate val = contents;
        System.out.println("Consumer: got..." + val);
        return val;
    }
}
