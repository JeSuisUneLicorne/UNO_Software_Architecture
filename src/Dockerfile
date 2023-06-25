FROM sbtscala/scala-sbt:eclipse-temurin-17.0.4_1.7.1_3.2.0

RUN apt-get update && apt-get install -y libxrender1 libxtst6 libxi6

ENV DISPLAY: $DISPLAY

WORKDIR /mydir
COPY . /mydir

EXPOSE 8080

CMD sbt run