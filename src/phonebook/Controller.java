package phonebook;

import java.time.Duration;
import java.util.Map;

public class Controller {

    private final String[] directory;
    private final String[] toFind;
    private final SearchController searchController;
    private final SortController sortController;

    static long linearSearchTime;

    public Controller(String[] directory, String[] toFind) {
        this.directory = directory;
        this.toFind = toFind;
        this.searchController = new SearchController();
        this.sortController = new SortController();
    }

    public void startSearches() {
        performLinearSearch();
        performBubbleSortAndJumpSearch();
        performBinarySearchAndQuickSort();
        performSearchWithHashMap();
    }

    private void performLinearSearch() {
        System.out.println("Start searching....");

        Long start = System.currentTimeMillis();
        int resultsFound = searchController.linearSearch(directory, toFind);
        Long end = System.currentTimeMillis();

        linearSearchTime = (end - start);

        Duration duration = Duration.ofMillis(end - start);
        int minutes = duration.toMinutesPart();
        int seconds = duration.toSecondsPart();
        int ms = duration.toMillisPart();

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", resultsFound, toFind.length, minutes, seconds, ms);
    }

    private void performBubbleSortAndJumpSearch() {
        System.out.println("\nStart searching (bubble sort + jump search)...");
        boolean isSorted = true;
        int resultsFond;

        long sortStartTime;
        long sortEndTime;

        long searchStartTime;
        long searchEndTime;

        Duration totalDuration;
        Duration sortDuration;
        Duration searchDuration;

        long start = System.currentTimeMillis();

        String[] copyDirectory = directory;

        sortStartTime = System.currentTimeMillis();
        //check if sorting takes too long, if it does go to linear search
        if (!sortController.bubbleSort(copyDirectory)) {
            isSorted = false;
            sortEndTime = System.currentTimeMillis();

            //now to linear search
            searchStartTime = System.currentTimeMillis();
            resultsFond = searchController.linearSearch(copyDirectory, toFind);

        } else {
            sortEndTime = System.currentTimeMillis();

            //didn't take too long to sort, do jump search
            searchStartTime = System.currentTimeMillis();
            resultsFond = searchController.jumpSearch(copyDirectory, toFind);
        }

        searchEndTime = System.currentTimeMillis();

        sortDuration = Duration.ofMillis(sortEndTime - sortStartTime);
        searchDuration = Duration.ofMillis(searchEndTime - searchStartTime);
        totalDuration = Duration.ofMillis(searchEndTime - start);

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", resultsFond, toFind.length, totalDuration.toMinutesPart(), totalDuration.toSecondsPart(), totalDuration.toMillisPart());

        String sortTime = String.format("Sorting time: %d min. %d sec. %d ms.", sortDuration.toMinutesPart(), sortDuration.toSecondsPart(), sortDuration.toMillisPart());
        System.out.println(isSorted ? sortTime : sortTime + " - STOPPED, moved to linear search");

        System.out.printf("Searching time: %d min. %d sec. %d ms.", searchDuration.toMinutesPart(), searchDuration.toSecondsPart(), searchDuration.toMillisPart());
    }

    private void performBinarySearchAndQuickSort() {
        System.out.println("\n\nStart searching (quick sort + binary search)...");
        int resultsFond;

        long sortStartTime;
        long sortEndTime;

        long searchStartTime;
        long searchEndTime;

        Duration totalDuration;
        Duration sortDuration;
        Duration searchDuration;

        long start = System.currentTimeMillis();

        //sort using quick sort
        sortStartTime = System.currentTimeMillis();
        sortController.quickSort(directory, 0, directory.length - 1);
        sortEndTime = System.currentTimeMillis();

        //find results with binary search
        searchStartTime = System.currentTimeMillis();
        resultsFond = searchController.binarySearch(directory, toFind);
        searchEndTime = System.currentTimeMillis();

        long end = System.currentTimeMillis();

        sortDuration = Duration.ofMillis(sortEndTime - sortStartTime);
        searchDuration = Duration.ofMillis(searchEndTime - searchStartTime);
        totalDuration = Duration.ofMillis(end - start);

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", resultsFond, toFind.length, totalDuration.toMinutesPart(), totalDuration.toSecondsPart(), totalDuration.toMillisPart());
        System.out.printf("Sorting time: %d min. %d sec. %d ms.\n", sortDuration.toMinutesPart(), sortDuration.toSecondsPart(), sortDuration.toMillisPart());
        System.out.printf("Searching time: %d min. %d sec. %d ms.", searchDuration.toMinutesPart(), searchDuration.toSecondsPart(), searchDuration.toMillisPart());
    }


    private void performSearchWithHashMap() {
        System.out.println("\n\nStart searching (hash table)...");
        int resultsFond;

        long mapCreationStart;
        long mapCreationEnd;

        long searchStartTime;
        long searchEndTime;

        Duration totalDuration;
        Duration mapDuration;
        Duration searchDuration;

        long start = System.currentTimeMillis();

        //create map
        mapCreationStart = System.currentTimeMillis();
        Map<String, String> map = searchController.createMap(directory);
        mapCreationEnd = System.currentTimeMillis();

        //start searching map
        searchStartTime = System.currentTimeMillis();
        resultsFond = searchController.hashMapSearch(map, toFind);
        searchEndTime = System.currentTimeMillis();

        long end = System.currentTimeMillis();

        mapDuration = Duration.ofMillis(mapCreationEnd - mapCreationStart);
        searchDuration = Duration.ofMillis(searchEndTime - searchStartTime);
        totalDuration = Duration.ofMillis(end - start);

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", resultsFond, toFind.length, totalDuration.toMinutesPart(), totalDuration.toSecondsPart(), totalDuration.toMillisPart());
        System.out.printf("Creating time: %d min. %d sec. %d ms.\n", mapDuration.toMinutesPart(), mapDuration.toSecondsPart(), mapDuration.toMillisPart());
        System.out.printf("Searching time: %d min. %d sec. %d ms.", searchDuration.toMinutesPart(), searchDuration.toSecondsPart(), searchDuration.toMillisPart());
    }

}
