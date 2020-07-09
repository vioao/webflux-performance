SPRING MVC VS. SPRING WEBFLUX

![env](static/flux-vs-mvc.png)

1. build service image
   - under folder flux: `docker build -t vioao/flux .`
   - under folder mvc: `docker build -t vioao/mvc .`
   - under folder web: `docker build -t vioao/web .`

2. run the service with limited cou and memory
   - run service web: `docker run --cpus=0.2 --memory=1g -p 8080:8080 -p 8081:8081 JAVA_OPTS="-Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote.port=8081 -Dcom.sun.management.jmxremote.rmi.port=8081" vioao/web`
   - get the IPAddress of service web： `docker inspect ${service web's ContainerId}`
   - run service flux： `docker run --cpus=0.2 --memory=1g -p 8090:8090 -p 8091:8091 -e JAVA_OPTS=-Durl="http://${IPAddress}:8080 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote.port=8091 -Dcom.sun.management.jmxremote.rmi.port=8091" vioao/flux`
   - run service mvc： `docker run --cpus=0.2 --memory=1g -p 9090:9090 -p 9091:9091 -e JAVA_OPTS="-Durl=http://${IPAddress}:8080 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote.port=9091 -Dcom.sun.management.jmxremote.rmi.port=9091" vioao/mvc`
   
3. begin performance test with jmeter, just import the configuration file under folder jmeter and configure the thread group by yourself.