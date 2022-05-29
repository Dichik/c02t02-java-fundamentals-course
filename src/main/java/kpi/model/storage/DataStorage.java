package kpi.model.storage;

import kpi.model.entities.Flat;
import kpi.model.helpers.FileEngine;

import java.io.File;
import java.util.List;

public class DataStorage implements Storage {

	private final FileEngine fileEngine;

	public DataStorage() {
		fileEngine = new FileEngine();
	}

	@Override
	public List<Flat> findAll() {
		return null;
	}

	@Override
	public void save(File file, List<Flat> flats) {

	}
}