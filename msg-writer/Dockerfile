FROM reg.qiniu.com/diablowu-pub/centos-java-maven as builder

WORKDIR /app

ADD ./src/ /app/src/
ADD ./pom.xml /app/pom.xml


RUN mvn -U -e -B clean package -Dmaven.test.skip

FROM reg.qiniu.com//diablowu-pub/centos-java

WORKDIR /app
COPY --from=builder /app/target/msg-writer-0.0.1-SNAPSHOT.jar /app/app.jar

ENV SERVER_PORT=8080

ENTRYPOINT [ "java","-jar","/app/app.jar" ]

