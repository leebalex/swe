/*
 * @author Florian Engertsberger
 */

package subsys.landside;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LandsideManagementSystem {
    private final List<Vehicle> vehicles;
    private final List<Worker> workers;
    private final List<SecurityInformation> securityInformations;
    private final List<ParkingSpot> parkingSpots;
    private final LandsideDataStorage storage;

    public LandsideManagementSystem() {
        this.storage = new LandsideDataStorage();
        this.vehicles = new ArrayList<>();
        this.workers = new ArrayList<>();
        this.securityInformations = new ArrayList<>();
        this.parkingSpots = new ArrayList<>();
    }

    public List<Vehicle> getVehicles() {
        return Collections.unmodifiableList(vehicles);
    }

    public List<Worker> getWorkers() {
        return Collections.unmodifiableList(workers);
    }

    public List<SecurityInformation> getSecurityInformations() {
        return Collections.unmodifiableList(securityInformations);
    }

    public List<ParkingSpot> getParkingSpots() {
        return Collections.unmodifiableList(parkingSpots);
    }

    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    public void addWorker(Worker worker){
        workers.add(worker);
    }

    public void addSecurityInformation(SecurityInformation securityInformation){
        securityInformations.add(securityInformation);
    }

    public void addParkingSpot(ParkingSpot parkingSpot){
        parkingSpots.add(parkingSpot);
    }

    public List<List> getLandsideData(){
        return null;
    }

    public boolean transport(){
        return true;
    }

    public boolean fuelAirplane(){
        return true;
    }

    public boolean relocateVehicle(){
        return true;
    }

}
