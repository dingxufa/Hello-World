

centos:

yum -y install  gcc gcc-c++ autocof  pcre  pcre-devel make automake

yum -y install wget httpd-tools  vim





yum list | grep "gcc"

iptables -L  查看iptables规则

iptables -F 关闭IP规则

iptables -t  nat -L 



getenforce

setenforce 0 关闭selinux



为什么使用nginx?

1.IO多路复用epoll 	 

多个描述符的IO操作都在一个线程内并发交替地顺序完成，这就是IO多路复用，“复用”指的是复用同一个线程

2.什么是epoll

io多路复用的实现方式select poll epoll

3.CPU亲和

4.sendfile





[root@VM_0_12_centos ~]# touch /etc/yum.repos.d/nginx.repo
[root@VM_0_12_centos ~]# vim /etc/yum.repos.d/nginx.repo 

```
[nginx-stable]
name=nginx stable repo
baseurl=http://nginx.org/packages/centos/$releasever/$basearch/
gpgcheck=1
enabled=1
gpgkey=https://nginx.org/keys/nginx_signing.key
module_hotfixes=true

[nginx] #section header也要有
name=nginx stable repo
baseurl=http://nginx.org/packages/centos/7/$basearch/
gpgcheck=1
enabled=1
gpgkey=https://nginx.org/keys/nginx_signing.key
module_hotfixes=true

```



yum install nginx



rpm -ql nginx



安装目录



| 路径                                                         | 类型         | 作用                                       |
| ------------------------------------------------------------ | ------------ | ------------------------------------------ |
| /etc/logrotate.d/nginx                                       | 配置文件     | nginx日志轮转，用于logrotate服务的日志切割 |
| /etc/nginx/etc/nginx/nginx.conf                        /etc/nginx/conf.d /etc/nginx/conf.d/default.conf | 目录配置文件 | nginx主配置文件                            |
| /usr/sbin/nginx /usr/sbin/nginx-debug                        | 命令         | nginx服务的启动管理的终端命令              |
| /var/cache/nginx                                             | 目录         | nginx缓存目录                              |
| /var/log/nginx                                               |              | nginx日志目录                              |



nginx -V 

systemctl  restart  nginx.service 重启nginx服务

systemctl  reload nginx.service 重启nginx服务





/etc/nginx/nginx.conf

```
user  nginx;
worker_processes  1;

error_log  /var/log/nginx/error.log warn;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;

    #gzip  on;

    include /etc/nginx/conf.d/*.conf;
}
```



/etc/nginx/conf.d/default.conf

```

server {
    listen       80;
    server_name  localhost;

    #charset koi8-r;
    #access_log  /var/log/nginx/host.access.log  main;

    location / {
        root   /usr/share/nginx/html;
        index  index.html index.htm;
    }

    #error_page  404              /404.html;

    # redirect server error pages to the static page /50x.html
    #
    error_page   500 502 503 504  /50x.html;
    location = /50x.html {
        root   /usr/share/nginx/html;
    }

    # proxy the PHP scripts to Apache listening on 127.0.0.1:80
    #
    #location ~ \.php$ {
    #    proxy_pass   http://127.0.0.1;
    #}

    # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
    #
    #location ~ \.php$ {
    #    root           html;
    #    fastcgi_pass   127.0.0.1:9000;
    #    fastcgi_index  index.php;
    #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
    #    include        fastcgi_params;
    #}

    # deny access to .htaccess files, if Apache's document root
    # concurs with nginx's one
    #
    #location ~ /\.ht {
    #    deny  all;
    #}
}

```





curl -v 'http://www.baidu.com' > /dev/null



nginx -t -c  /etc/nginx/nginx.conf  检查nginx文件正确与否



nginx -s reload  -c   /etc/nginx/nginx.conf   检查nginx文件正确与否 





nginx变量

1.HTTP请求变量

arg_PARAMETER   http_HEADER  send_http_HEADER

 内置变量  nginx内置的

自定义变量





http_stub_status_module模块配置

syntax:   stub_status;

Default:  -

Context: server , location

eg:   /etc/nginx/conf.d/default.conf

```
server {
    listen       80;
    server_name  localhost;

    #charset koi8-r;
    #access_log  /var/log/nginx/host.access.log  main;

	location /myStatus {
		stub_status;
	}


    location / {
        root   /usr/share/nginx/html;
        index  index.html index.htm;
    }

```



nginx -tc /etc/nginx/nginx.conf

nginx -s reload -c  /etc/nginx/nginx.conf 





http://49.233.70.214/mystatus

```
Active connections: 3 
server accepts handled requests
 12 12 11 
Reading: 0 Writing: 1 Waiting: 2 
```









```
server {
    listen       80;
    server_name  localhost;

    #charset koi8-r;
    #access_log  /var/log/nginx/host.access.log  main;

    location /mystatus {
        stub_status;
    }

    location / {
        root   /usr/share/nginx/html;
        random_index  on;
        #index  index.html index.htm;
    }



[root@VM_0_12_centos conf.d]# nginx  -tc /etc/nginx/nginx.conf 
nginx: the configuration file /etc/nginx/nginx.conf syntax is ok
nginx: configuration file /etc/nginx/nginx.conf test is successful
```





# --with-http_sub_module



syntax: sub_filter string replacement;

Default: -

Context: http, server,location



syntax: sub_filter_last_modified on|off;

Default: sub_filter_last_modified off;

Context: http, server,location



syntax: sub_filter_once on|off;   至匹配第一个

Default: sub_filter_once off;

Context: http, server,location



```

server {
    listen       80;
    server_name  localhost;

    #charset koi8-r;
    #access_log  /var/log/nginx/host.access.log  main;

    location /mystatus {
        stub_status;
    }

    location / {
        root   /usr/share/nginx/html;
        sub_filter '<a>hello' '<a>HELLO';
        sub_filter_once off;
        index  index.html index.htm;
    }

```





nginx请求限制

连接频率限制  -  limit_conn_module

请求频率限制  -  limit_req_module

链接频率限制  -  limit_conn_module

![image-20191230005737055](../AppData/Roaming/Typora/typora-user-images/image-20191230005737055.png)



http请求建立在一次TCP连接基础上

一次TCP请求至少产生一次HTTP请求



Syntax: limit_conn_zone key zone=name:zie;

Default: -

Context: http



Syntax: limit_conn   zone number;

Default: -

Context: http,server,location



Syntax: limit_conn_zone key zone=name:zie rate=rate;

Default: -

Context: http



Syntax: limit_req zone=name [burst=number] [nodelay];

Default: -

Context: http





ab -n 50 -c 20  url

50个请求  20并发





nginx的访问控制

基于IP的访问控制 -    http_access_moudle

基于用户的信任登陆-    http_auth_basic_moudle

Syntax:  allow address | CIDR | unix | all ;    CIDR是网段 uinx是基于unix的socket

Default: -

Context: http，server,location, limit_except;



Syntax:  deny address | CIDR | unix | all ;    CIDR是网段 uinx是基于unix的socket

Default: -

Context: http，server,location, limit_except;