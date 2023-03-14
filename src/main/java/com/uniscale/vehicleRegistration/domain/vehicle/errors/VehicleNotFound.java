package com.uniscale.vehicleRegistration.domain.vehicle.errors;

import java.util.UUID;

public class VehicleNotFound extends RuntimeException {
    public VehicleNotFound(UUID identifier) {
        super(String.format("vehicle %s does not exist", identifier));
    }
}
