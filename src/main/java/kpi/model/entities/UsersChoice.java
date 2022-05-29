package kpi.model.entities;

import java.util.Arrays;

public enum UsersChoice {
	GENERATE_VALUES(1),
	INPUT_VALUES(2),
	SAVE(3),
	PRINT_FLATS(4),
	GET_FLATS_WITH_ROOMS(5),
	GET_FLATS_WITH_SQUARE(6),
	EXIT(7);

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