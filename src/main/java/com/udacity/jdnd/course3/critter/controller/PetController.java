package com.udacity.jdnd.course3.critter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.services.CustomerServives;
import com.udacity.jdnd.course3.critter.services.PetServives;
import com.udacity.jdnd.course3.critter.utils.ConvertDTO;

@RestController
@RequestMapping("/pet")
public class PetController {

	@Autowired
	private PetServives petService;

	@Autowired
	private CustomerServives customerService;

	@PostMapping
	public PetDTO savePet(@RequestBody PetDTO petDTO) {
		Pet pet = convertPetDTOToPet(petDTO);
		pet = petService.save(pet);
		return ConvertDTO.convertPetToPetDTO(pet);
	}

	@GetMapping("/{petId}")
	public PetDTO getPet(@PathVariable long petId) {
		Pet pet = petService.getPetByID(petId);
		return ConvertDTO.convertPetToPetDTO(pet);
	}

	@GetMapping
	public List<PetDTO> getPets() {
		throw new UnsupportedOperationException();
	}

	@GetMapping("/owner/{ownerId}")
	public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
		Customer customer = customerService.findCustomerById(ownerId);
		List<Pet> pets = customer.getPets();
		List<PetDTO> PetDTOs = new ArrayList<>();
		for (Pet pet : pets) {
			PetDTOs.add(ConvertDTO.convertPetToPetDTO(pet));
		}
		return PetDTOs;
	}

	public Pet convertPetDTOToPet(PetDTO petDTO) {
		Pet pet = new Pet();
		BeanUtils.copyProperties(petDTO, pet);
		pet.setCustomer(customerService.findCustomerById(petDTO.getOwnerId()));
		return pet;

	}
}
