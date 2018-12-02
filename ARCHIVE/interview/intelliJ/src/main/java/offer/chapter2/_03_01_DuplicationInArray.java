package offer.chapter2;

// 面试题3（一）：找出数组中重复的数字
// 题目：在一个长度为n的数组里的所有数字都在0到n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
// 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。例如，如果输入长度为7的数组{2, 3, 1, 0, 2, 5, 3}，
// 那么对应的输出是重复的数字2或者3。
public class _03_01_DuplicationInArray {
    public static void exch(int[] arr,int i,int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] =t;
    }
    public static boolean duplicate(int[] numbers){
        //输入不符合要求
        if(numbers==null||numbers.length<0){
            return false;
        }
        //输入不符合要求
        for(int i=0;i<numbers.length;i++){
            if(numbers[i]<0||numbers[i]>numbers.length-1){
                return false;
            }
        }
        for(int i=0;i<numbers.length;i++){
            while(numbers[i] != i){
                if(numbers[i] == numbers[ numbers[i]  ]  ){
                    System.out.println("重复数字："+ numbers[i]);
                    return true;
                }else{
                    exch(numbers,i,numbers[i]);
                }
            }
        }
        return false;
    }


    public static boolean duplicate2(int[] arr){
        if(arr == null || arr.length<0){
            return false;
        }
        for(int i=0;i<arr.length;i++){
            if(arr[i] <0 || arr[i]>arr.length-1){
                return false;
            }
        }
        for(int i=0;i<arr.length;i++){
            while(i != arr[i]){
                if(arr[i] == arr[ arr[i]  ] ){
                    System.out.println("重复数字："+ arr[i]);
                    return true;
                }else{
                    exch(arr,i, arr[i]);
                }
            }
        }
        return false;
    }


    public static void main(String[] args){
           // duplicate(new int[]{2,3,1,0,2,5,3}); //时间O(n)  空间O(1)
        System.out.println(duplicate(new int[]{2,3,1,0,2,5,3}));
          //  duplicate(new int[]{2,3,1,0,4,5,6});
        System.out.println(duplicate(new int[]{2,3,1,0,4,5,6}));
          //  duplicate(new int[]{2,3,1,0,4,5,17});
        System.out.println(duplicate(new int[]{2,3,1,0,4,5,17}));

        System.out.println("==================");
        System.out.println(duplicate2(new int[]{2,3,1,0,2,5,3}));
        System.out.println(duplicate2(new int[]{2,3,1,0,4,5,6}));
        System.out.println(duplicate2(new int[]{2,3,1,0,4,5,17}));
    }
}
