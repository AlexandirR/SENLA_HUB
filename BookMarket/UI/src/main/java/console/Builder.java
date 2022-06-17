package console;

import model.Book;
import model.Order;
import model.Request;
import service.BookService;
import service.OrderService;
import service.QueryService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Builder {

    private Menu rootMenu;
    private String path;
    private String pathRoot = "MainMenu.txt";
    private String pathBibliography = "BibliographyMenu.txt";
    private String pathOrderHistory = "OrderHistoryMenu.txt";

    private void buildMainMenu(Menu bibliography, Menu orderHistory) throws IOException {
        Path rootPath = Paths.get(path + pathRoot);
        List<MenuItem> menuItems = new ArrayList<>();
        String titleRoot = "";
        if (Files.exists(rootPath)) {
            try (Scanner in = new Scanner(Files.newInputStream(rootPath))) {
                titleRoot = in.nextLine();
                String titleBibliography = in.nextLine();
                menuItems.add(new MenuItem(titleBibliography, bibliography, () -> {}));

                String titleOrderHistory = in.nextLine();
                menuItems.add(new MenuItem(titleOrderHistory, orderHistory, () -> {}));

                String titleRequest = in.nextLine();
                menuItems.add(new MenuItem(titleRequest, bibliography, () -> {
                    String name =  new Scanner(System.in).nextLine();
                    for(Request request: QueryService.requestOnBook(BookService.getBookByName(name))) {
                        System.out.println(request.getOrder());
                    }
                }));

                String titleExit = in.nextLine();
                menuItems.add(new MenuItem(titleExit, orderHistory, () -> {System.exit(0);}));
            }
        }
        rootMenu = new Menu(menuItems, titleRoot);
    }

    private Menu buildBibliographyMenu() throws IOException {
        Path rootPath = Paths.get(path + pathBibliography);
        List<MenuItem> menuItems = new ArrayList<>();
        String titleRoot = "";
        if (Files.exists(rootPath)) {
            try (Scanner in = new Scanner(Files.newInputStream(rootPath))) {
                titleRoot = in.nextLine();
                String titleNewBook = in.nextLine();
                menuItems.add(new MenuItem(titleNewBook, rootMenu, () -> {
                    Scanner scanner = new Scanner(System.in);
                    String name = scanner.next();
                    String description = scanner.nextLine();
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    int price = scanner.nextInt();
                    Book book = new Book(name, description, date, price);
                    BookService.addBook(book);
                }));

                String titleStatus = in.nextLine();
                menuItems.add(new MenuItem(titleStatus, rootMenu, () -> {
                    Scanner scanner = new Scanner(System.in);
                    String name = scanner.next();
                    String status = scanner.next();
                    if(status == "+") {
                        BookService.addBook(BookService.getBookByName(name));
                    }
                    else {
                        BookService.writeOfBook(BookService.getBookByName(name));
                    }
                }));

                for(int i = 3; i <=8; ++i) {
                    String titleItem = in.nextLine();
                    menuItems.add(new MenuItem(titleItem, rootMenu, () -> {
                        int index = new Scanner(System.in).nextInt();
                        for(Book book: BookService.getSortedBooks(index)) {
                            System.out.println(book);
                        }
                    }));
                }

                String titleDescription = in.nextLine();
                menuItems.add(new MenuItem(titleDescription, rootMenu, () -> {
                    Scanner scanner = new Scanner(System.in);
                    String name = scanner.next();
                    System.out.println(BookService.getBookByName(name).getDescription());
                }));

                String titleExit = in.nextLine();
                menuItems.add(new MenuItem(titleExit, rootMenu, () -> {}));
            }
        }
        return new Menu(menuItems, titleRoot);
    }

    private Menu buildOrderHistoryMenu() throws IOException {
        Path rootPath = Paths.get(path + pathOrderHistory);
        List<MenuItem> menuItems = new ArrayList<>();
        String titleRoot = "";
        if (Files.exists(rootPath)) {
            try (Scanner in = new Scanner(Files.newInputStream(rootPath))) {
                titleRoot = in.nextLine();
                String titleNewOrder = in.nextLine();
                menuItems.add(new MenuItem(titleNewOrder, rootMenu, () -> {
                    Scanner scanner = new Scanner(System.in);
                    String info = scanner.nextLine();
                    Integer count = scanner.nextInt();
                    List<Book> books = new ArrayList<>();
                    for(int i = 0; i < count; ++i) {
                        String nameBook = scanner.nextLine();
                        books.add(BookService.getBookByName(nameBook));
                    }
                    OrderService.createOrder(books, info);
                }));

                String titleAddBook = in.nextLine();
                menuItems.add(new MenuItem(titleAddBook, rootMenu, () -> {
                    Scanner scanner = new Scanner(System.in);
                    String info = scanner.nextLine();
                    String bookName = scanner.nextLine();
                    OrderService.addBookInOrder(OrderService.getOrderByDataCustomer(info), BookService.getBookByName(bookName));
                }));

                String titleListOrder = in.nextLine();
                menuItems.add(new MenuItem(titleListOrder, rootMenu, () -> {
                    for(Order order: OrderService.getSortedOrderList("Price")) {
                        System.out.println(order);
                    }
                }));

                titleListOrder = in.nextLine();
                menuItems.add(new MenuItem(titleListOrder, rootMenu, () -> {
                    for(Order order: OrderService.getSortedOrderList("Status")) {
                        System.out.println(order);
                    }
                }));

                titleListOrder = in.nextLine();
                menuItems.add(new MenuItem(titleListOrder, rootMenu, () -> {
                    Scanner scanner = new Scanner(System.in);
                    LocalDate begin = LocalDate.parse(scanner.next());
                    LocalDate end = LocalDate.parse(scanner.next());
                    for(Order order: OrderService.getCompletedOrderList("Date", begin, end)) {
                        System.out.println(order);
                    }
                }));

                titleListOrder = in.nextLine();
                menuItems.add(new MenuItem(titleListOrder, rootMenu, () -> {
                    Scanner scanner = new Scanner(System.in);
                    LocalDate begin = LocalDate.parse(scanner.next());
                    LocalDate end = LocalDate.parse(scanner.next());
                    for(Order order: OrderService.getCompletedOrderList("Price", begin, end)) {
                        System.out.println(order);
                    }
                }));

                String titleCancel = in.nextLine();
                menuItems.add(new MenuItem(titleCancel, rootMenu, () -> {
                    String info = new Scanner(System.in).nextLine();
                    OrderService.canselOrder(OrderService.getOrderByDataCustomer(info));
                }));

                String titleComplete = in.nextLine();
                menuItems.add(new MenuItem(titleComplete, rootMenu, () -> {
                    String info = new Scanner(System.in).nextLine();
                    OrderService.completeOrder(OrderService.getOrderByDataCustomer(info));
                }));

                String titleBooks = in.nextLine();
                menuItems.add(new MenuItem(titleBooks, rootMenu, () -> {
                    String info = new Scanner(System.in).nextLine();
                    for(Book book: OrderService.getOrderByDataCustomer(info).getBooks()) {
                        System.out.println(book);
                    }
                }));

                String titleExit = in.nextLine();
                menuItems.add(new MenuItem(titleExit, rootMenu, () -> {}));
            }
        }
        return new Menu(menuItems, titleRoot);
    }

    public Builder(String path) throws IOException {
        this.path = path;
        Menu bibliographyMenu = buildBibliographyMenu();
        Menu orderHistoryMenu = buildOrderHistoryMenu();
        buildMainMenu(bibliographyMenu, orderHistoryMenu);

        for(MenuItem menuItem: bibliographyMenu.getItems()) {
            menuItem.setNextMenu(rootMenu);
        }

        for(MenuItem menuItem: orderHistoryMenu.getItems()) {
            menuItem.setNextMenu(rootMenu);
        }
    }

    public Menu getRootMenu() {
        return rootMenu;
    }
}
