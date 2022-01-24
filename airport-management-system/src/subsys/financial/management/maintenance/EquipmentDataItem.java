package subsys.financial.management.maintenance;

/**
 * @author Alexander Leeb, k11702617
 */
public class EquipmentDataItem {
	
	private Equipment equipment;
	private float amount;
	private String shippingAddress;
	
	public EquipmentDataItem(Equipment equipment, float amount, String shippingAddress) {
		this.equipment = equipment;
		this.amount = amount;
		this.shippingAddress = shippingAddress;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public float getAmount() {
		return amount;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
}
