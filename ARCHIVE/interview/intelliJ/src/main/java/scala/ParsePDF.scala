package scala

import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by ding on 2018/4/21.
 */
object ParsePDF {
  def main(args: Array[String]) {
    val conf: SparkConf = new SparkConf().setAppName("parsePDF").setMaster("local").set("spark.testing.memory","2147480000")
    val sc = new SparkContext(conf)
    val rdd1 = sc.textFile("D:/Program Files (x86)/aireader/preinst/epub")
    rdd1.map(line=>{print(line)})
    sc.stop()
  }

}
