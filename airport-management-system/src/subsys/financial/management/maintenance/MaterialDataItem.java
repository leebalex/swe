package subsys.financial.management.maintenance;

/**
 * @author Alexander Leeb, k11702617
 */
public class MaterialDataItem {
	
	private Material material;
	private float amount;
	private String shippingAddress;
	
	public MaterialDataItem(Material material, float amount, String shippingAddress) {
		this.material = material;
		this.amount = amount;
		this.shippingAddress = shippingAddress;
	}

	public Material getMaterial() {
		return material;
	}

	public float getAmount() {
		return amount;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public String toCSVString() {
		return material.getId() + ";" + material.getName() + ";" + material.getStock() + ";" + material.getPrice() + ";" + amount + ";" + shippingAddress + ";\n";
	}
}
