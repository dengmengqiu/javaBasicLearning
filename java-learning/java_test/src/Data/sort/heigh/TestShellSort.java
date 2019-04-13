package Data.sort.heigh;

public class TestShellSort {
	public static void main(String[] args) {
		int[] arr = new int[] {14, 5, 2 ,7, 1 ,9 ,2};
		ShellSort sort =  new ShellSort(arr);
		sort.shellSort();
		sort.display();
	}
}

//n-增量排序
//由Knuth提出的间隔序列为 1， 4， 13, ... ， 3*n + 1
class ShellSort{
	private int[] arr;
	
	public ShellSort(int[] a) {
		arr = a;
	}
	
	public void display() {
		for(int j = 0; j < arr.length; j++) {
			System.out.println(arr[j] + " ");
		}
	}
	
	public void shellSort() {
		int h = 1;
		
		while(h < arr.length/3) {
			h = h * 3 + 1;
		}
		
		while(h > 0) {
			//以h为间隔的分组
			for(int i = h; i < arr.length; i++) {
				//插入排序
				int temp = arr[i];
				int j;
				for(j = i; j > h -1 && arr[j - h] > temp; j-=h) {
					arr[j] = arr[j-h];
				}
				arr[j] = temp;
			}
			h = (h - 1)/ 3;
		}
	}
}