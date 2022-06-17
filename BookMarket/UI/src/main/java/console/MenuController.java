package console;

import console.Builder;
import console.Navigator;

import java.io.IOException;
import java.util.Scanner;

public class MenuController {

    private Builder builder;
    private Navigator navigator;
    private Scanner scanner;

    public MenuController(String path) throws IOException {
        this.builder = new Builder(path);
        this.scanner = new Scanner(System.in);
        this.navigator = new Navigator(builder.getRootMenu());
    }

    public void run() {
        int index = 0;
        while (true) {
            navigator.printMenu();
            System.out.println("Выбор пункта меню:");
            index = scanner.nextInt();
            navigator.navigate(index);
        }
    }
}
