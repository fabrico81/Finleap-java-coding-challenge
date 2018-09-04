package exercise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LoadHandler {

	private static final int MAX_PRICE_UPDATES = 100;
	private int countUpdate = 0;
	private final Consumer consumer;
	private HashSet <PriceUpdate> priceUpdates = new HashSet<>();

	public LoadHandler (Consumer consumer) {
		this.consumer = consumer;
	}

	public void receive(PriceUpdate priceUpdate) {
		if(MAX_PRICE_UPDATES >= countUpdate){
			if(!priceUpdates.contains(priceUpdate)){
				priceUpdates.add(priceUpdate);
				update(priceUpdates,priceUpdate);
				countUpdate++;
			}
		}else{
			try {
				Thread.sleep( (int) Math.random() * 100); // sleep for a randomly chosen time
				countUpdate = 0;
			} catch (InterruptedException e) {return;}
		}
	}

	private void update(HashSet<PriceUpdate> priceUpdates, PriceUpdate priceUpdate){
		for (PriceUpdate obj : priceUpdates) {
			if (obj.getPrice()!= priceUpdate.getPrice() &&
					obj.getCompanyName().equals(priceUpdate.getCompanyName())) {
				priceUpdates.remove(obj);
				priceUpdates.add(priceUpdate);
				break;
			}
		}
	}

	public void send(){
		List<PriceUpdate> priceUpdateList = new ArrayList<>(priceUpdates);
		consumer.send(priceUpdateList);
	}
}
