package com.tecsup.petclinic.domain;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
	
	// Fetch pets by name
		List<Owner> findByName(String first_name);
}
