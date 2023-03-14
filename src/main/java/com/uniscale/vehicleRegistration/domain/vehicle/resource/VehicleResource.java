package com.uniscale.vehicleRegistration.domain.vehicle.resource;

import com.uniscale.vehicleRegistration.domain.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ura.vehicleregistrationservice.vehicleregistration.vechicleregistration.Vehicle;
import ura.vehicleregistrationservice.vehicleregistration.vechicleregistration.VehicleValues;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/vehicle")
public class VehicleResource {
    private final VehicleService service;

    @Autowired
    public VehicleResource(VehicleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UUID> registerDrone(@RequestBody VehicleValues body) {
        var identifier = service.addVehicle(body);
        return new ResponseEntity<>(identifier, HttpStatus.OK);

    }

    @GetMapping(path = "/{vehicleId}/_lookup")
    public ResponseEntity<Vehicle> fetchVehicle(@PathVariable("vehicleId") UUID vehicleId) {
        var vehicle = service.fetchVehicle(vehicleId);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);

    }

    @PutMapping(path = "/{vehicleId}/_update")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("vehicleId") UUID vehicleId, @RequestBody VehicleValues vehicleValues) {
        var vehicle = service.updateVehicle(vehicleId, vehicleValues);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> fetchVehicle() {
        return new ResponseEntity<>(service.fetchAll(), HttpStatus.OK);
    }

}
