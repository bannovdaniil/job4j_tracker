package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book[] books = new Book[4];
        books[0] = new Book("Clean code", 501);
        books[1] = new Book("Head First Java, Изучаем Java", 761);
        books[2] = new Book("Java. Руководство для начинающих", 1600);
        books[3] = new Book("Java. Полное руководство", 3212);
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            System.out.println(book.getName() + " - " + book.getPages());
        }
        Book tmp = books[0];
        books[0] = books[3];
        books[3] = tmp;
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            if (book.getName().equals("Clean code")) {
                System.out.println(book.getName() + " - " + book.getPages());
            }
        }
    }
}
