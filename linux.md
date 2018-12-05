# 1 

双引号允许shell解释字符串中出现的特殊字符。单引号不会对其做任何解释。

```shell
[hadoop@mini0 ~]$ echo "hello!"
-bash: !": event not found
[hadoop@mini0 ~]$ echo 'hello!'
hello!
[hadoop@mini0 ~]$ echo hello!
hello!
[hadoop@mini0 ~]$ echo "hello\thi"
hello\thi
[hadoop@mini0 ~]$ echo "hello \t hi"
hello \t hi
[hadoop@mini0 ~]$ echo -e "hello\ter"
hello   er
#echo 会在输出文本的尾部追加一个换行符。可以使用选项 -n 来禁止这种行为。echo 同样接受双包含转义序列的双引号字符串作为参数。在使用转义序列时，需要使用 echo -e" 包含转义序列的字符串 " 这种形式
```

 printf 中使用格式化字符串来指定字符串的宽度、左右对齐方式等。默认情况下， printf 并不会自动添加换行符

```shell
[hadoop@mini0 ~]$ printf "%-5s %-10s %-4s\n" No Name Mark
No    Name       Mark
[hadoop@mini0 ~]$ printf "%-5s %-10s %-4.2f\n" 1 Sarath 80.3456
1     Sarath     80.35
[hadoop@mini0 ~]$ printf "%-5s %-10s %-4.2f\n" 2 James 90.9989
2     James      91.00
[hadoop@mini0 ~]$ printf "%-5s %-10s %-4.2f\n" 3 Jeff 77.564
3     Jeff       77.56

# %-5s 指明了一个格式为左对齐且宽度为5的字符串替换（ - 表示左对齐）。如果不指明 - ，字符串就采用右对齐形式
# %-4 . 2f ，其中. 2 指定保留两位小数
```

## 查看当前shell中所定义的全部环境变量

```shell
[hadoop@mini0 ~]$ env
TOMCAT_HOME=/home/hadoop/app/tomcat
XDG_SESSION_ID=1
HOSTNAME=mini0

[hadoop@mini0 ~]$ printenv
TOMCAT_HOME=/home/hadoop/app/tomcat

#cat /proc/$PID/environ
#  pgrep 命令获得gedit的进程ID：
$ pgrep gedit
12501
#与该进程相关的环境变量
$ cat /proc/12501/environ
GDM_KEYBOARD_LAYOUT=usGNOME_KEYRING_PID=1560USER=slynuxHOME=/home/slynux

#特殊文件/proc/PID/environ是一个包含环境变量以及对应变量值的列表。每一个变量以 name=value 的形式来描述，彼此之间由null字符（ \0 ）分隔。形式上确实不太易读。
#要想生成一份易读的报表，可以将 cat 命令的输出通过管道传给 tr ，将其中的 \0 替换成 \n：
$ cat /proc/12501/environ | tr '\0' '\n'
```

```shell
varName=value
#如果 value 不包含任何空白字符（例如空格），那么就不需要将其放入引号中，否则必须使用单引号或双引号。
[hadoop@mini0 ~]$ var="value"
[hadoop@mini0 ~]$ echo $var
value
[hadoop@mini0 ~]$ echo ${var}
value

[hadoop@mini0 ~]$ vi test.sh
#!/bin/bash
fruit=apple
count=5
echo "We have $count ${fruit}(s)"

[hadoop@mini0 ~]$ chmod a+x test.sh 
[hadoop@mini0 ~]$ ./test.sh 
We have 5 apple(s)
```

把 var=value 写成 var = value是一个常见的错误。**两边没有空格的等号是赋值操作符，加上空格的等号表示的**
**是等量关系测试。**



环境变量是从父进程中继承而来的变量。

```shell
HTTP_PROXY=192.168.1.23:3128
export HTTP_PROXY
#export 命令声明了将由子进程所继承的一个或多个变量。这些变量被导出后，当前shell脚本所执行的任何应用程序都会获得这个变量。
[hadoop@mini0 ~]$ echo '$PATH:/HJ'
$PATH:/HJ
[hadoop@mini0 ~]$ echo "$PATH:/HJ"
/usr/local/bin:....
```



```shell
#1. 获得字符串的长度
[hadoop@mini0 ~]$ var=123
[hadoop@mini0 ~]$ echo ${#var}
3

#2. 识别当前所使用的shell
[hadoop@mini0 ~]$ echo $SHELL
/bin/bash
[hadoop@mini0 ~]$ echo $0
-bash

#3. 检查是否为超级用户
#环境变量 UID 中保存的是用户ID  root用户的 UID 是 0 。
[hadoop@mini0 ~]$ vi test.sh 
#!/bin/bash
if [ $UID -ne  0 ];then    #注意， [ 实际上是一个命令，必须将其与剩余的字符串用空格隔开
        echo Non root user.please run as root.
else
        echo ROOt user.
fi

[hadoop@mini0 ~]$ ./test.sh 
Non root user.please run as root.
[hadoop@mini0 ~]$ 


#4. 修改Bash的提示字符串（ username@hostname:~$ ）
查看设置变量 PS1 的那一行：
$ cat ~/.bashrc | grep PS1
PS1='${debian_chroot:+($debian_chroot)}\u@\h:\w\$ '
 如果要修改提示字符串，可以输入：
slynux@localhost: ~$ PS1="PROMPT>" #提示字符串已经改变
PROMPT> Type commands here.
```

**注意， [ 实际上是一个命令，必须将其与剩余的字符串用空格隔开**





## 如何将新的路径添加到环境变量的起始部分

```SHELL
export PATH=/opt/myapp/bin:$PATH
export LD_LIBRARY_PATH=/opt/myapp/lib; $LD_LIBRARY_PATH
PATH 和 LD_LIBRARY_PATH 现在看起来应该像这样：
PATH=/opt/myapp/bin:/usr/bin:/bin
LD_LIBRARY_PATH=/opt/myapp/lib:/usr/lib; /lib

我们可以在.bashrc文件中定义如下函数，简化路径添加操作：
prepend() { [ -d "$2" ] && eval $1=\"$2':'\$$1\" && export $1; } #-d 判断目录是否存在
该函数用法如下：
prepend PATH /opt/myapp/bin
prepend LD_LIBRARY_PATH /opt/myapp/lib

[hadoop@mini0 ~]$ test(){ [ -d $2 ] && echo "$2':'exists" ; } #注意这里的;
[hadoop@mini0 ~]$ test path /home
/home':'exists
[hadoop@mini0 ~]$ test path  
':'exists   


#函数 prepend() 首先确认该函数第二个参数所指定的目录是否存在。
#如果存在， eval 表达式将第一个参数所指定的变量值设置成第二个参数的值加上 : （路径分隔符），随后再跟上第一个参数的原始值。
#但是在进行添加时，如果变量为空，则会在末尾留下一个 : 。
prepend() { [ -d "$2" ] && eval $1=\"$2':'\$$1\" && export $1; }  #or
prepend() { [ -d "$2" ] && eval $1="$2':'\$$1" && export $1; }
#经过测试 eval  $1=""  不需要转义
[hadoop@mini0 ~]$ test(){ [ -d $2 ] && eval $1="$2:\$$1" ; }
[hadoop@mini0 ~]$ test PATH /home
[hadoop@mini0 ~]$ echo $PATH
/home:/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/sbin:/usr/local/jdk1.7.0_79/bin:
[hadoop@mini0 ~]$ test(){ [ -d $2 ] && eval $1="$2:$1" ; }
[hadoop@mini0 ~]$ test PATH /home/hadoop
[hadoop@mini0 ~]$ echo $PATH
/home/hadoop:PATH



[hadoop@mini0 ~]$ test(){ [ -d $2 ] && echo "$2 exists" && eval $1="$2':'\$$1"; echo $$1; }
[hadoop@mini0 ~]$ test PATH /home/hadoop
/home/hadoop exists
15711
[hadoop@mini0 ~]$ echo $PATH
/home/hadoop:/home:/home:/home:/home:/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/sbin:

[hadoop@mini0 ~]$ test(){ [ -d $2 ] && echo "$2 exists" && eval $1="$2':'\$$1"; echo \$$1; }
[hadoop@mini0 ~]$ test PATH /home/hadoop/tars
/home/hadoop/tars exists
$PATH
[hadoop@mini0 ~]$ echo $PATH
/home/hadoop/tars:/home/hadoop:/home:/home:/home:/home:/usr/local/bin:/usr/bin:/usr/local/sbin:
[hadoop@mini0 ~]$ 



[hadoop@mini0 ~]$ fun(){ echo "$1" ; }
[hadoop@mini0 ~]$ fun PATH
PATH
[hadoop@mini0 ~]$ fun(){ echo "$$1" ; }
[hadoop@mini0 ~]$ fun PATH
15711
[hadoop@mini0 ~]$ fun(){ echo "\$$1" ; }
[hadoop@mini0 ~]$ fun PATH
$PATH
[hadoop@mini0 ~]$ fun(){ echo "$(\$$1)" ; }
[hadoop@mini0 ~]$ fun PATH
-bash: $PATH: command not found
[hadoop@mini0 ~]$ fun(){ $("echo  \$$1") ; }
[hadoop@mini0 ~]$ fun PATH
-bash: echo  $PATH: command not found


```





## 1.5使用 shell 进行数学运算

```shell
#(1) 可以像为变量分配字符串值那样为其分配数值
#(2)  let 命令可以直接执行基本的算术操作。当使用 let 时，变量名之前不需要再添加 $
[hadoop@mini0 ~]$ no1=4
[hadoop@mini0 ~]$ no2=5
[hadoop@mini0 ~]$ let result=no1+no2
[hadoop@mini0 ~]$ echo $result
9
[hadoop@mini0 ~]$ let no1++
[hadoop@mini0 ~]$ echo $no1
5
[hadoop@mini0 ~]$ let no2--
[hadoop@mini0 ~]$ echo $no2
4
#其他方法   操作符 [] 的使用方法和 let 命令一样：
[hadoop@mini0 ~]$ let re1=$[ no1 + no2 ]
[hadoop@mini0 ~]$ echo $re1
9
#在 [] 中也可以使用 $ 前缀，例如：
result=$[ $no1 + 5 ]
也可以使用操作符 (()) 。出现在 (()) 中的变量名之前需要加上 $ ：
result=$(( no1 + 50 ))
[hadoop@mini0 ~]$ re2=$(( $no1 + 1))
[hadoop@mini0 ~]$ echo $re2
6

#expr 同样可以用于基本算术操作：
result=`expr 3 + 4`
result=$(expr $no1 + 5)
#以上这些方法不支持浮点数，只能用于整数运算



#(3)  bc 是一个用于数学运算的高级实用工具，这个精密的计算器包含了大量的选项。我们可以借助它执行浮点数运算并使用一些高级函数：
[hadoop@mini0 ~]$ echo "4 * 0.56 " | bc
2.24
[hadoop@mini0 ~]$ no=54;
[hadoop@mini0 ~]$ result=`echo "$no * 1.5" | bc`
[hadoop@mini0 ~]$ echo $result
81.0

#bc 可以接受操作控制前缀。这些前缀之间使用分号分隔。
#设定小数精度  参数 scale=2 将小数位个数设置为 2
[hadoop@mini0 ~]$ echo "scale=2;22/7" | bc
3.14
#进制转换。用 bc 可以将一种进制系统转换为另一种。
[hadoop@mini0 ~]$ no=100
[hadoop@mini0 ~]$ echo "obase=2;$no" | bc
1100100
[hadoop@mini0 ~]$ no=1100100
[hadoop@mini0 ~]$ echo "obase=10;$no" | bc
1100100
[hadoop@mini0 ~]$ echo "obase=10;ibase=2;$no" | bc
100

#计算平方以及平方根。
[hadoop@mini0 ~]$ echo "sqrt(100)" | bc
10
[hadoop@mini0 ~]$ echo "10^10" | bc #Square
10000000000

```



## 1.6文件描述符与重定向

标准输入（ stdin ）、标准输出（ stdout ）和标准错误（ stderr ）。

脚本可以使用大于号将输出重定向到文件中。命令产生的文本可能是正常输出，也可能是错误信息。默认情况下，正常输出（ stdout ）和错误信息（ stderr ）都会显示在屏幕上。

文件描述符是与某个打开的文件或数据流相关联的整数。文件描述符 0 、 1 以及 2 是系统预留的。
  0 ——  stdin  （标准输入）。
  1 ——  stdout （标准输出）。
  2 ——  stderr （标准错误）。



```shell
(1) 使用大于号将文本保存到文件中：
$ echo "This is a sample text 1" > temp.txt
#该命令会将输出的文本保存在temp.txt中。如果temp.txt已经存在，大于号会清空该文件中先前的内容。
(2) 使用双大于号将文本追加到文件中：
$ echo "This is sample text 2" >> temp.txt
```





```
[root@app7 etc]# echo  "1 2 3 4\nhello\n5 6"
1 2 3 4\nhello\n5 6
[root@app7 etc]# echo -e "1 2 3 4\nhello\n5 6"
1 2 3 4
hello
5 6


$# ：传给脚本的参数个数;
$0 ：脚本名称;
$n ：n为数字，代表传给脚本的第n个参数;
$@ ：参数列表;
$* ：也是显示参数列表,与上一条命令不同的是,当在双引号里面时,”$*”表示一个参数,即”a b c”,而”$@”表示三个参数,即”a” “b” “c”;
$$ ：执行当前脚本的进程ID;
$? ：最后一条命令的退出状态,0表示执行成功,非0表示执行失败.

echo "number:$#"
echo "scname:$0"
echo "first :$1"
echo "second:$2"
echo "third :$3"
echo "fourth:$4"
echo "argume:$@"
echo "show parm list:$*"
echo "show process id:$$"
echo "show precomm stat: $?"
--------------------- 

[root@app7 ~]# ./test2.sh a b c
number:3
scname:./test2.sh
first :a
second:b
third :c
fourth:
argume:a b c
show parm list:a b c
show process id:21007
show precomm stat: 0





```



gawk 'BEGIN {FS=":"} { "grep root /etc/passwd" | getline; print $1,$6 }' 

[root@app7 ~]# gawk 'BEGIN{FS=":"} {name[$1]=$5} END{for( i in name){ print i,"\t", name[i]}}' /etc/passwd
ntp 	 
rpc 	 Rpcbind Daemon
vcsa 	 virtual console memory owner
dbus 	 System message bus








