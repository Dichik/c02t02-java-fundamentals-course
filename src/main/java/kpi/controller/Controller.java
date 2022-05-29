package kpi.controller;

import kpi.model.entities.Flat;
import kpi.model.entities.UsersChoice;
import kpi.model.exceptions.InvalidUserInputException;
import kpi.model.services.FlatService;
import kpi.model.storage.DataStorage;
import kpi.view.CalculateView;
import kpi.view.InputUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

	private FlatService service;
	private final CalculateView calculateView;
	private Boolean doStop = false;

	private final Scanner scanner = new Scanner(System.in);
	private static final Logger logger = LogManager.getLogger(Controller.class);

	public Controller() {
		logger.debug("Initialization in [Controller] started.");
		calculateView = new CalculateView();
	}

	public void start() {
		logger.info("App was started.");

		File file;
		while((file = calculateView.getFile()) == null) {
			System.err.println("Invalid name of the file. Please try again.");
		}
		DataStorage dataStorage = new DataStorage(file);
		service = new FlatService(dataStorage);

		List<Flat> currentFlats = new ArrayList<>(service.getAllFlats());
		do {
			try {
				boolean performAnotherOperation = calculateView.isPerformingAnotherOperation();

				if(!(doStop = !performAnotherOperation)) {
					UsersChoice choice = calculateView.getUsersChoice();
					switch (choice) {
						case GENERATE_VALUES -> {
							List<Flat> generatedFlats = InputUtility.generateValues();
							calculateView.printFlats(generatedFlats);
							currentFlats.addAll(generatedFlats);
						}
						case INPUT_VALUES -> {
							List<Flat> enteredFlats = InputUtility.enterValues();
							calculateView.printFlats(enteredFlats);
							currentFlats.addAll(enteredFlats);
						}
						case SAVE -> {
							service.saveAll(file, currentFlats);
							calculateView.printMessage(CalculateView.SAVED_SUCCESSFULLY);
						}
						case PRINT_FLATS -> calculateView.printFlats(currentFlats);
						case GET_FLATS_WITH_ROOMS -> {
							try {
								System.out.println("Please enter number of rooms:");
								int n = scanner.nextInt();
								List<Flat> flatsWithNRooms = service.getFlatsWithNRooms(n);
								if(flatsWithNRooms.isEmpty()) {
									calculateView.printMessage("We didn't find satisfied data.");
								} else calculateView.printFlats(flatsWithNRooms);
							} catch (Exception e) {
								logger.info("Entered invalid values for getting flats with no. rooms.");
								throw new InvalidUserInputException();
							}
						}
						case GET_FLATS_WITH_SQUARE -> {
							try {
								System.out.println("Please enter minSquare:");
								double minSquare = scanner.nextDouble();
								System.out.println("Please enter minFloor:");
								int minFloor = scanner.nextInt();
								List<Flat> flatsWithSquare = service.getFlatsWithSquare(minSquare, minFloor);
								if(flatsWithSquare.isEmpty()) {
									calculateView.printMessage("We didn't find satisfied data.");
								} else calculateView.printFlats(flatsWithSquare);
							} catch (Exception e) {
								logger.info("Entered invalid values for getting flats with min square and floor.");
								throw new InvalidUserInputException();
							}
						}
						case EXIT -> {
							this.doStop = true;
						}
					}
				}
			} catch (InvalidUserInputException e) {
				System.err.println("You input is invalid.");
			}

			if(doStop) {
				service.saveAll(file, currentFlats);
				logger.info("All flats were saved successfully.");
				calculateView.printMessage(CalculateView.SAVED_SUCCESSFULLY);
			}
		} while (!doStop);

		calculateView.bye();
		logger.info("App was finished successfully.");
	}

}