package scala

/**
 * Created by ding on 2018/4/17.
 */

object Test{

  def apply():(Int,Int)={(1,2)}
  def fun(){print("tttt")}
  //  def main(args: Array[String]) {
  //    val t = Test();
  //    print(t+" aa   ")
  //    val p=new Person[String]().choose("a","b")
  //    println("p="+p)
  //   // Test.fun()
  //    val tmp = new Chooser2[Int]().bigger(3,6)
  //    print(tmp)
}


//class Person[T]{
//  def choose[T <: Comparable[T]](first:T,second:T):T={
//    if(first.compareTo(second)>0) first else second
//  }
//}
//

//class Chooser[T <% Ordered[T]]{
//  def bigger(first:T,second:T)={
//    if(first > second) first else second
//  }
//}
//class Chooser2[T]{
//  def bigger(first:T,second:T)(implicit ord:T => Ordered[T])={
//    if(first > second) first else second
//  }
//}
//class Chooser3[T : Ordering[T] ] {
//  def bigger(first:T,second:T):T={
//    val ord   = implicitly [Ordering[T]]
//    if(first > second) first else second
//  }
//}
//
//class Chooser4[T]{
//  def bigger(first:T,second:T)(implicit ord:Ordering[T]):T={
//    if(ord.gt(first,second))first else  second
//  }

