package com.lams1989.rest.exercise.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lams1989.rest.exercise.beans.ParkingCar;
import com.lams1989.rest.exercise.exceptions.CarNotFoundException;
import com.lams1989.rest.exercise.service.ParkingCarDaoService;

@RestController
public class ParkingCarControle {

	@Autowired
	private ParkingCarDaoService service;
	
	@Autowired
	private MessageSource message;
	Logger logger = LoggerFactory.getLogger(ParkingCarControle.class);

	@GetMapping("/parkingcars")
	public List<ParkingCar> retrieveAllUsers() {
		logger.info(message.getMessage("info.parking.car.getall.info", null, LocaleContextHolder.getLocale()));
		return service.findAll();
	}

	@GetMapping("/parkingcars/{id}")
	public EntityModel<ParkingCar> retrieveCar(@PathVariable int id) {
		logger.info(message.getMessage("info.parking.car.getid.info", null, LocaleContextHolder.getLocale()));
		ParkingCar car = service.findOne(id);
		if (car == null) {
			throw new CarNotFoundException("id-:" + id);
		}
		EntityModel<ParkingCar> resource = EntityModel.of(car);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-cars"));

		return resource;

	}
	
	@DeleteMapping("/parkingcars/{id}")
	public void deleteCar(@PathVariable int id) {
		logger.info(message.getMessage("info.parking.car.deleteporid.info", null, LocaleContextHolder.getLocale()));
		ParkingCar car = service.deleteById(id);

		if (car == null) {
			throw new CarNotFoundException("id-:" + id);
		}
	}

	@PostMapping("/parkingcars")
	public ResponseEntity<Object> createParkingCar(@Valid @RequestBody ParkingCar car) {
		logger.info(message.getMessage("info.parking.car.created.info", null, LocaleContextHolder.getLocale()));
		ParkingCar savedCar = service.save(car);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCar.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/parkingcars/{id}")
	public void upDatecar(@PathVariable int id, @Valid @RequestBody ParkingCar car) {
		logger.info(message.getMessage("info.parking.car.update.info", null, LocaleContextHolder.getLocale()));
		ParkingCar carActual = service.findOne(id);

		if (carActual == null) {
			throw new CarNotFoundException("id-:" + id);
		}
		
		carActual.setOwnersname(car.getOwnersname());
		carActual.setHourin(car.getHourin());
		carActual.setBrand(car.getBrand());
		carActual.setRegistry(car.getRegistry());
		carActual.setPayment(car.getPayment());
	}
}
