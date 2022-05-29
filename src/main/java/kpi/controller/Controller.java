package kpi.controller;

import kpi.model.entities.Flat;
import kpi.model.entities.UsersChoice;
import kpi.model.exceptions.InvalidUserInputException;
import kpi.model.services.FlatService;
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
		// do some operation with file saving
		calculateView = new CalculateView();
	}

	public void start() {
		logger.info("App was started.");

		// TODO load list of available list of items
		//  and add view print current list of flats
		List<Flat> currentFlats = new ArrayList<>();
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
							service.saveAll(null, currentFlats);
							currentFlats.clear();
						}
						case PRINT_FLATS -> {
							List<Flat> allFlats = service.getAllFlats();
							allFlats.addAll(currentFlats);
							calculateView.printFlats(allFlats);
						}
						case GET_FLATS_WITH_ROOMS -> {
							try {
								int n = scanner.nextInt();
								List<Flat> flatsWithNRooms = service.getFlatsWithNRooms(n);
								calculateView.printFlats(flatsWithNRooms);
							} catch (Exception e) {
								logger.info("Entered invalid values for getting flats with no. rooms.");
								throw new InvalidUserInputException();
							}
						}
						case GET_FLATS_WITH_SQUARE -> {
							try {
								System.out.println("Please enter minSquare:");
								double minSquare = scanner.nextDouble();
								System.out.println("Please enter minSquare:");
								int minFloor = scanner.nextInt();
								List<Flat> flatsWithSquare = service.getFlatsWithSquare(minSquare, minFloor);
								calculateView.printFlats(flatsWithSquare);
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
				File fileToSave = calculateView.getFileForSaving();
				if(fileToSave != null) {
					service.saveAll(null, currentFlats);
					logger.info("All flats were saved successfully.");
					calculateView.printMessage(CalculateView.SAVED_SUCCESSFULLY);
				}
			}
		} while (!doStop);

		logger.info("App was finished successfully.");
	}

}