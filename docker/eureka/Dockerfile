FROM tomcat:8.5

RUN apt-get update -y && apt-get install -y git && mkdir /app

WORKDIR /app
RUN git clone https://github.com/Netflix/eureka.git

WORKDIR /app/eureka
RUN git checkout 1.4.x && ./gradlew clean build

COPY /app/eureka/eureka-server/build/libs/eureka-server-*.war /usr/local/tomcat/webapps/eureka.war
