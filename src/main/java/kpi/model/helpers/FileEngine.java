package kpi.model.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kpi.model.entities.Flat;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileEngine {

	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Gson gson = new Gson();

	private final Logger logger = Logger.getLogger(FileEngine.class);

	public List<Flat> parseFromJsonToFlats(File file) {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(file.getPath()));
			return gson.fromJson(reader, new TypeToken<List<Flat>>() {}.getType());
		} catch (IOException e) {
			logger.error(String.format("Error to parse data from %s.json file.", file.getName()));
		}
		return null;
	}

	public String getJSONStringFrom(List<Flat> flats) {
		return gson.toJson(flats);
	}
}