[root@testtech6-193 test]# ls -lS --time-style=long-iso
total 4268
-rw-r--r--. 1 root root 1670108 2018-11-18 12:27 dubbo-registry-10.200.4.74.cache
-rw-r--r--. 1 root root 1552396 2018-11-18 12:27 pet_payment_dubbo.registry.properties
-rw-r--r--. 1 root root  625417 2018-11-18 12:27 tnt_clutter_dubbo.registry.properties
-rw-r--r--. 1 root root  310369 2018-11-18 12:27 pet_payfront_dubbo.registry.properties
-rw-r--r--. 1 root root  125872 2018-11-18 12:27 lvmm_dest_back_dubbo.registry.properties
-rw-r--r--. 1 root root   34946 2018-11-18 12:27 lvmm_dest_hotel_trade_dubbo.registry.properties
-rw-------. 1 root root    2463 2018-11-18 12:27 fcinfo-1-testtech6-193-RedHat-7.1.1503-en.properties
-rw-------. 1 root root    1339 2018-11-18 12:27 anaconda-ks.cfg
-rw-r--r--. 1 root root    1134 2018-11-18 12:27 known_hosts
-rw-r--r-x. 1 root root     431 2018-11-18 12:45 remove_duplicates.sh
-rw-------. 1 root root     399 2018-11-18 12:27 authorized_keys
-rw-r--r--. 1 root root      93 2018-11-18 12:28 test_copy_fi
-rw-r--r--. 1 root root      93 2018-11-18 12:28 test_copy_file
-rw-r--r--. 1 root root      93 2018-11-18 12:27 test_file
-rw-r--r--. 1 root root      36 2018-11-18 12:27 93b98799d145f0b8.timestamp
-rw-r--r--. 1 root root      28 2018-11-18 12:27 data.txt
-rw-r--r--. 1 root root       0 2018-11-18 12:27 dubbo-registry-10.200.4.74.cache.lock
-rw-r--r--. 1 root root       0 2018-11-18 12:27 lvmm_dest_back_dubbo.registry.properties.lock
-rw-r--r--. 1 root root       0 2018-11-18 12:27 lvmm_dest_hotel_trade_dubbo.registry.properties.lock
-rw-r--r--. 1 root root       0 2018-11-18 12:27 pet_payfront_dubbo.registry.properties.lock
-rw-r--r--. 1 root root       0 2018-11-18 12:27 pet_payment_dubbo.registry.properties.lock
-rw-r--r--. 1 root root       0 2018-11-18 12:27 tnt_clutter_dubbo.registry.properties.lock
[root@testtech6-193 test]# ./remove_duplicates.sh 
name1= dubbo-registry-10.200.4.74.cache
size= 1670108
$5= 1670108
$6= 2018-11-18
$7= 12:27
$8= dubbo-registry-10.200.4.74.cache
pet_payment_dubbo.registry.properties
tnt_clutter_dubbo.registry.properties
pet_payfront_dubbo.registry.properties
lvmm_dest_back_dubbo.registry.properties
lvmm_dest_hotel_trade_dubbo.registry.properties
fcinfo-1-testtech6-193-RedHat-7.1.1503-en.properties
anaconda-ks.cfg
known_hosts
remove_duplicates.sh
authorized_keys
test_copy_fi
test_copy_file
test_copy_fi
test_copy_file
test_file
93b98799d145f0b8.timestamp
data.txt
dubbo-registry-10.200.4.74.cache.lock
lvmm_dest_back_dubbo.registry.properties.lock
dubbo-registry-10.200.4.74.cache.lock
lvmm_dest_back_dubbo.registry.properties.lock
lvmm_dest_hotel_trade_dubbo.registry.properties.lock
pet_payfront_dubbo.registry.properties.lock
lvmm_dest_hotel_trade_dubbo.registry.properties.lock
pet_payfront_dubbo.registry.properties.lock
pet_payment_dubbo.registry.properties.lock
tnt_clutter_dubbo.registry.properties.lock
pet_payment_dubbo.registry.properties.lock
tnt_clutter_dubbo.registry.properties.lock

#!/bin/bash
if [ $# -ne 1 ];then
        echo "usage is $0 basepath"
        exit
fi
path=$1
declare -A statarray
while read line;
do
        ftype=`file -b "$line" | cut -d, -f1`
        let statarray["$ftype"]++
done < <( find $path -type f -print )
echo ============ File types and counts =============
for ftype in "${!statarray[@]}";
do
                echo $ftype : ${statarray["$ftype"]}
done

============ File types and counts =============
OpenSSH RSA public key : 1
Vim swap file : 1
Bourne-Again shell script : 2
ASCII text : 21
empty : 6
UTF-8 Unicode text : 3



-rw-r--r--. 1 root root 1073741824 Nov 18 16:26 loobackfile.img


=========== chapter4.3
#!/bin/bash
# 文件名: silent_grep.sh
# 用途: 测试文件是否包含特定的文本内容

if [ $# -ne 2 ];then
	echo "Usage: $0 match_txt filename"
	exit 1
fi 
match_txt=$1
filename=$2
grep -q "$match_txt" $filename

if [ $? -eq 0 ];then 
	echo "The text exists in the file"
else
	echo  "Text does not exist in the file"
fi




gawk 'BEGIN{FS=":"} {name[$1]=$5} END{for( i in name){ print i,name[i]}}' /etc/passwd

=====4.7.2
#!/bin/bash
if [ $# -ne 1 ]; then
	echo "usage: $0 filename"
	exit -1
fi

filename=$1
egrep -o "\b[[:alpha:]]+\b" $filename | gawk '
	{ count[$0]++ }
	END{
		printf("%-14s%s\n","Word","Count");
		for( i in count ){
			printf("%-14s%d\n",i,count[i]);
		}
	}
'


#!/bin/bash
if [ $# -ne 1 ];then 
	echo "usage: $0 filename"
	exit -1
fi
filename=$1
egrep -o "\b[[:alpha:]]+\b" $filename | gawk '
	{ count[$0]++ }
	END{
		printf("%-14s%s\n","Word","Count");
		for ( key in count ){
			printf("%-14s%d\n", key, count[key] )
		}
	}
'
# [root@app7 ~]# ./word_freq.sh word.t
# Word          Count
# test          1
# a             1
# this          1
# is            1
# hello         2

# [root@app7 ~]# egrep '\b[[:alpha:]]+\b' word.t 
# hello this is a test hello
# You have mail in /var/spool/mail/root
# [root@app7 ~]# egrep '\b[[:alpha:]]+\b' word.t  -o
# hello
# this
# is
# a
# test
# hello

#!/bin/bash
if [ $# -ne 1 ];then 
	echo "usage: $0 filename"
	exit -1
fi
filename=$1
egrep -o "\b[[:alpha:]]+\b" $filename | gawk '
	{ count[$0]++ }
	END{
		printf("%-14s%s\n","Word","Count");
		for ( key in count ){
			printf("%-14s%d\n", key, count[key] )
		}
	}
'

#!/bin/bash
if [ $# -ne 1 ];then 
	echo "usage: $0 filename"
	exit -1
fi
filename=$1
egrep -o "\b[[:alpha:]]+\b" $filename | tr [A-Z] [a-z] | gawk '
	{ count[$0]++ }
	END{
		printf("%-14s%s\n","Word","Count");
		for ( key in count ){
			printf("%-14s%d\n", key, count[key] )
		}
	}
' | sort















































