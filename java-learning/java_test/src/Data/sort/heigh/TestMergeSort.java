package Data.sort.heigh;

public class TestMergeSort {
	public static void main(String[] args) {
		int[] arr = new int[]{1, 3, 8, 9, 2 ,5};
		new MergeSort(arr).display();	
	}
}


class MergeSort{
	public int[] sortArr;
	
	public MergeSort(int[] arr) {
		sortArr = arr;
	
		int[] tempArray = new int[arr.length];
		recMergeSort(tempArray, 0, arr.length - 1);
	}
	
	public void recMergeSort(int[] arr, int lower, int upper) {
		if(lower != upper){
			int mid = (upper + lower) / 2;
			recMergeSort(arr, lower, mid);
			recMergeSort(arr, mid + 1, upper);
			Merage(arr, lower, mid, upper);
		}
	}
	
	public void Merage(int[] arr, 
							int lower, 
							int mid,
							int upper) {
		int upperPtr = mid + 1;
		int lowerbound = lower;
		
		int top = 0;
		while(lower <= mid && upperPtr <= upper) {
			if(sortArr[lower] <= sortArr[upperPtr]) {
				arr[top++] = sortArr[lower++];
			}else {
				arr[top++] = sortArr[upperPtr++];
			}
		}
		
		while(lower <= mid) {
			arr[top++] = sortArr[lower++];
		}
		
		while(upperPtr <= upper) {
			arr[top++] = sortArr[upperPtr++];
		}
		for(int j = 0; j < upper - lowerbound + 1; j++) {
			sortArr[lowerbound+j]  = arr[j];
		}
	}
	
	public void display() {
		System.out.println("ÅÅÐòºó½á¹û:");
		for(int i = 0; i < sortArr.length; i++) {
			System.out.print(sortArr[i] + "   ");
		}
		System.out.println();
	}
}