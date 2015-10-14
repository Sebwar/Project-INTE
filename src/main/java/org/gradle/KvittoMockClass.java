

public class KvittoMockClass {
	
	
	private int id;
	private String name;
	private String adress;
	private int amount;
	private boolean priceByWeight;
	private int productPrice;
	private int discount;
	
	
	//Mocks Product
	public KvittoMockClass (int id, String name, int amount, boolean priceByWeight, int productPrice) {
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.priceByWeight = priceByWeight; 
		this.productPrice = productPrice;
	}
	
	//Mocks Customer
	public KvittoMockClass (String name, String adress) {
		this.name = name;
		this.adress = adress;
	}
	
	//Mocks Sales
	public KvittoMockClass (String name, int discount) {
		this.name = name;
		this.discount = discount;
	}
	
	
	
	
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAdress() {
		return adress;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public boolean priceByWeight() {
		return priceByWeight;
	}
	
	public int getProductPrice() {
		return productPrice;
	}
	
	public int getDiscount() {
		return discount;
	}

	
	
}
