package com.wy.newblog.entity.dto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/03/31
 */
public class Test {

    public static void main(String[] args) {

        try {
            File redder = new File("/Users/wangyi/Desktop/work/myWork/newBlog/src/main/java/com/wy/newblog/entity/dto/book.txt");
            BufferedReader br = new BufferedReader(new FileReader(redder));
            List<Book> books = new ArrayList<>();
            String str;
            while ((str = br.readLine()) != null) {
                String[] b = str.split(",");
                Book book = new Book(b[0], Double.valueOf(b[1]), Integer.valueOf(b[2]));
                books.add(book);
            }
            Double sum = 0.0;
            for (Book b : books) {
                System.err.println(b.getBookName() + ": 共计" + b.getMoney() * b.getQuantity() + "元");
                sum += b.getMoney() * b.getQuantity();
            }
            System.err.println("图书总额为: " + sum + "元");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
