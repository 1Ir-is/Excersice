package bai_tap_them.case_study_furuma.repositories.contract;

import bai_tap_them.case_study_furuma.models.Contract;
import bai_tap_them.case_study_furuma.utils.SaveFileUtils;

import java.util.ArrayList;
import java.util.List;

public class ContractRepository implements IContractRepository {

    private static final String CONTRACTS_FILE = "bai_tap_them/case_study_furuma/data/contracts.csv";

    @Override
    public void add(Contract contract) {
        List<String> lines = new ArrayList<>();
        lines.add(contract.getContractNumber() + "," +
                    contract.getBookingId() + "," +
                    contract.getDeposit() + "," +
                    contract.getTotalPayment());
        SaveFileUtils.writeToFile(CONTRACTS_FILE, lines, true);
    }

    @Override
    public void update(Contract updatedContract) {
        List<String> lines = SaveFileUtils.readFromFile(CONTRACTS_FILE);
        List<String> updateLines = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 4 && parts[0].equals(updatedContract.getContractNumber())) {
                updateLines.add(updatedContract.getContractNumber() + "," +
                        updatedContract.getBookingId() + "," +
                        updatedContract.getDeposit() + "," +
                        updatedContract.getTotalPayment());
            } else {
                updateLines.add(line);
            }
        }

        SaveFileUtils.writeToFile(CONTRACTS_FILE, updateLines, false);
    }

    @Override
    public List<Contract> findAll() {
        List<String> lines = SaveFileUtils.readFromFile(CONTRACTS_FILE);
        List<Contract> contracts = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                contracts.add(new Contract(
                        parts[0],
                        parts[1],
                        Double.parseDouble(parts[2]),
                        Double.parseDouble(parts[3])
                ));
            }
        }
        return contracts;
    }
}