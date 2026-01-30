This is s project that i am building to understand the microservices based application using java.

We have used inter service communication between our order service and inventory service.
We have used service discovery.
We have used API Gateway.
We have used keycloak for security.

We have used circuit breaker with relsilience4j.

We have used Distributed Tracing.


kafka at 9092 for broker and 9093 for controller

Zipkin is running on 8087
keycloak at 8086
Notification Service at 8089
Order Service at 8801



Keycloak:Command Prompt
cd C:\Users\a.ep.sharma\keycloak-26.5.1\bin
kc.bat start-dev
kc.bat start-dev --http-port=8806

ZipKin:
cd downloads\zipkin
java -jar zipkin.jar --server.port=8087


Daily startup steps (copy–paste friendly)
Step 1: Start CONTROLLER

Open PowerShell:

cd C:\kafka
$env:KAFKA_HEAP_OPTS="-Xms512M -Xmx512M"
.\bin\windows\kafka-server-start.bat .\config\controller.properties


Leave this window open
Don’t worry if logs keep printing — that’s normal.

Step 2: Start BROKER (new window)

Open another PowerShell window:

cd C:\kafka
$env:KAFKA_HEAP_OPTS="-Xms512M -Xmx512M"
.\bin\windows\kafka-server-start.bat .\config\broker.properties