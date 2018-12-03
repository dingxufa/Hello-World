package algorithm_exercise;

import clojure.lang.Obj;

import java.util.LinkedList;

/**
 * 自定义Map的升级版：
 * 1. 提高查询的效率
 *
 *
 */
public class MyHashMap {
    private class Entry{
        private Object key;
        private Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    LinkedList[]  arr  = new LinkedList[9]; //Map的底层结构就是：数组+链表!
    int size;

    public void put(Object key,Object value){
        Entry  e = new Entry(key,value);

//        int hash = key.hashCode();
//        hash = hash<0?-hash:hash;
       int hash = Math.abs(key.hashCode());

        int a = hash%arr.length;
        if(arr[a]==null){
            LinkedList list = new LinkedList();
            arr[a] = list;
            list.add(e);
        }else{
            LinkedList list = arr[a];
            for(int i=0;i<list.size();i++){
                Entry e2 = (Entry) list.get(i);
                if(e2.key.equals(key)){
                    e2.value = value;  //键值重复直接覆盖！
                    return;
                }
            }

            arr[a].add(e);
        }
        //a:1000-->1   b:10000-->13
    }

    public Object get(Object key){
        int a = key.hashCode()%arr.length;
        if(arr[a]!=null){
            LinkedList list = arr[a];
            for(int i=0;i<list.size();i++){
                Entry e = (Entry) list.get(i);
                if(e.key.equals(key)){
                    return e.value;
                }
            }
        }
        return null;
    }


}

