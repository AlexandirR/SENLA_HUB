import console.MenuController;
import model.Book;
import repo.Bibliography;
import repo.OrderHistory;
import repo.QuerySet;
import service.BookService;
import service.OrderService;
import service.QueryService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BookMarket {

    private final String menuFolder;
    private final MenuController menuController;
    private Bibliography bibliography = new Bibliography();
    private OrderHistory orderHistory = new OrderHistory();
    private QuerySet querySet = new QuerySet();

    public BookMarket(Properties properties) throws IOException {
        menuFolder = properties.getProperty("menu.folder","./menu/");
        List<Book> books = new ArrayList<>();
        bibliography.setBooks(books);
        OrderService.initialization(orderHistory);
        BookService.initialization(bibliography);
        QueryService.initializationQuerySet(querySet);
        menuController = new MenuController(menuFolder);
    }

    public static void main(String[] args) throws IOException {
        String configPath = args.length > 0
                ? args[0]
                : "bookmarket.properties";
        Properties properties = new Properties();
        Path path = Paths.get(configPath);
        if (Files.exists(path)) {
            try (InputStream in = Files.newInputStream(Paths.get(configPath))) {
                properties.load(in);
            }
        }

        BookMarket app = new BookMarket(properties);
        app.menuController.run();
    }
}
