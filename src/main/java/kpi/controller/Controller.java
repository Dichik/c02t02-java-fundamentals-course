package kpi.controller;

import kpi.model.entities.UsersChoice;
import kpi.model.exceptions.InvalidUserInputException;
import kpi.model.services.FlatService;
import kpi.view.CalculateView;
import kpi.view.InputUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Controller {

	private FlatService service;
	private CalculateView calculateView = new CalculateView();
//	private InputUtility input = new InputUtility(calculateView);
	private static final Logger logger = LogManager.getLogger(Controller.class);

	public Controller() {
		logger.debug("Initialization in [Controller] started.");
		// do some operation with file saving
	}

	public void start() {
		logger.info("App was started.");

		// view print current list of flats

		while(true) {
			try {
				boolean performAnotherOperation = calculateView.isPerformingAnotherOperation();
				if(!performAnotherOperation) {
					service.saveAll(null, null);
					calculateView.bye();
					break;
				}

				// ask about operation type
				UsersChoice usersChoice = calculateView.getUsersChoice();

				// print customer list [view]

				// check if we want to save data

			} catch (InvalidUserInputException e) {
				System.err.println("You input is invalid.");
			}
//			model.setFlats(input.inputValues());
//			calculateView.printFlats(model.getAllFlats());
//			int usersOption = input.inputOperationId();
//			if(usersOption == 1)
		}
		logger.info("App was finished successfully.");
	}

}