package kpi.model.entities;

import java.util.Arrays;

public enum UsersChoice {
	GENERATE_VALUES(1),
	INPUT_VALUES(2),
	SAVE(3),
	EXIT(4);

	int index = 0;

	UsersChoice(int index) {
		this.index = index;
	}

	public static UsersChoice getFromIndex(int index) {
		return Arrays.stream(UsersChoice.values())
			.filter(choice -> choice.index == index)
			.findFirst()
			.orElse(null);
	}
}