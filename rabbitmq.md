


rabbitmqctl status

rabbitmq-server -detached

rabbitmqctl add_user root root

rabbitmqctl set_permissions -p / root ".*" ".*" ".*"

rabbitmqctl set_user_tags root administor