package bai_tap_them.case_study_furuma.repositories.promotion;

import bai_tap_them.case_study_furuma.models.Customer;

import java.util.List;
import java.util.Stack;

public interface IPromotionRepository {
    List<Customer> findCustomerByYear(int year);
    Stack<Customer> getCustomerForVoucher(int month, int totalVoucher);
}
