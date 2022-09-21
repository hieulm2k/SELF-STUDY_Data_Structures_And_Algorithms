public class SortingAlgorithms {
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void printResult(int[] a) {
        for (int j : a) {
            System.out.print(j + " ");
        }
    }

    public static void selectionSort(int[] a, int n) {
        for (int i = 0; i < n - 1; i++) {
            int jmin = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[jmin]) jmin = j;
            }
            if (jmin != i) {
                swap(a, i, jmin);
            }
        }
    }

    public static void bubbleSort(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            for (int j = n - 1; j >= i; j--) {
                if (a[j - 1] > a[j]) swap(a, j - 1, j);
            }
        }
    }

    public static void insertionSort(int[] a, int n) {
        int i;
        int j;
        int temp;
        for (i = 1; i < n; i++) {
            temp = a[i];
            j = i - 1;
            while (j >= 0 && a[j] > temp) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }
    }

    // Binary insertion sort function
    public static void binaryInsertionSort(int[] a, int n) {
        int i;
        int j;
        int mid;
        int left;
        int right;
        int temp;

        for (i = 1; i < n; i++) {
            temp = a[i];
            left = 0;
            right = i - 1;

            while (left <= right) {
                mid = (left + right) / 2;
                if (a[mid] > temp) right = mid - 1;
                else left = mid + 1;
            }

            for (j = i - 1; j >= left; j--) {
                a[j + 1] = a[j];
            }
            a[left] = temp;
        }
    }

    public static void shellSort(int[] a, int n) {
        for (int interval = n / 2; interval > 0; interval /= 2) {
            for (int i = interval; i < n; i++) {
                int temp = a[i];
                int j;
                for (j = i; j >= interval && a[j - interval] > temp; j -= interval)
                    a[j] = a[j - interval];
                a[j] = temp;
            }
        }
    }

    public static void quickSort(int[] a, int first, int last) {
        if (first < last) {
            int pivotIndex = partition(a, first, last);
            quickSort(a, first, pivotIndex - 1);
            quickSort(a, pivotIndex + 1, last);
        }
    }

    public static int partition(int[] a, int first, int last) {
        int mid = (last + first) / 2;
        int pivotIndex = last - 1;
        sortFirstMiddleLast(a, first, mid, last);
        swap(a, mid, pivotIndex);

        int pivot = a[pivotIndex];

        int indexFromLeft = first;
        int indexFromRight = pivotIndex - 1;

        while (indexFromLeft < indexFromRight) {
            while (a[indexFromLeft] < pivot)
                indexFromLeft++;

            while (a[indexFromRight] > pivot)
                indexFromRight--;

            if (indexFromLeft < indexFromRight) {
                swap(a, indexFromLeft, indexFromRight);
                indexFromLeft++;
                indexFromRight--;
            }
        }

        swap(a, pivotIndex, indexFromLeft);
        pivotIndex = indexFromLeft;
        return pivotIndex;
    }

    public static void sortFirstMiddleLast(int[] a, int first, int mid, int last) {
        if (a[first] > a[mid]) swap(a, first, mid);

        if (a[mid] > a[last]) swap(a, last, mid);

        if (a[first] > a[mid]) swap(a, first, mid);
    }

    public static void heapSort(int[] a, int n) {
        createHeap(a, n);
        while (n > 0) {
            swap(a, n - 1, 0);
            n--;
            shift(a, 0, n);
        }
    }

    public static void createHeap(int[] a, int n) {
        int l = (n / 2) - 1;
        while (l >= 0) {
            shift(a, l, n);
            l--;
        }
    }

    public static void shift(int[] a, int l, int n) {
        int i = l;
        int j = 2 * i + 1;

        while (j < n) {
            if (j + 1 < n && a[j + 1] > a[j]) j = j + 1;
            if (a[j] < a[i]) {
                break;
            } else {
                swap(a, i, j);
                i = j;
                j = 2 * i + 1;
            }
        }
    }

    public static void countingSort(int[] a, int n) {
        int[] sortedArray = new int[n];

        int max = a[0];
        int min = a[0];

        for (int i = 1; i < n; i++) {
            if (a[i] > max) {
                max = a[i];
            } else if (a[i] < min) {
                min = a[i];
            }
        }

        int k = max - min + 1; // count array size

        int[] countArray = new int[k];

        for (int i = 0; i < n; i++)
            countArray[a[i] - min]++;

        // convert values of countArray to their position value in the sortedList
        for (int i = 1; i < k; i++)
            countArray[i] += countArray[i - 1];


        for (int i = 0; i < n; i++) {
            sortedArray[countArray[a[i] - min] - 1] = a[i];
            countArray[a[i] - min]--;
        }
        System.arraycopy(sortedArray, 0, a, 0, n);
    }

    private static void radixSort(int[] a, int n) {
        int max = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(a, n, exp);
        }
    }

    private static void countSort(int[] a, int n, int exp) {
        int[] outputList = new int[n];
        int[] countDigit = new int[10];

        int i;
        for (i = 0; i < n; i++)
            countDigit[(a[i] / exp) % 10]++;

        // convert values of countDigit to their position value in the outputList
        for (i = 1; i < 10; i++)
            countDigit[i] += countDigit[i - 1];

        for (i = n - 1; i >= 0; i--) {
            outputList[countDigit[(a[i] / exp) % 10] - 1] = a[i];
            countDigit[(a[i] / exp) % 10]--;
        }

        System.arraycopy(outputList, 0, a, 0, n);
    }

    public static void mergeSort(int[] a, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(a, l, m);
            mergeSort(a, m + 1, r);

            merge(a, l, m, r);
        }
    }

    public static void merge(int[] a, int first, int mid, int last) {
        int[] tempArray = new int[last + 1];

        int first1 = first;
        int last1 = mid;
        int first2 = mid + 1;
        int last2 = last;
        int index = first1;

        while ((first1 <= last1) && (first2 <= last2)) {
            if (a[first1] <= a[first2]) {
                tempArray[index] = a[first1];
                first1++;
            } else {
                tempArray[index] = a[first2];
                first2++;
            }
            index++;
        }

        while (first1 <= last1) {
            tempArray[index] = a[first1];
            first1++;
            index++;
        }

        while (first2 <= last2) {
            tempArray[index] = a[first2];
            first2++;
            index++;
        }

        System.arraycopy(tempArray, first, a, first, last - first + 1);
    }

    public static void main(String[] args) {
        int[] a = new int[]{55, 6, 7, 1, 30, 24, 50, 99, 48, 82, 102, 3000};
        // selectionSort(a, a.length);
        // bubbleSort(a, a.length);
        // insertionSort(a, a.length);
        // binaryInsertionSort(a, a.length);
        // shellSort(a, a.length);
        // quickSort(a, 0, a.length - 1);
        // heapSort(a, a.length);
        // countingSort(a, a.length);
        // radixSort(a, a.length);
        // mergeSort(a, 0, a.length - 1);
        printResult(a);
    }
}
