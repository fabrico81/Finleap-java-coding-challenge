package exercise;

public class PriceUpdate {
	
	private final String companyName;
	private final double price;
	
	public PriceUpdate(String companyName, double price) {
		this.companyName = companyName;
		this.price = price;
	}
	
	public String getCompanyName() {
		return this.companyName;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	@Override
	public String toString() {
		return companyName + " - " + price;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof PriceUpdate))
			return false;
		if (obj == this)
			return true;
		return this.getPrice() == ((PriceUpdate) obj).getPrice();
	}
	
	@Override
	public int hashCode() {
		return (Double.valueOf(getPrice()).hashCode());
	}
}
