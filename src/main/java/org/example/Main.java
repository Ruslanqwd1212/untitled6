package org.example;

import jdk.internal.access.JavaIOFileDescriptorAccess;
import org.example.EmployeeInfo.EmployeeInfo;
import org.example.EmployeeInfo.FileLoader;

import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static JavaIOFileDescriptorAccess employees;

    public static void main(String[] args) throws IOException {

        FileWriter writer = new FileWriter("Security names.txt");
        writer.close();


        FileLoader fileLoader = new FileLoader();




        Thread accessCodeGeneratorThread = new Thread(fileLoader::generateAccessCodes);
        accessCodeGeneratorThread.start();


        while (!fileLoader.isAccessCodesGenerated()) {
            System.out.println("Генерация кодов доступа...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("Содержимое файла:");
        fileLoader.printFileContent();


        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер сотрудника: ");
        int employeeNumber = scanner.nextInt();


        int accessCode = 0;
        FileDescriptor i = new FileDescriptor();
        FileDescriptor s;
        s = null;
        EmployeeInfo employeeInfo = new EmployeeInfo(employees.get(s), accessCode);
        System.out.println("Сотрудник #" + employeeNumber + ": " + employeeInfo.getName() +
                ", код доступа: " + employeeInfo.getAccessCode());
    }
}