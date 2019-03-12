

windows

启动Redis server：

运行cmd

然后到redis路径

运行命令: redis-server redis.windows.conf



启动客户端

redis-cli.exe



```shell

D:\software\Redis-x64-3.2.100>redis-cli.exe
127.0.0.1:6379> set hello world
OK
127.0.0.1:6379> get hello
"world"
127.0.0.1:6379> del hello
(integer) 1
127.0.0.1:6379> get hello
(nil)
127.0.0.1:6379>
127.0.0.1:6379>
127.0.0.1:6379> rpush list-key item
(integer) 1
127.0.0.1:6379> rpush list-key item2
(integer) 2
127.0.0.1:6379> lrange list-key 0 -1
1) "item"
2) "item2"
127.0.0.1:6379> lpush list-key item3
(integer) 3
127.0.0.1:6379> lrange list-key 0 -1
1) "item3"
2) "item"
3) "item2"
127.0.0.1:6379> lindex list-key 1
"item"
127.0.0.1:6379> lpop list-key
"item3"
127.0.0.1:6379> lrange list-key 0 -1
1) "item"
2) "item2"


127.0.0.1:6379> sadd set-key item
(integer) 1
127.0.0.1:6379> sadd set-key item2
(integer) 1
127.0.0.1:6379> sadd set-key item3
(integer) 1
127.0.0.1:6379> sadd set-key item
(integer) 0
127.0.0.1:6379> smembes set-key
(error) ERR unknown command 'smembes'
127.0.0.1:6379> smembers set-key
1) "item3"
2) "item"
3) "item2"
127.0.0.1:6379> sismember set-key item4
(integer) 0
127.0.0.1:6379> sismember set-key item
(integer) 1
127.0.0.1:6379> srem set-key item2
(integer) 1
127.0.0.1:6379> srem set-key item2
(integer) 0
127.0.0.1:6379> smembers set-key
1) "item3"
2) "item"
127.0.0.1:6379>





127.0.0.1:6379>
127.0.0.1:6379>
127.0.0.1:6379>
127.0.0.1:6379> hset hash-key sub-key1 value1
(integer) 1
127.0.0.1:6379> hset hash-key sub-key2 value2
(integer) 1
127.0.0.1:6379> hset hash-key sub-key1 value1
(integer) 0
127.0.0.1:6379> hgetall hash-key
1) "sub-key1"
2) "value1"
3) "sub-key2"
4) "value2"
127.0.0.1:6379> hdel hash-key sub-key2
(integer) 1
127.0.0.1:6379> hget hash-key sub-key2
(nil)
127.0.0.1:6379> hget hash-key sub-key1
"value1"
127.0.0.1:6379> hgetall hahs-key
(empty list or set)
127.0.0.1:6379> hetall hash-key
(error) ERR unknown command 'hetall'
127.0.0.1:6379> hgetall hash-key
1) "sub-key1"
2) "value1"
127.0.0.1:6379>


127.0.0.1:6379>
127.0.0.1:6379> zadd zset-key 728 member1
(integer) 1
127.0.0.1:6379> zadd zset-key 982 member0
(integer) 1
127.0.0.1:6379> zadd zset-key 982 member0
(integer) 0
127.0.0.1:6379> zrange zset-key 0 -1 withscores
1) "member1"
2) "728"
3) "member0"
4) "982"
127.0.0.1:6379> zrangebyscore zset-key 0 800 withscores
1) "member1"
2) "728"
127.0.0.1:6379> zrem zset-key member1
(integer) 1
127.0.0.1:6379> zrem zset-key member1
(integer) 0
127.0.0.1:6379> zrange zset-key 0 -1 withscores
1) "member0"
2) "982"
127.0.0.1:6379>
127.0.0.1:6379> zscore zset-key member1  
(nil)
127.0.0.1:6379> zscore zset-key member0
"982"
127.0.0.1:6379>


```


sds(simple dynamic string 简单)





