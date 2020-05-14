
Rabbitmq
启动管理端名利：\RabbitMQ Server\rabbitmq_server-3.8.3\sbin>rabbitmq-plugins enable rabbitmq_management
Rabbitmq管理端地址：http://localhost:15672 默认用户密码：guest/guest

Elasticsearch
启动命令：./bin/elasticsearch.bat
访问地址：http://localhost:9200/

Kibana
启动命令：./bin/kibana.bat
访问地址：http://localhost:5601

Logstash
启动命令：./bin/logstash.bat -f config/logstash-cuner.conf

zipkin
访问地址：http://localhost:9411


发送Kafka：
hystrix、turbine监控信息：destination(Topic)=hystrixStreamDest
zipkin跟踪日志：destination(Topic)=zipkin

日志整合：
APP-->zipkin-->kafka-->zipkin-->elastic-->kibana
启动顺序：kafka-->elastic-->kibana-->zipkin
