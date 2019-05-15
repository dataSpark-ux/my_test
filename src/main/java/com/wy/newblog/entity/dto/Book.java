package com.wy.newblog.entity.dto;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/03/31
 */
public class Book {

    private String bookName;

    private Double money;

    private int quantity;

    public Book() {
    }

    public Book(String bookName, Double money, int quantity) {
        this.bookName = bookName;
        this.money = money;
        this.quantity = quantity;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", money=" + money +
                ", quantity=" + quantity +
                '}';
    }
}
