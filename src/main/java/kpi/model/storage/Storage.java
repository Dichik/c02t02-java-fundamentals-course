package kpi.model.storage;

import kpi.model.entities.Flat;

import java.io.File;
import java.util.List;

public interface Storage {

	List<Flat> findAll();

	void save(File file, List<Flat> flats);

}