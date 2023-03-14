package com.uniscale.vehicleRegistration.domain.owner.readmodel;

import com.uniscale.vehicleRegistration.domain.owner.errors.OwnerNotFound;
import org.springframework.stereotype.Repository;
import ura.vehicleregistrationservice.vehicleregistration.vechicleregistration.Owner;

import java.util.*;

@Repository
public class OwnerRepository {
    public final Map<UUID, Owner> owners = new HashMap<>();

    public List<Owner> fetchAll(){
        return  owners.values().stream().toList();
    }

    public Optional<Owner> findByIdentifier(UUID identifier){
        return owners.values().stream().filter(v -> Objects.equals(v.getOwnerIdentifier(), identifier)).findFirst();
    }

    public Optional<Owner> findByEmail(String email){
        return owners.values().stream().filter(v -> Objects.equals(v.getEmail(), email)).findFirst();
    }

    public Owner getById(UUID identifier){
        return owners.get(identifier);
    }

    public UUID add(Owner owner){
        owners.put(owner.getOwnerIdentifier(), owner);
        return  owner.getOwnerIdentifier();
    }

    public Owner update(UUID identifier, Owner owner){
        var ownerToUpdate = findByIdentifier(identifier);
        if(ownerToUpdate.isEmpty()){
            throw new OwnerNotFound(identifier);
        }

        owners.replace(identifier, owner);
        return  owner;
    }
}
