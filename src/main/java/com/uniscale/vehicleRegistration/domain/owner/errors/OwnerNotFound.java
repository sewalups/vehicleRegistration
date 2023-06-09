package com.uniscale.vehicleRegistration.domain.owner.errors;

import java.util.UUID;

public class OwnerNotFound extends RuntimeException {
    public OwnerNotFound(UUID identifier) {
        super(String.format("owner %s does not exist", identifier));
    }
    public OwnerNotFound(String email) {
        super(String.format("owner with email %s does not exist", email));
    }
}
