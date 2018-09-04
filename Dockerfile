FROM frolvlad/alpine-oraclejdk8
#指定维护者信息
MAINTAINER zzf
VOLUME /tmp
ADD target/newblog-0.0.1-SNAPSHOT.jar blog.jar
RUN sh -c 'touch /blog.jar'
ENV JAVA_OPTS=""
EXPOSE 9091
#指定执行启动spring boot小项目     ENTRYPOINT 为容器启动后执行的命令
ENTRYPOINT ["java","-jar","/blog.jar"]