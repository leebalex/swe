package subsys.financial.management.maintenance;

/**
 * @author Alexander Leeb, k11702617
 */
public class Material {
	
	private int id;
	private String name;
	private float stock;
	private float price;
	
	public Material(int id, String name, float stock, float price) {
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getStock() {
		return stock;
	}

	public float getPrice() {
		return price;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStock(float stock) {
		this.stock = stock;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
