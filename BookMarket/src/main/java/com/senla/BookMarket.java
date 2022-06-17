package com.senla;

import annitations.Inject;
import application.Application;
import com.senla.configurations.JavaConfiguration;
import context.ApplicationContext;
import com.senla.controllers.*;
import com.senla.model.Book;
import com.senla.model.Order;

import java.time.LocalDate;

public class BookMarket {

    @Inject
    private BibliographyController bibliographyController;
    @Inject
    private BookController bookController;
    @Inject
    private OrderController orderController;
    @Inject
    private OrderHistoryController orderHistoryController;
    @Inject
    private RequestController requestController;

    public void init() {
        bookController.createBook(new Book(0, "name1", "description", LocalDate.now(), 500));
        bookController.createBook(new Book(1, "name2", "description2", LocalDate.now(), 700));
        Order order = orderController.createOrder("Customer1");
        orderController.addBookInOrder(order, bibliographyController.getBookById(0));
    }

    public static void main(String[] args) {
        ApplicationContext context = Application.run(JavaConfiguration.class);
        BookMarket app = context.getBean(BookMarket.class);
        //app.init();
        System.out.println(app.orderHistoryController.getOrderByIndex(0));
    }
}
