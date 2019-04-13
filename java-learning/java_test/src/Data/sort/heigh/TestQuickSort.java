package Data.sort.heigh;

public class TestQuickSort {
	public static void main(String[] args) {
		int[] arr = new int[] {1, 22, 100, 30, 30, 40, 30, 32, 
				299, 342434, 99, 13, 20, 4132,43134, 6, 34, 431,
				34, 41, 3413, 34, 34,7654, 764, 765, 47, 7465, 
				56, 56, 67, 242, 42, 53425, 4, 5234, 54252, 254423525,
				76141, 56, 11,5,43,7, 100, 432552345, 524524, 54,5, 5432,
				52452425, 2452435, 54, 5, 1, 9, 0, 2442, 425, 5432, 2435243,
				52345, 25, 5, 423525, 45243524, 4523, 324523, 432524, 4252345,
				4, 4, 7, 725, 52, 5, 25, 245, 245, 425423, 6546,5, 6, 7,8,854,
				45235, 4, 9, 9, 543, 4, 435, 54, 54, 42452, 65, 756, 734, 2,423,
				425, 524,656, 65,65,65,243,32,42, 324, 1, 22, 100, 30, 30, 40, 30, 32, 
				299, 342434, 99, 13, 20, 4132,43134, 6, 34, 431,
				34, 41, 3413, 34, 34,7654, 764, 765, 47, 7465, 
				56, 56, 67, 242, 42, 53425, 4, 5234, 54252, 254423525,
				76141, 56, 11,5,43,7, 100, 432552345, 524524, 54,5, 5432,
				52452425, 2452435, 54, 5, 1, 9, 0, 2442, 425, 5432, 2435243,
				52345, 25, 5, 423525, 45243524, 4523, 324523, 432524, 4252345,
				4, 4, 7, 725, 52, 5, 25, 245, 245, 425423, 6546,5, 6, 7,8,854,
				45235, 4, 9, 9, 543, 4, 435, 54, 54, 42452, 65, 756, 734, 2,423,
				425, 524,656, 65,65,65,243,32,42,324};
		QuickSort sort = new QuickSort(arr);
		sort.display();
	}
}

class QuickSort{
	private int[] sortArr;
	
	public QuickSort(int[] arr) {
		this.sortArr = arr;
		
		doQuickSort();
	}
	
	public void doQuickSort() {
		int leftPtr = 0;
		int rightPtr = sortArr.length - 1;
//		doQuickSort1(leftPtr, rightPtr);
//		myQuickSort2(leftPtr, rightPtr);
		myQuickSort3(leftPtr, rightPtr);
	}
	
	public void doQuickSort1(int left, int right) {
		if(left >= right) {
			return;
		}
		int pivot = sortArr[right];
		int mid = myQuickSort1(left, right, pivot);
		doQuickSort1(left, (mid-1));
		doQuickSort1((1+mid), right);
	}
	
	public int myQuickSort1(int left, int right, int pivot) {
		int leftPtr = left;
		int rightPtr = right;

		while(leftPtr < rightPtr) {
			while(leftPtr < right && sortArr[leftPtr] <= pivot) {
				leftPtr++;
			}
			while(rightPtr > left && sortArr[rightPtr] >= pivot) {
				rightPtr--;
			}
			if(leftPtr >= rightPtr) {
				break;
			}
			int temp = sortArr[leftPtr];
			sortArr[leftPtr] = sortArr[rightPtr];
			sortArr[rightPtr] = temp;
		}
		int temp = sortArr[leftPtr];
		sortArr[leftPtr] = sortArr[right];
		sortArr[right] = temp;
		
		return leftPtr;
	}
	
	//"三数据取中"划分
	public void myQuickSort2(int left, int right) {
		if(right - left <= 2) {
			sort(left, right);
			return;
		}
		int center = (right + left) / 2;
		
		if(sortArr[left] > sortArr[center]) {
			int temp = sortArr[left];
			sortArr[left] = sortArr[center];
			sortArr[center] = temp;
		}
		if(sortArr[left] > sortArr[right]) {
			int temp = sortArr[left];
			sortArr[left] = sortArr[right];
			sortArr[right] = temp;
		}
		if(sortArr[center] > sortArr[right]) {
			int temp = sortArr[right];
			sortArr[right] = sortArr[center];
			sortArr[center] = temp;
		}
		int temp = sortArr[right - 1];
		sortArr[right - 1] = sortArr[center];
		sortArr[center] = temp;
		
		int leftPtr = left;
		int rightPtr = right-1;
		int pivot = sortArr[right - 1];
		
		while(leftPtr < rightPtr) {
			while(leftPtr < rightPtr && sortArr[++leftPtr] <= pivot);
			while(rightPtr > leftPtr && sortArr[--rightPtr] > pivot);
			if(leftPtr >= rightPtr) {
				break;
			}
			int temp1 = sortArr[leftPtr];
			sortArr[leftPtr] = sortArr[rightPtr];
			sortArr[rightPtr] = temp1;
		}
		int t = sortArr[leftPtr];
		sortArr[leftPtr] = sortArr[right - 1];
		sortArr[right - 1] = t;
		
		
		myQuickSort2(left, leftPtr - 1);
		myQuickSort2(leftPtr + 1, right);
	}
	
	public void sort(int leftPtr, int rightPtr) {
		if(leftPtr - rightPtr == 1){
			if(sortArr[leftPtr] > sortArr[rightPtr]) {
				int temp = sortArr[leftPtr];
				sortArr[leftPtr] = sortArr[rightPtr];
				sortArr[rightPtr] = temp;
			}
			return;
		}
		if(rightPtr - leftPtr == 2){
			int center = (leftPtr + rightPtr) / 2;
			if(sortArr[leftPtr] > sortArr[center]) {
				int temp = sortArr[leftPtr];
				sortArr[leftPtr] = sortArr[center];
				sortArr[center] = temp;
			}
			if(sortArr[leftPtr] > sortArr[rightPtr]) {
				int temp = sortArr[leftPtr];
				sortArr[leftPtr] = sortArr[rightPtr];
				sortArr[rightPtr] = temp;
			}
			if(sortArr[center] > sortArr[rightPtr]) {
				int temp = sortArr[center];
				sortArr[center] = sortArr[rightPtr];
				sortArr[rightPtr] = temp;
			}
		}
	}
	
	//处理小划分，使用插入排序来处理小划分，以上三数据区中划分中一3作为划分点，在实际状况中，可以改变为不同的值，已达到最高的效率
	public void myQuickSort3(int left, int right) {
		if(right - left <= 10) {
			insertSort(left, right);
			return;
		}
		int center = (right + left) / 2;
		
		if(sortArr[left] > sortArr[center]) {
			int temp = sortArr[left];
			sortArr[left] = sortArr[center];
			sortArr[center] = temp;
		}
		if(sortArr[left] > sortArr[right]) {
			int temp = sortArr[left];
			sortArr[left] = sortArr[right];
			sortArr[right] = temp;
		}
		if(sortArr[center] > sortArr[right]) {
			int temp = sortArr[right];
			sortArr[right] = sortArr[center];
			sortArr[center] = temp;
		}
		int temp = sortArr[right - 1];
		sortArr[right - 1] = sortArr[center];
		sortArr[center] = temp;
		
		int leftPtr = left;
		int rightPtr = right - 1;
		int pivot = sortArr[right - 1];
		
		while(leftPtr < rightPtr) {
			while(leftPtr < rightPtr && sortArr[++leftPtr] <= pivot);
			while(rightPtr > leftPtr && sortArr[--rightPtr] > pivot);
			if(leftPtr >= rightPtr) {
				break;
			}
			int temp1 = sortArr[leftPtr];
			sortArr[leftPtr] = sortArr[rightPtr];
			sortArr[rightPtr] = temp1;
		}
		int t = sortArr[leftPtr];
		sortArr[leftPtr] = sortArr[right - 1];
		sortArr[right - 1] = t;
		
		
		myQuickSort2(left, leftPtr - 1);
		myQuickSort2(leftPtr + 1, right);
	}
	
	public void insertSort(int left, int right) {
		for(int i = left + 1; i <= right; i++) {
			int j = i;
			int temp = sortArr[i];
			while(j > left && sortArr[j-1] > temp) {
				sortArr[j] = sortArr[j-1];
				j--;
			}
			sortArr[j] = temp;
		}
	}
	
	public void swap(int i, int j) {
		int temp = sortArr[i];
		sortArr[i] = j;
		sortArr[j] = temp;
	}
	
	public void display() {
		for(int j = 0; j < sortArr.length; j++) {
			System.out.print(sortArr[j] + " ");
		}
		System.out.println();
	}
}