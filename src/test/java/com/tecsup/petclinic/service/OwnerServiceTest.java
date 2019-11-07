package com.tecsup.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.service.OwnerService;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OwnerServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(PetServiceTest.class);
	
	@Autowired
	private OwnerService ownerService;
	
	@Test
	public void testFindOwnerByName() {
		String first_name = "George";
		int size_expected = 1;
		
		List<Owner> owners = ownerService.findByName(first_name);
		assertEquals(size_expected, owners.size());
	}
	
	@Test
	public void testCreateOwner(){
		long id = 11;
		String first_name = "William";
		String last_name = "Arevalo";
		String address = "Lima";
		String city= "Lima";
		String telephone = "963937997";
		
		Owner ow = new Owner(id, first_name, last_name, address, city, telephone);
		ow = ownerService.create(ow);
		logger.info("Insertado: "+ow);
		
	}
	
	@Test
	public void testUpdateOwner() {
		String first_name = "William";
		String last_name = "Arevalo";
		String address = "Lima";
		String city= "Lima";
		String telephone = "963937997";
		long create_id = -1;
		
		String up_first_name = "Juan";
		String up_last_name = "Perez";
		String up_address = "Callao";
		String up_city= "Lima";
		String up_telephone = "923487952";
		
		Owner owner = new Owner(first_name, last_name, address, city, telephone);
		logger.info(">" + owner);
		Owner readOwner = ownerService.create(owner);
		logger.info(">>"+ readOwner);
		
		create_id = readOwner.getId();
		
		readOwner.setAddress(up_address);
		readOwner.setCity(up_city);
		readOwner.setFirst_name(up_first_name);
		readOwner.setLast_name(up_last_name);
		readOwner.setTelephone(up_telephone);
		
		Owner upgradeOwner = ownerService.update(readOwner);
		logger.info(">>>>" + upgradeOwner);
		
		assertThat(create_id).isNotNull();
	}
	
	@Test
	public void testDeleteOwner() {
		String first_name = "Maria";
		String last_name = "Escobito";
		String address = "345 Maple St.";
		String city= "Madison";
		String telephone = "6085557683";
		
		Owner owner= new Owner(first_name, last_name, address, city, telephone);
		owner = ownerService.create(owner);
		logger.info(" "+owner);
		
		try {
			ownerService.delete(owner.getId());
		}catch(OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		
		try {
			ownerService.findById(owner.getId());
			assertTrue(false);
		}catch(OwnerNotFoundException e) {
			assertTrue(true);
		}
		
	}
	
	
	
}



