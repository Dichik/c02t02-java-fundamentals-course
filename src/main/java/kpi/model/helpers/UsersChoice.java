package kpi.model.helpers;

import java.util.Arrays;

public enum UsersChoice {
	GENERATE_VALUES(1),
	SAVE(2),
	PRINT_FLATS(3),
	GET_FLATS_WITH_ROOMS(4),
	GET_FLATS_WITH_SQUARE(5),
	EXIT(6);

	final int index;

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