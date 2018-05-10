package lib;

public class BinarySearch {
    public static int getInsertPosition(int searchedIndex) {
        if (searchedIndex < 0) {
            return -searchedIndex - 1;
        }
        return searchedIndex;
    }
}
