import controllers.*;
import model.Book;
import model.Order;
import repository.interfacedata.Bibliography;
import repository.interfacedata.OrderHistory;
import repository.interfacedata.QuerySet;
import repository.jsondata.BibliographyJson;
import repository.jsondata.OrderHistoryJson;
import repository.jsondata.QuerySetJson;
import util.property.ConfigurationProperty;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Properties;

public class BookMarket {

    private final String dataFolder;

    private Bibliography bibliography;
    private OrderHistory orderHistory;
    private QuerySet querySet;

    private BibliographyController bibliographyController;
    private BookController bookController;
    private OrderController orderController;
    private OrderHistoryController orderHistoryController;
    private RequestController requestController;

    private ConfigurationProperty configurationProperty = ConfigurationProperty.getInstance();

    public BookMarket() throws IOException {
        dataFolder = configurationProperty.getPropertyByName("data.json.folder");

        bibliography = new BibliographyJson(dataFolder);
        orderHistory = new OrderHistoryJson(dataFolder);
        querySet = new QuerySetJson(dataFolder);

        bibliographyController = new BibliographyController(bibliography);
        bookController = new BookController(bibliography, orderHistory, querySet);
        orderController = new OrderController(orderHistory, querySet);
        orderHistoryController = new OrderHistoryController(orderHistory);
        requestController = new RequestController(querySet);
    }

    public void init() {
        bookController.createBook(new Book(0, "name1", "description", LocalDate.now(), 500));
        bookController.createBook(new Book(1, "name2", "description2", LocalDate.now(), 700));
        Order order = orderController.createOrder("Customer1");
        orderController.addBookInOrder(order, bibliographyController.getBookById(0));
    }

    public static void main(String[] args) throws IOException {
        BookMarket app = new BookMarket();
        //app.init();
        //System.out.println(app.orderHistoryController.getOrderByIndex(0));
    }
}
