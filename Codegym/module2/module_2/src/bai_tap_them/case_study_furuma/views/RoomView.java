package bai_tap_them.case_study_furuma.views;

import bai_tap_them.case_study_furuma.utils.ValidationUtils;

public class RoomView extends FacilityView {
    public static String inputFreeService() {
        String freeService;
        do {
            System.out.print("Enter free service: ");
            freeService = scanner.nextLine();
        } while (!ValidationUtils.validateNotEmpty(freeService, "Free service cannot be empty, please re-enter."));
        return freeService;
    }
}
