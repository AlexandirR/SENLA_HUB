package console;

public class Navigator {

    private Menu currentMenu;

    public Navigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        System.out.println(currentMenu.getTitle());
        for(MenuItem menuItem: currentMenu.getItems()) {
            System.out.println(menuItem.getTitle());
        }
    }

    public void navigate(Integer index) {
        MenuItem menuItem = currentMenu.getItemsByIndex(index - 1);
        currentMenu = menuItem.getNextMenu();
        System.out.println(menuItem.getTitle());
        menuItem.doAction();
    }
}
