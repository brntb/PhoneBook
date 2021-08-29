package phonebook;

public class Main {
    public static void main(String[] args) {

        String smallDirectory = "/home/slinger/directory/small_directory.txt";
        String smallToFind = "/home/slinger/directory/small_find.txt";

        String largeDirectory = "/home/slinger/directory/directory.txt";
        String largeToFind = "/home/slinger/directory/find.txt";

        DataLoader dataLoader = new DataLoader();
        String[] directory = dataLoader.loadData(largeDirectory);
        String[] toFind = dataLoader.loadData(largeToFind);

        Controller controller = new Controller(directory, toFind);
        controller.startSearches();
    }
}
