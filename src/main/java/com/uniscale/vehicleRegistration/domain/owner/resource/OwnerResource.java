package com.uniscale.vehicleRegistration.domain.owner.resource;

import com.uniscale.vehicleRegistration.domain.owner.service.OwnerService;
import com.uniscale.vehicleRegistration.domain.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ura.vehicleregistrationservice.vehicleregistration.vechicleregistration.Owner;
import ura.vehicleregistrationservice.vehicleregistration.vechicleregistration.OwnerValues;
import ura.vehicleregistrationservice.vehicleregistration.vechicleregistration.Vehicle;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/owner")
public class OwnerResource {
    private final OwnerService service;
    private final VehicleService vehicleService;

    @Autowired
    public OwnerResource(OwnerService service, VehicleService vehicleService) {
        this.service = service;
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<UUID> register(@RequestBody OwnerValues body) {
        var identifier = service.addOwner(body);
        return new ResponseEntity<>(identifier, HttpStatus.OK);

    }

    @GetMapping(path = "/{ownerId}/_lookup")
    public ResponseEntity<Owner> fetchOwner(@PathVariable("ownerId") UUID ownerId) {
        var owner = service.fetchOwner(ownerId);
        return new ResponseEntity<>(owner, HttpStatus.OK);

    }

    @GetMapping(path = "/{ownerId}/_vehicles")
    public ResponseEntity<List<Vehicle>> fetchVehicles(@PathVariable("ownerId") UUID ownerId) {
        var vehicles = vehicleService.fetchVehiclesByOwner(ownerId);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);

    }

    @GetMapping(path = "/_search")
    public ResponseEntity<Owner> fetchOwnerByEmail(@RequestBody OwnerSearch body) {
        var owner = service.fetchOwnerByEmail(body.email());
        return new ResponseEntity<>(owner, HttpStatus.OK);

    }

    @PutMapping(path = "/{ownerId}/_update")
    public ResponseEntity<Owner> updateOwner(@PathVariable("ownerId") UUID ownerId, @RequestBody OwnerValues ownerValues) {
        var owner = service.updateOwner(ownerId, ownerValues);
        return new ResponseEntity<>(owner, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Owner>> fetchOwners() {
        return new ResponseEntity<>(service.fetchAll(), HttpStatus.OK);
    }
}
