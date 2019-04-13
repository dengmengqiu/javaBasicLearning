public class TestHeapSort {
    public static void main(String[] args){
        HeapSort heap = new HeapSort(new int[]{23, 21, 34, 45, 56, 78, 12, 32, 34, 45, 56, 65, 98, 233, 234, 76, 98});
        heap.heapSort();
//        heap.displayHeap();
    }
}

class HeapSort{
    private int[] arr;
    private int size;

    public HeapSort(int[] arr){
        this.arr = arr;
        this.size = arr.length;
        for(int i = 0; i < (this.size - 1)/ 2; ){
            makeHeap(i, this.size-1);
            i = i * 2 + 1;
        }
    }

    public void makeHeap(int start, int end){
        for(int parentIndex = (end - 1) / 2; parentIndex >= start; parentIndex--){
            heapJust(arr, parentIndex, end);
        }
    }

    public void heapJust(int[] arr, int parentIndex, int end){
        int child1Index = parentIndex * 2 + 1;
        int child2Index = parentIndex * 2 + 2;
        if(child1Index > end){
            return;
        }else if(child2Index > end){
            if(arr[parentIndex] < arr[child1Index]){
                int temp = arr[parentIndex];
                arr[parentIndex] = arr[child1Index];
                arr[child1Index] = temp;
            }
        }else{
           if(arr[parentIndex] < arr[child1Index]){
                if(arr[child1Index] < arr[child2Index]){
                    int temp = arr[child2Index];
                    arr[child2Index] = arr[parentIndex];
                    arr[parentIndex] = temp;
                }else{
                    int temp = arr[child1Index];
                    arr[child1Index] = arr[parentIndex];
                    arr[parentIndex] = temp;
                }
           }else{
                if(arr[parentIndex] < arr[child2Index]){
                    int temp = arr[child2Index];
                    arr[child2Index] = arr[parentIndex];
                    arr[parentIndex] = temp;
                }
           }
        }
    }

    public void displayHeap(){
        int l = (int)(Math.log(this.size) / Math.log(2)) + 1;
       for(int i = 0; i < this.size; ){
           for(int j = i; j < i * 2 + i && j < this.size; j++){
               System.out.print(arr[j] + "  ");
           }
           System.out.println();
           i = i * 2 + 1;
       }
    }

    public void heapSort(){
        while (this.size > 0){
            System.out.print(pop() + " ");
        }
    }

    public int pop(){
        int temp = arr[0];
        arr[0] = arr[this.size-1];
        arr[this.size-1] = temp;
        this.size--;
//        trackDown();
        makeHeap(0, this.size-1);
        return temp;
    }

    public void trackDown(){
        for(int i = 0; i < this.size;){
            if(i * 2 + 1 < this.size){
                return;
            }else if(i * 2 + 2 < this.size){
                if(arr[i * 2 + 1] > arr[i]){
                    int temp = arr[i];
                    arr[i] = arr[i * 2 + 1];
                    arr[i * 2 + 1] = temp;
                    i = i * 2 + 1;
                }else{
                    return;
                }
            }else{
                if(arr[i * 2 + 1] > arr[i]){
                    if(arr[i * 2 + 1] > arr[i * 2]){
                        int t = arr[i];
                        arr[i] = arr[i * 2 + 1];
                        i = i * 2 + 1;
                    }else{
                        int t = arr[i];
                        arr[i] = arr[i * 2 + 2];
                        i = i * 2 + 2;
                    }
                }else if(arr[i * 2 + 2] > arr[i]){
                    int t = arr[i];
                    arr[i] = arr[i * 2 + 2];
                    arr[i * 2 + 2] = t;
                    i = i * 2 + 2;
                }else{
                    return;
                }
            }
        }
    }
}
