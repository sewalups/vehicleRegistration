package com.uniscale.vehicleRegistration.domain.owner.service;

import com.uniscale.vehicleRegistration.domain.owner.errors.OwnerNotFound;
import com.uniscale.vehicleRegistration.domain.owner.readmodel.OwnerRepository;
import com.uniscale.vehicleRegistration.domain.vehicle.errors.VehicleNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ura.vehicleregistrationservice.vehicleregistration.vechicleregistration.Owner;
import ura.vehicleregistrationservice.vehicleregistration.vechicleregistration.OwnerValues;

import java.util.List;
import java.util.UUID;

@Service
public class OwnerService {
    private final OwnerRepository repository;

    @Autowired
    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }

    public UUID addOwner(OwnerValues owner){
        var newOwner = Owner.builder()
                .ownerIdentifier(UUID.randomUUID())
                .fullname(owner.getFullname())
                .email(owner.getEmail())
                .tin(owner.getTin())
                .address(owner.getAddress())
                .build();

        return  repository.add(newOwner);
    }

    public List<Owner> fetchAll(){
        return repository.fetchAll();
    }

    public Owner fetchOwner(UUID identifier){
        return repository.findByIdentifier(identifier)
                .orElseThrow(() -> new OwnerNotFound(identifier));

    }

    public Owner fetchOwnerByEmail(String email){
        return repository.findByEmail(email)
                .orElseThrow(() -> new OwnerNotFound(email));

    }

    public Owner updateOwner(UUID identifier, OwnerValues owner){
        fetchOwner(identifier);
        var updatedOwner= Owner.builder()
                .ownerIdentifier(identifier)
                .fullname(owner.getFullname())
                .email(owner.getEmail())
                .tin(owner.getTin())
                .address(owner.getAddress())
                .build();

        return repository.update(identifier,updatedOwner);
    }
}
