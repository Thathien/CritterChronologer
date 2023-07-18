package com.udacity.jdnd.course3.critter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.exception.PetException;
import com.udacity.jdnd.course3.critter.repository.PetRepository;

@Service
@Transactional
public class PetServives {

	@Autowired
	private PetRepository petRepository;

	public List<Pet> findAllPet() {
		return petRepository.findAll();
	}

	public Pet getPetByID(Long id) {
		return petRepository.findById(id).orElseThrow(() -> new PetException("Fet not found with id :" + id));
	}

	public Pet save(Pet pet) {
		Pet savePet = petRepository.save(pet);
		Customer customer = savePet.getCustomer();
		List<Pet> pets = customer.getPets();
		if (!pets.contains(savePet)) {
			pets.add(savePet);
			customer.setPets(pets);
		}

		return savePet;
	}
}
