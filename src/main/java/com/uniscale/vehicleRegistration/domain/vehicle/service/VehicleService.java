package com.uniscale.vehicleRegistration.domain.vehicle.service;

import com.uniscale.vehicleRegistration.domain.vehicle.errors.VehicleNotFound;
import com.uniscale.vehicleRegistration.domain.vehicle.readmodel.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ura.vehicleregistrationservice.vehicleregistration.vechicleregistration.Vehicle;
import ura.vehicleregistrationservice.vehicleregistration.vechicleregistration.VehicleValues;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {
    private final VehicleRepository repository;

    @Autowired
    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public UUID addVehicle(VehicleValues vehicle){
        var newVehicle = Vehicle.builder()
                .vehicleIdentifier(UUID.randomUUID())
                .color(vehicle.getColor())
                .make(vehicle.getMake())
                .model(vehicle.getModel())
                .owner(vehicle.getOwner())
                .plateNumber(vehicle.getPlateNumber())
                .yearOfManufacture(vehicle.getYearOfManufacture())
                .createdAt(LocalDateTime.now())
                .build();

        return  repository.add(newVehicle);
    }

    public List<Vehicle> fetchAll(){
        return repository.fetchAll();
    }

    public Vehicle fetchVehicle(UUID identifier){
       return repository.findByIdentifier(identifier)
                .orElseThrow(() -> new VehicleNotFound(identifier));

    }

    public Vehicle updateVehicle(UUID identifier, VehicleValues vehicle){
        fetchVehicle(identifier);
        var updatedVehicle = Vehicle.builder()
                .vehicleIdentifier(identifier)
                .color(vehicle.getColor())
                .make(vehicle.getMake())
                .model(vehicle.getModel())
                .owner(vehicle.getOwner())
                .plateNumber(vehicle.getPlateNumber())
                .yearOfManufacture(vehicle.getYearOfManufacture())
                .createdAt(vehicle.getCreatedAt())
                .build();

        return repository.update(identifier,updatedVehicle);
    }
}
