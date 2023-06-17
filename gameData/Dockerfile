FROM sbtscala/scala-sbt:eclipse-temurin-17.0.4_1.7.1_3.2.0

#ENV SCALA_VERSION 3.2.0
#ENV SBT_VERSION 1.7.1

WORKDIR /mydir
ADD . /mydir

EXPOSE 8080

CMD sbt run