package com.tecsup.petclinic.web;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.service.OwnerService;

@RestController
public class OwnerController {
	
	@Autowired
	private OwnerService service;
	
	@PostMapping("/owners")
	@ResponseStatus(HttpStatus.CREATED)
	Owner create(@RequestBody Owner owner) {
		return service.create(owner);
	}
	
	@PutMapping("/owners/{id}")
	Owner saveOrUpdate(@RequestBody Owner newOwner,@PathVariable Long id) {
		Owner owner=null;
		try {
			owner = service.findById(id);
			owner.setAddress(newOwner.getAddress());
			owner.setCity(newOwner.getCity());
			owner.setFirst_name(newOwner.getFirst_name());
			owner.setLast_name(newOwner.getLast_name());
			owner.setTelephone(newOwner.getTelephone());
			service.update(owner);
		}catch(OwnerNotFoundException e) {
			owner = service.create(newOwner);
		}
		return null;
	}
	
	@DeleteMapping("/owners/{id}")
	ResponseEntity<String> delete(@PathVariable Long id){
		try {
			service.delete(id);
			return new ResponseEntity<>("" + id, HttpStatus.OK);
		} catch (OwnerNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}
