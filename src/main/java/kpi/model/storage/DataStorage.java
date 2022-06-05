package kpi.model.storage;

import com.google.gson.JsonParseException;
import kpi.model.entities.Flat;
import kpi.model.helpers.FileEngine;

import java.io.*;
import java.util.List;

public class DataStorage implements Storage {

	private final FileEngine fileEngine;
	private final File source;

	public DataStorage(File source) {
		this.source = source;
		fileEngine = new FileEngine();
	}

	@Override
	public List<Flat> getAllFlatsFromFile() throws IOException, JsonParseException {
		return fileEngine.parseFromJsonToFlats(source);
	}

	@Override
	public void saveAllFlatsToFile(List<Flat> flats) throws IOException, JsonParseException {
		FileWriter fw = new FileWriter(source);
		String jsonString = fileEngine.getJSONStringFrom(flats);
		BufferedWriter writer = new BufferedWriter(fw);
		writer.write(jsonString);
		writer.close();
	}
}