package com.lams1989.rest.exercise.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.springframework.stereotype.Component;

import com.lams1989.rest.exercise.beans.ParkingCar;

@Component
public class ParkingCarDaoService {
	private static List<ParkingCar> cars = new ArrayList<>();

	private static int carsCount = 5;
	static {
		cars.add(new ParkingCar(1, "jpa123", "Chevrolet", "luis", new Date(), 200L));
		cars.add(new ParkingCar(2, "mvn456", "Ford", "pedro", new Date(), 400L));
		cars.add(new ParkingCar(3, "xxp025", "Susuki", "juan", new Date(), 2000L));
		cars.add(new ParkingCar(4, "noq762", "yamaha", "augusto", new Date(), 500L));
		cars.add(new ParkingCar(5, "mvc567", "Renault", "amiliano", new Date(), 300L));
	}

	public List<ParkingCar> findAll() {

		return cars;
	}

	public ParkingCar findOne(int id) {
		for (ParkingCar car : cars) {
			if (car.getId() == id) {
				return car;
			}
		}
		return null;
	}

	public ParkingCar save(ParkingCar car) {
		if(car.getId()==null) {
			car.setId(++carsCount);
		}
		cars.add(car);
		return car;
	}

	public ParkingCar deleteById(int id) {
		Iterator<ParkingCar> iterator = cars.iterator();
		while (iterator.hasNext()) {
			ParkingCar car=iterator.next();
			if(car.getId()==id) {
				iterator.remove();
				return car;
			}
		}
		return null;
	}

}
