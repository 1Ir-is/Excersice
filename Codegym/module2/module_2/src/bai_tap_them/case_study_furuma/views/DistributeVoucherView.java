package bai_tap_them.case_study_furuma.views;

import bai_tap_them.case_study_furuma.utils.ValidationUtils;

import java.util.Scanner;

public class DistributeVoucherView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputTenPercentVoucher() {
        System.out.print("Enter the number of 10% voucher: ");
        return ValidationUtils.validatePositiveInt("Voucher must be a non-negative number!");
    }
    public static int inputTwentyPercentVoucher() {
        System.out.print("Enter the number of 20% voucher: ");
        return ValidationUtils.validatePositiveInt("Voucher must be a non-negative number!");
    }
    public static int inputFiftyPercentVoucher() {
        System.out.print("Enter the number of 50% voucher: ");
        return ValidationUtils.validatePositiveInt("Voucher must be a non-negative number!");
    }

}
