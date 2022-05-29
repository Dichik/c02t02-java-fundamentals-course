package kpi.model.services;

import kpi.model.entities.Flat;
import kpi.model.storage.DataStorage;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatService {
	private DataStorage dataStorage;

	public FlatService(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}

	// add hashing to get flats without accessing to db

	public List<Flat> getAllFlats() {
		return null;
	}

	public List<Flat> getFlatsWithNRooms(int numberOfRooms) {
		List<Flat> flats = getAllFlats();
		return flats.stream()
			.filter(flat -> flat.getTotalRooms() == numberOfRooms)
			.collect(Collectors.toList());
	}

	public List<Flat> getFlatsWithSquare(double minSquare, int minFloor) {
		List<Flat> flats = getAllFlats();
		return flats.stream()
			.filter(flat -> flat.getSquare() >= minSquare)
			.filter(flat -> flat.getFloor() >= minFloor)
			.collect(Collectors.toList());
	}

	public void saveAll(File file, List<Flat> flats) {

	}

}