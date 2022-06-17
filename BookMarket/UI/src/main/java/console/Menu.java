package console;

import java.util.List;

public class Menu {

    private List<MenuItem> items;
    private String title;

    public Menu(List<MenuItem> items, String title) {
        this.title = title;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public MenuItem getItemsByIndex(Integer index) {
        return items.get(index);
    }

    public List<MenuItem> getItems() {
        return items;
    }
}
