package org.example.EmployeeInfo;

import java.io.*;
import java.util.*;

public class FileLoader {
    private final String employeesFileName = "Security names.txt";
    private final String accessCodesFileName = "access_codes.txt";
    private List<String> employees;
    private Map<Integer, EmployeeInfo> employeeInfos;
    private boolean accessCodesGenerated;
    private int s;


    public FileLoader() {
        loadEmployees();
        employeeInfos = new HashMap<>();
        accessCodesGenerated = false;
    }

    public void loadEmployees() {
        employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(employeesFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                employees.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateAccessCodes() {
        Random random = new Random();
        try (PrintWriter pw = new PrintWriter(new FileWriter(accessCodesFileName))) {
            for (int i = 0; i < employees.size(); i++) {
                int accessCode = random.nextInt(900000) + 100000;
                pw.println(accessCode);
                employeeInfos.put(i + 1, new EmployeeInfo(employees.get(s), accessCode));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        accessCodesGenerated = true;
    }

    public boolean isAccessCodesGenerated() {
        return accessCodesGenerated;
    }

    public EmployeeInfo getEmployeeInfo(int employeeNumber) {
        EmployeeInfo employeeInfo = employeeInfos.get(employeeNumber);
        if (employeeInfo == null) {
            System.out.println("Некорректный номер сотрудника");
            return null;
        }
        return employeeInfo;
    }

    public void printFileContent() {
        try (BufferedReader br = new BufferedReader(new FileReader(accessCodesFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}