public class TestBag { 
    public static void main(String args[]) {
        // get a list of items

        Item apple1 = new Item("apple");
        Item apple2 = new Item("apple");
        Item pear1 = new Item("pear");

    }
}

 // item class
class Item{
    private String name;

    Item(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

}