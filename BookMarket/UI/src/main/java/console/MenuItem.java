package console;

public class MenuItem {

    private String title;
    private Menu nextMenu;
    private IAction action;

    public MenuItem(String title, Menu nextMenu, IAction action) {
        this.title = title;
        this.nextMenu = nextMenu;
        this.action = action;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

    public void setNextMenu(Menu nextMenu) {
        this.nextMenu = nextMenu;
    }

    public String getTitle() {
        return title;
    }

    public void doAction() {
        action.doAction();
    }

    public void setAction(IAction action) {
        this.action = action;
    }
}
