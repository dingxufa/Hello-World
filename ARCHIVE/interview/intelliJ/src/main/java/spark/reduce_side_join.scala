package spark

import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by ding on 2018/5/7.
 */
object reduce_side_join {
  def main(args: Array[String]) {
    var conf = new SparkConf().setAppName("map_side_join").setMaster("local[2]");
    var sc = new SparkContext(conf);
    var table1 = sc.textFile(args(1));
    var table2 = sc.textFile(args(2));

    var pairs = table1.map(line=>{
      var pos = line.indexOf(",")
      (line.substring(0,pos),line.substring(pos+1))
    })
    var result = table2.map(line =>{
      var pos = line.indexOf(",")
      (line.substring(0,pos),line.substring(pos+1))
    }).join(pairs)

    result.saveAsTextFile(args(3))
  }
}
