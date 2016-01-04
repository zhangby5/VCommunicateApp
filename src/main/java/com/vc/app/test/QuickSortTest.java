package com.vc.test;

public class QuickSortTest {

  public static void main(String[] args) {
    int array[] = new int[] {
      10, 4, 20, 5, 89, 2, 46, 3, 2, 1
    };
    new QuickSortTest().quickSort(array, 0, array.length - 1);
    for (int i : array) {
      System.out.println(i);
    }
  }

  public void quickSort(int[] array, int low, int high) {
    if (low == high) {
      return;
    }
    if (low < high) {
      int pos = sortPart(array, low, high);

      quickSort(array, low, pos - 1);

      quickSort(array, pos + 1, high);
    }
  }

  private int sortPart(int[] array, int low, int high) {
    int key = array[low];
    while (low < high) {
      while (array[high] >= key && high > low) {
        --high;
      }
      array[low] = array[high];
      while (array[low] <= key && high > low) {
        ++low;
      }
      array[high] = array[low];
    }
    array[high] = key;
    return high;
  }

}
