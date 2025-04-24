package ss16_IO_Text_File.thuc_hanh.thuc_hanh_2;

import java.util.List;

import static ss16_IO_Text_File.thuc_hanh.thuc_hanh_2.FindMaxValue.findMax;

public class Main {
    public static void main(String[] args) {
        ReadAndWriteFile readAndWriteFile = new ReadAndWriteFile();
        List<Integer> numbers = readAndWriteFile.readFile("numbers.txt");
        int maxValue = findMax(numbers);
        readAndWriteFile.writeFile("result.txt", maxValue);
    }
}
