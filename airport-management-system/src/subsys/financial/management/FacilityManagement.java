package subsys.financial.management;

import subsys.financial.management.facility.Facility;

/**
 * @author Alexander Leeb, k11702617
 */
public interface FacilityManagement {
	
	/**
	 * Registers a new facility for the terminal by writing facility data to designated file
	 * The file facilitydata.csv stores data for facilities like address, rent, description
	 * @param facility The facility to register
	 * @return true for successful registration
	 * @throws IllegalArgumentException if parameter is null
	 */
	public boolean registerFacility(Facility facility) throws IllegalArgumentException;
	
	/**
	 * Unregisters a existing facility by deleting facility data from designated file
	 * @param facilityId The facilityId to delete
	 * @return true for successful deletion
	 * @throws IllegalArgumentException if facilityId is not registered
	 */
	public boolean unregisterFacility(int facilityId) throws IllegalArgumentException;

	/**
	 * Calculates this years facility costs until current date
	 * @param facilityId The facilityId to calculate the cost for
	 * @return float costs
	 * @throws IllegalArgumentException if facilityId is not registered
	 */
	public float calculateFacilityCost(int facilityId) throws IllegalArgumentException;
}
