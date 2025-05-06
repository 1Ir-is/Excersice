package bai_tap_them.case_study_furuma.services.promotion;

import bai_tap_them.case_study_furuma.models.Customer;
import bai_tap_them.case_study_furuma.repositories.promotion.IPromotionRepository;
import bai_tap_them.case_study_furuma.views.DistributeVoucherView;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class PromotionService implements IPromotionService {

    private final IPromotionRepository promotionRepository;
    private final Scanner scanner = new Scanner(System.in);

    public PromotionService(IPromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public void displayCustomersByYear() {
        System.out.print("Enter the year to filter customer: ");
        int year = Integer.parseInt(scanner.nextLine());
        List<Customer> customerList = promotionRepository.findCustomerByYear(year);
        if (customerList.isEmpty()) {
            System.out.println("No customer found in given year!");
        } else {
            System.out.println("Customer who used service in " + year + ":");
            for (Customer customer : customerList) {
                System.out.println(customer.getDetails());
                System.out.println();
            }
        }
    }

    @Override
    public void distributeVouchers() {
        int tenPercent = DistributeVoucherView.inputTenPercentVoucher();
        int twentyPercent = DistributeVoucherView.inputTwentyPercentVoucher();
        int fiftyPercent = DistributeVoucherView.inputFiftyPercentVoucher();

        int totalVoucher = tenPercent + twentyPercent + fiftyPercent;
        int currentMonth = LocalDate.now().getMonthValue();

        Stack<Customer> customerStack;

        try {
            customerStack = promotionRepository.getCustomerForVoucher(currentMonth, totalVoucher);
        } catch (RuntimeException e) {
            System.out.println("Error retrieving customers for voucher distribution: " + e.getMessage());
            return;
        }

        if (customerStack.isEmpty()) {
            System.out.println("No customers eligible for voucher distribution in current month.");
            return;
        }

        System.out.println("Distributing Vouchers....");
        while (!customerStack.isEmpty()) {
            Customer customer = customerStack.pop();
            if (tenPercent > 0) {
                System.out.println("10% voucher awarded to: " + customer.getName());
                tenPercent--;
            } else if (twentyPercent > 0) {
                System.out.println("20% voucher awarded to: " + customer.getName());
                twentyPercent--;
            } else if (fiftyPercent > 0){
                System.out.println("50% voucher awarded to: " + customer.getName());
                fiftyPercent--;
            }
        }
    }

}
