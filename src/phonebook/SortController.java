package phonebook;

import static phonebook.Controller.linearSearchTime;

public class SortController {

    public void quickSort(String[] data, int left, int right) {
        if (left < right) {
            int pi = partition(data, left, right);
            quickSort(data, left, pi - 1);
            quickSort(data, pi + 1, right);
        }
    }

    private int partition(String[] data, int low, int high) {
        String pivot = getOwnerName(data[high]);
        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)  {
            if (getOwnerName(data[j]).compareTo(pivot) < 0) {
                i++;
                swap(data, i, j);
            }
        }

        swap(data, i + 1, high);
        return i + 1;
    }

    private void swap(String[] data, int i, int j) {
        String temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     *  sort based of owners name
     *
     * @param data  array of data to be sorted, data is formatted as 'number firstName lastName'
     */
    public boolean bubbleSort(String[] data) {
        //NOTE: If the sorting process takes too long (more than 10 times longer than all 500 iterations of the linear search)
        //stop sorting and use the linear search.

        long start = System.currentTimeMillis();

        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                String current = getOwnerName(data[j]);
                String next = getOwnerName(data[ j + 1]);

                if (next.compareTo(current) < 0) {
                    swap(data, i, j);
                }
            }

            //check if taking too long
            long current = System.currentTimeMillis();

            if (current - start > 10 * linearSearchTime) {
                return false;
            }

        }

        return true;
    }

    /**
     *
     * @param data     data is inputted as number firstName lastName?
     * @return         return firstName and lastName
     */
    private String getOwnerName(String data) {
        int idx = data.indexOf(" ");
        return data.substring(idx).trim();
    }

}
