package subsys.financial.management;

import java.io.IOException;
import java.util.List;

import subsys.financial.management.maintenance.MaintenanceDataItem;
import subsys.financial.management.maintenance.MaterialDataItem;

/**
 * @author Alexander Leeb, k11702617
 */
public interface MaintenanceManagement {
	
	/**
	 * Writes maintenance data to a designated file
	 * @param maintenanceDataItems
	 * @return true if successful
	 * @throws IllegalArgumentException if maintenanceDataItems is null
	 */
	public boolean writeMaintenanceData(List<MaintenanceDataItem> maintenanceDataItems) throws IllegalArgumentException, IOException;
	
	/**
	 * Builds a maintenance report by generating a list of MaintenanceDataItems
	 * @return List<MaintenanceDataItem> the individual entities under maintenance demand
	 */
	public List<MaintenanceDataItem> reportMaintenanceDemand();
	
	/**
	 * Builds a material report by generating a list of MaterialDataItems
	 * @return List<MaterialDataItem> the individual materials demanded to be ordered
	 */
	public List<MaterialDataItem> reportMaterialDemand();
	
	/**
	 * Builds a equipment report by generating a list of EquipmentDataItems
	 * @return List<EquipmentDataItem> the individual materials demanded to be ordered
	 */
	public List<MaintenanceDataItem> reportEquipmentDemand();
}
