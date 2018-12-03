package algorithm.mystack;

import java.util.Arrays;

/**
 * Created by ding on 2018/2/25.
 */
public class ThreeSumFast {
    public static  int count(int[] arr){
        Arrays.sort(arr);
        int N = arr.length;
        int cnt = 0;
        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                for(int k=j+1;k<N;k++){
                    // rank() 方法返回元素在数组中的下标，如果元素不存在，这里会返回 -1。应该注意这里的下标必须大于 j，这样就不会重复统计了。
//                    if(BinarySearch.rank(-arr[i]-arr[j],arr)>j){
//                        cnt++;
//                    }
                }
            }
        }
        return cnt;
    }

}
