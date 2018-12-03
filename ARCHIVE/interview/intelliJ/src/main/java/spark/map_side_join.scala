package spark

import clojure.main
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by ding on 2018/5/7.
 */
object map_side_join {
  def main(args: Array[String]) {
    var conf = new SparkConf().setAppName("map_side_join").setMaster("local[2]");
    var sc = new SparkContext(conf);
    var table1 = sc.textFile(args(1));
    var table2 = sc.textFile(args(2));
    // table1 is smaller, so broadcast it as a map<String, String>
    var pairs = table1.map(line => {
      var pos = line.indexOf(",")
      (line.substring(0, pos), line.substring(pos + 1))
    }).collectAsMap()

    var broadCastMap = sc.broadcast(pairs) //save table1 as map, and broadcast it
    // table2 join table1 in map side
    var result = table2.map { line => {
      var pos = line.indexOf(",")
      (line.substring(0, pos), line.substring(pos + 1))
    }
    }.mapPartitions({ iter =>
      var m = broadCastMap.value
      for {
        (key, value) <- iter if (m.contains(key))
      } yield  (key, (value, m.get(key).getOrElse("")))
    })
  }
}
