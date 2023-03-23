package org.example.EmployeeInfo;

public record EmployeeInfo(int s, int accessCode) {


    public EmployeeInfo(String s, int accessCode) {
    }

    public String getAccessCode() {
        return null;
    }

    public String getName() {
        return null;
    }
}
