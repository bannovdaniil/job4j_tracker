package ru.job4j.pojo;

import java.time.LocalDateTime;

public class Student {
    private String fio;
    private String group;
    private LocalDateTime receipt;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public LocalDateTime getReceipt() {
        return receipt;
    }

    public void setReceipt(LocalDateTime receipt) {
        this.receipt = receipt;
    }
}
