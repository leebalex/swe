package subsys.financial.management.maintenance;

/**
 * @author Alexander Leeb, k11702617
 */
public class Equipment {
	
	private int id;
	private String description;
	private float stock;
	private int supplierId;
	private float price;
	
	public Equipment(int id, String description, float stock, int supplierId, float price) {
		this.id = id;
		this.description = description;
		this.stock = stock;
		this.supplierId = supplierId;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public float getStock() {
		return stock;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public float getPrice() {
		return price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStock(float stock) {
		this.stock = stock;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
