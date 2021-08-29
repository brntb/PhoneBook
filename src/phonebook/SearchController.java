package phonebook;

import java.util.HashMap;
import java.util.Map;

public class SearchController {

    public int binarySearch(String[] data, String[] toFind) {
        int resultsFound = 0;

        for (String dataToFind : toFind) {
            int idx = binarySearch(data, dataToFind);
            if (idx != -1) {
                resultsFound++;
            }
        }

        return resultsFound;
    }


    private int binarySearch(String[] data, String toFind) {
       int left = 0;
       int right = data.length - 1;
       int mid;

       while (left <= right) {
           mid = left + (right - left) / 2;

           String current = getOwnerName(data[mid]);

           if (current.equals(toFind)) {
               return mid;
           } else if (current.compareTo(toFind) < 0) {
               left = mid + 1;
           } else {
               right = mid - 1;
           }
       }

       return -1;
    }

    public int linearSearch(String[] data, String[] toFind) {
        int foundResults = 0;

        for (String line : data) {

            String name = getOwnerName(line);

            for (String dataToFind : toFind) {
                if (name.equals(dataToFind.trim())) {
                    foundResults++;
                    break;
                }
            }
        }

        return foundResults;
    }

    public int jumpSearch(String[] data, String[] toFind) {
        int foundResults = 0;

        for (String dataToFind : toFind) {
            int idx = jumpSearch(data, dataToFind);
            if (idx != -1) {
                foundResults++;
            }
        }

        return foundResults;
    }

    private int jumpSearch(String[] data, String toFind) {
        int current = 0;
        int previous = 0;

        int jumpLength = (int) Math.sqrt(data.length);

        while (current < data.length - 1) {
            current = Math.min(current + jumpLength, data.length - 1);

            String name = getOwnerName(data[current]);

            if (name.compareTo(toFind) >= 0) {
                break;
            }

            previous = current;
        }

        if (current == data.length - 1 && toFind.compareTo(data[current]) > 0) {
            return -1;
        }

        return backwardsLinearSearch(data, toFind, previous, current);
    }

    private int backwardsLinearSearch(String[] array, String toFind, int start, int end) {
        for (int i = end; i > start; i--) {
            String name = getOwnerName(array[i]);
            if (name.equals(toFind)) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param data     data is inputted as 'number firstName lastName?'
     * @return         return firstName and lastName
     */
    private String getOwnerName(String data) {
        int idx = data.indexOf(" ");
        return data.substring(idx).trim();
    }

    private String getPersonsNumber(String data) {
        return data.substring(data.indexOf(" "));
    }

    public int hashMapSearch(Map<String, String> map, String[] toFind) {
        int resultsFound = 0;

        for (String line : toFind) {
            if (map.containsKey(line)) {
                resultsFound++;
            }
        }

        return resultsFound;
    }

    public Map<String, String> createMap(String[] data) {
        //key -> name, value -> phone number
        Map<String, String> map = new HashMap<>();

        for (String line : data) {
            String name = getOwnerName(line);
            String number = getPersonsNumber(line);
            map.put(name, number);
        }

        return map;
    }


}
