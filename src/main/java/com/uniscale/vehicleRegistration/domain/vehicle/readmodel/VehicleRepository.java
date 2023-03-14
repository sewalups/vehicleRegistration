package com.uniscale.vehicleRegistration.domain.vehicle.readmodel;

import com.uniscale.vehicleRegistration.domain.vehicle.errors.VehicleNotFound;
import org.springframework.stereotype.Repository;
import ura.vehicleregistrationservice.vehicleregistration.vechicleregistration.Vehicle;

import java.util.*;

@Repository
public class VehicleRepository {
    public final Map<UUID, Vehicle> vehicles = new HashMap<>();

    public List<Vehicle> fetchAll(){
        return  vehicles.values().stream().toList();
    }

    public Optional<Vehicle> findByIdentifier(UUID identifier){
       return vehicles.values().stream().filter(v -> Objects.equals(v.getVehicleIdentifier(), identifier)).findFirst();
    }

    public Optional<Vehicle> findByPlateNumber(String plateNumber){
       return vehicles.values().stream().filter(v -> Objects.equals(v.getPlateNumber(), plateNumber)).findFirst();
    }

    public List<Vehicle> findVehiclesByOwner(UUID ownerId){
       return vehicles.values().stream().filter(v -> Objects.equals(v.getOwner(), ownerId)).toList();
    }

    public Vehicle getById(UUID identifier){
        return vehicles.get(identifier);
    }

    public UUID add(Vehicle vehicle){
        vehicles.put(vehicle.getVehicleIdentifier(), vehicle);
        return  vehicle.getVehicleIdentifier();
    }

    public Vehicle update(UUID identifier, Vehicle vehicle){
        var vehicleToUpdate = findByIdentifier(identifier);
        if(vehicleToUpdate.isEmpty()){
            throw new VehicleNotFound(identifier);
        }

        vehicles.replace(identifier, vehicle);
        return  vehicle;
    }


}
