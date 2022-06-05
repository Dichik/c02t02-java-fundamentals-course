package kpi.model.services;

import com.google.gson.JsonParseException;
import kpi.model.entities.Flat;
import kpi.model.storage.DataStorage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FlatService {
	private DataStorage dataStorage;

	public FlatService(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}

	public List<Flat> getAllFlats() throws IOException, JsonParseException {
		return dataStorage.getAllFlatsFromFile();
	}

	public List<Flat> getFlatsWithNRooms(int numberOfRooms) throws IOException, JsonParseException {
		List<Flat> flats = getAllFlats();
		return flats.stream()
			.filter(flat -> flat.getTotalRooms() == numberOfRooms)
			.collect(Collectors.toList());
	}

	public List<Flat> getFlatsWithSquare(double minSquare, int minFloor) throws IOException, JsonParseException {
		List<Flat> flats = getAllFlats();
		return flats.stream()
			.filter(flat -> flat.getSquare() >= minSquare)
			.filter(flat -> flat.getFloor() >= minFloor)
			.collect(Collectors.toList());
	}

	public void saveAll(List<Flat> flats) throws IOException, JsonParseException {
		dataStorage.saveAllFlatsToFile(flats);
	}

}