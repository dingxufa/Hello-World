package offer.chapter2;

// 面试题3（二）：不修改数组找出重复的数字
// 题目：在一个长度为n+1的数组里的所有数字都在1到n的范围内，所以数组中至
// 少有一个数字是重复的。请找出数组中任意一个重复的数字，但不能修改输入的
// 数组。例如，如果输入长度为8的数组{2, 3, 5, 4, 3, 2, 6, 7}，那么对应的
// 输出是重复的数字2或者3。
public class _03_02_DuplicationInArrayNoEdit {
    public static int getDuplication(int[] numbers){
        if(numbers==null||numbers.length<0){
            return -1;
        }
        int start =1;
        int end = numbers.length-1;
        while(start<=end){
            int middle = (start+end)/2;
            int count = countRange(numbers,start,middle);
            if(count > (middle-start+1)){
                end = middle;
            }else{
                start = middle+1;
            }

            if(end==start){
                if (count>1){
                    return start;
                }else{
                    break;
                }
            }
        }
        return -1;
    }

    public static int countRange(int[] arr,int start,int end){
        if (arr == null) return 0;
        int count = 0;
        for(int i=0;i<arr.length;i++){
            if(start <= arr[i] && arr[i] <= end){
                ++count;
            }
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println(getDuplication(new int[]{2,3,5,4,3,2,6,7}));
        System.out.println(getDuplication(new int[]{2,3,1,0,2,5,3}));
    }
}
