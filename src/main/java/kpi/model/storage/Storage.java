package kpi.model.storage;

import kpi.model.entities.Flat;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Storage {

	List<Flat> getAllFlatsFromFile() throws IOException;

	void saveAllFlatsToFile(List<Flat> flats) throws IOException;

}