package ss16_IO_Text_File.bai_tap.bai_tap_1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileCopyTool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap duong dan file nguon: ");
        String sourceFilePath = scanner.nextLine();

        System.out.print("Nhap duong dan file dich: ");
        String targetFilePath = scanner.nextLine();

        File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetFilePath);

        if (!sourceFile.exists()) {
            System.err.println("File nguon khong ton tai!");
            return;
        }

        if (targetFile.exists()) {
            System.err.println("File dich khong ton tai!");
            return;
        }

        int charCount = 0;


        try (FileReader reader = new FileReader(sourceFile);
             FileWriter writer = new FileWriter(targetFile);
             ){
            int character;
            while ((character = reader.read()) != -1) {
                writer.write(character);
                charCount++;
            }

            System.out.println("Sao chep thanh cong");
            System.out.println("So ky tu da sao chep: " + charCount);

        } catch (IOException e) {
            System.err.println("Loi khi doc hoac ghi file: " + e.getMessage());
        }
    }
}
