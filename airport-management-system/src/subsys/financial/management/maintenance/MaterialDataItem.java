package subsys.financial.management.maintenance;

import subsys.financial.utils.DataItem;

/**
 * @author Alexander Leeb, k11702617
 */
public class MaterialDataItem extends DataItem {
	
	private Material material;
	private double amount;
	private String shippingAddress;
	
	public MaterialDataItem(Material material, double amount, String shippingAddress) {
		this.material = material;
		this.amount = amount;
		this.shippingAddress = shippingAddress;
	}

	public Material getMaterial() {
		return material;
	}

	public double getAmount() {
		return amount;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	@Override
	public String toString() {
		return "MaterialDataItem [material=" + material + ", amount=" + amount + ", shippingAddress=" + shippingAddress
				+ "]";
	}

	public String toCSVString() {
		return material.getId() + ";" + material.getName() + ";" + material.getStock() + ";" + material.getPrice() + ";" + amount + ";" + shippingAddress + ";\n";
	}
}
