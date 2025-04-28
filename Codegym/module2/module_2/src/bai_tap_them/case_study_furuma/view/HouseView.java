package bai_tap_them.case_study_furuma.view;

import bai_tap_them.case_study_furuma.utils.ValidationUtils;

public class HouseView extends FacilityView {
    public static String inputRoomStandard() {
        String roomStandard;
        do {
            System.out.print("Enter room standard: ");
            roomStandard = scanner.nextLine();
        } while (!ValidationUtils.validateNotEmpty(roomStandard, "Room standard cannot be empty, please re-enter."));
        return roomStandard;
    }

    public static int inputNumberOfFloors() {
        System.out.print("Enter number of floors (>0): ");
        return ValidationUtils.validateInt(1, Integer.MAX_VALUE);
    }
}
