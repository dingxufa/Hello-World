package scala

import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by ding on 2018/3/29.
 */
object WordCount {
  def main(args: Array[String]) {
    /*val input = "hdfs://server0:9000/wc"
    val output = "hdfs://server0:9000/out/out1"  //注意hdfs文件权限问题
    val conf= new SparkConf().setAppName("WC").setMaster("local[2]").set("spark.testing.memory","2147480000")
    System.setProperty("user.name","hadoop")
    val sc = new SparkContext(conf)

    sc.textFile(input).flatMap(_.split(" ")).map((_,1)).reduceByKey(_ + _).sortBy(_._2,false).saveAsTextFile(output)
    sc.stop()*/
  }
}
