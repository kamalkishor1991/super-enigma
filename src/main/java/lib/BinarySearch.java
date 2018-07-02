package lib;

public class BinarySearch {
    public static int getInsertPosition(int searchedIndex) {
        if (searchedIndex < 0) {
            return -searchedIndex - 1;
        }
        return searchedIndex;
    }

    /**
     * Get start and end indexes of target in sorted array, else returns {-1, -1}
     */
    public int[] getStartAndEndIndexes(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] {-1, -1};
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        int ans[] = {-1, -1};
        if (low >= nums.length || nums[low] != target) {
            return ans;
        }
        ans[0] = low;
        low = 0;
        high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        ans[1] = high;
        return ans;

    }
}
