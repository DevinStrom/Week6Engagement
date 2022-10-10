
package measurebigotime;


public class MeasureBigOTime {


    public static void main(String[] args) {
        
        //10,000 test 
        int[] array10000 = new int[10000];             
        for (int i = 0; i < array10000.length; i++) {
            array10000[i] = (int) (Math.random () * 1000);
        }
        int intArr1[] = new int[array10000.length]; 
        int intArr2[] = new int[array10000.length]; 
        for(int i = 0; i < array10000.length; i++) {
            intArr1[i] = array10000[i];
            intArr2[i] = array10000[i];
        }

        //int[] intArr1 = {2, 8, 5, 3, 9, 4, 1};
        //int[] intArr1 = {1,2};
        //int [] intArr1 = {3};
        long start = System.nanoTime();
        selectionSort(intArr1);
        long totalTime = System.nanoTime() - start;
        System.out.println("\nselectionSort running time: " + totalTime);
        
        System.out.println("---------------------");
        
        //int[] intArr = {2, 8, 5, 3, 9, 4, 1};
        //int[] intArr = {1,2};
        //int [] intArr2 = {3};
        start = System.nanoTime();
        mergeSort(intArr2);
        printArray(intArr2);
        System.out.println("");
        totalTime = System.nanoTime() - start;
        System.out.println("mergeSort running time: " + totalTime);
        
    }
    
    //O(N^2)
    //https://www.youtube.com/watch?v=g-PGLbMth_g
    //given an array, sort the ints from smallest to largest
    private static void selectionSort(int [] intArr) {
        
        int currentMin;
        for(int j = 0; j < intArr.length-1; j++) {
            currentMin = j;
            for(int i = j + 1; i < intArr.length; i++) {
                if(intArr[i] < intArr[currentMin]) {
                    currentMin = i;
                }
            }

            if(currentMin != j) {
                int temp = intArr[j];
                intArr[j] = intArr[currentMin];
                intArr[currentMin] = temp;
            }
        }

        System.out.print("selection sort array: ");
        for(int x : intArr) {
            System.out.print(x + " ");
        }
    }
    
    
    
     //O(NlogN)
    //from youtube https://www.youtube.com/watch?v=4VqmGXwpLqc <-- class link
    //merge sort least to greatest
    private static void mergeSort(int [] inputArray) {
        
        int inputLength = inputArray.length;
        int midIndex = inputLength / 2;
        
        if(inputLength < 2) {
            return;
        }
        int [] first = new int[midIndex];
        int[] second = new int[inputLength - midIndex];
        
        for(int i = 0; i < midIndex; i++) {
            first[i] = inputArray[i];
        }
        for(int i = midIndex; i < inputLength; i++) {
            second[i - midIndex] = inputArray[i];
        }
        
        mergeSort(first);
        mergeSort(second);
        
        //merge two sorted arrays into one large array
        merge(inputArray, first, second);  
    }
    //merge sort
    private static void merge(int [] inputArray, int [] first, int [] second) {
        int leftSize = first.length;
        int rightSize = second.length;
        
        int i = 0, j = 0, k = 0;
        while(i < leftSize && j < rightSize) {
            if(first[i] <= second[j]) {
                inputArray[k] = first[i];
                i++;
            }
            else {
                inputArray[k] = second[j];
                j++;
            }
            k++;
        }
        while(i < leftSize) {
            inputArray[k] = first[i];
            i++;
            k++;
        }
        while(j < rightSize) {
            inputArray[k] = second[j];
            j++;
            k++;
        }
    }
    
    //method to print the array
    private static void printArray(int [] inputArray) {
        System.out.print("from merge sort: ");
        for(int element : inputArray) {
            System.out.print(element + " ");
        }
    }
    
    //O(n log n) time
    //quick sort
    private static void quickSort(int [] array, int left, int right) {
        //int arrayLength = array.length;
        if(left >= right) {
            return;
        }
        int pivot = array[(left + (right - left)/2)];
        int index = partition(array, left, right, pivot);
        quickSort(array, left, index - 1);
        quickSort(array, index, right);
    }
    //still quick sort
    private static int partition(int [] array, int left, int right, int pivot) {
        while(left <= right) {
            while(array[left] < pivot) {
                left++;
            }
            while(array[right] > pivot) {
                right--;
            }
            if(left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }
    //was used for quick sort
    private static void swap(int [] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
    
}
