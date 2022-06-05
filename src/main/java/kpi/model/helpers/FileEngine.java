package kpi.model.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import kpi.model.entities.Flat;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileEngine {

	private final Gson gson = new Gson();

	public List<Flat> parseFromJsonToFlats(File file) throws IOException, JsonParseException {
		Reader reader = Files.newBufferedReader(Paths.get(file.getPath()));
		return gson.fromJson(reader, new TypeToken<List<Flat>>() {}.getType());
	}

	public String getJSONStringFrom(List<Flat> flats) throws JsonParseException {
		return gson.toJson(flats);
	}
}