package kpi.model.storage;

import kpi.model.entities.Flat;
import kpi.model.helpers.FileEngine;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DataStorage implements Storage {

	private final FileEngine fileEngine;
	private File file;

	public DataStorage(File file) {
		this.file = file;
		fileEngine = new FileEngine();
	}

	@Override
	public List<Flat> findAll() {
		return fileEngine.parseFromJsonToFlats(file);
	}

	@Override
	public void save(File file, List<Flat> flats) {
		try (PrintWriter out = new PrintWriter(new FileWriter(file.getPath()))) {
			String jsonString = fileEngine.getJSONStringFrom(flats);
			out.write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}