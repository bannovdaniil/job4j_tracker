package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printError() {
        System.out.println("Active: " + this.active + " Status: "
                + this.status + " Message: " + this.message);
    }

    public static void main(String[] args) {
        Error error1 = new Error();
        Error error2 = new Error(true, 1, "Out of memory");
        Error error3 = new Error(false, 2, "Invalid client");
        error1.printError();
        error2.printError();
        error3.printError();
    }
}
