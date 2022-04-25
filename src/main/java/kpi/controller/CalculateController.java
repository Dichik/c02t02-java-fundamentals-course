package kpi.controller;

import kpi.model.CalculateModel;
import kpi.view.CalculateView;
import kpi.view.InputUtility;

public class CalculateController {

	private CalculateModel model = new CalculateModel();
	private CalculateView calculateView = new CalculateView();
	private InputUtility input = new InputUtility(calculateView);

	public void calculate() {
		while(true) {
			model.setFlats(input.inputValues());
			calculateView.printFlats(model.getFlats());
//			int usersOption = input.inputOperationId();
//			if(usersOption == 1)
		}
	}

}