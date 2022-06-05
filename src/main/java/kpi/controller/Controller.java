package kpi.controller;

import com.google.gson.JsonParseException;
import kpi.model.entities.Flat;
import kpi.model.exceptions.InvalidUserInputException;
import kpi.model.helpers.UsersChoice;
import kpi.model.services.FlatService;
import kpi.model.storage.DataStorage;
import kpi.view.FlatView;
import kpi.model.helpers.InputUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Controller {
	private static final Logger logger = LogManager.getLogger(Controller.class);

	private final FlatService service;
	private final FlatView flatView;

	public Controller() {
		logger.debug("Initialization in [Controller] started.");

		this.flatView = new FlatView();

		File file = null;
		try {
			file = loadFileFromResources();
		} catch (IOException e) {
			flatView.printMessage(FlatView.FAILED_TO_LOAD_DATA);
			logger.error("Couldn't load data from the file...");
			System.exit(0);
		}
		this.service = new FlatService(new DataStorage(file));
	}

	public void start() {
		logger.debug("App was started.");

		try {
			performOperations();

			flatView.bye();
			logger.debug("App was finished successfully.");
		} catch (JsonParseException e) {
			flatView.printMessage(FlatView.PROBLEM_WITH_PARSING);
			logger.error("Some troubles with parsing data.");
		} catch (IOException e) {
			flatView.printMessage(FlatView.PROBLEM_WITH_STORAGE);
			logger.error("Some troubles with storage.");
		}

		logger.error("App was not finished successfully.");
	}

	private void performOperations() throws IOException, JsonParseException {
		flatView.printMessage(FlatView.CURRENT_LIST_OF_FLATS);
		flatView.printFlats(service.getAllFlats());

		UsersChoice choice = null;
		List<Flat> currentFlats = new ArrayList<>();
		do {
			try {
				choice = flatView.getUsersChoice();
			} catch (InvalidUserInputException e) {
				flatView.printMessage("Note: you can enter only valid integer. Please try again.");
				continue;
			}
			switch (choice) {
				case GENERATE_VALUES -> currentFlats = InputUtility.generateValues();
				case PRINT_FLATS -> flatView.printFlats(service.getAllFlats());
				case GET_FLATS_WITH_ROOMS -> {
					int numberOfRooms = flatView.getNumberOfRooms();
					currentFlats = service.getFlatsWithNRooms(numberOfRooms);
				}
				case GET_FLATS_WITH_SQUARE -> {
					double minSquare = flatView.getMinSquare();
					int minFloor = flatView.getMinFloor();
					currentFlats = service.getFlatsWithSquare(minSquare, minFloor);
				}
				default -> {
					if(!currentFlats.isEmpty()) {
						currentFlats.addAll(service.getAllFlats());
						service.saveAll(currentFlats);
						flatView.printMessage(FlatView.SAVED_SUCCESSFULLY);
						currentFlats.clear();
					}
				}
			}
			if(currentFlats.isEmpty()) {
				flatView.printMessage(FlatView.EMPTY_LIST);
			} else {
				flatView.printFlats(currentFlats);
			}
		} while (choice != UsersChoice.EXIT);
	}

	private File loadFileFromResources() throws IOException {
		Properties properties = new Properties();
		FileReader fileReader = new FileReader("src/main/resources/application.properties");
		properties.load(fileReader);
		return new File(properties.getProperty("main-db"));
	}

}