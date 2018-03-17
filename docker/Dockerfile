FROM clojure

MAINTAINER Jim Clark <jim@atomist.com>

RUN mkdir -p /usr/src/app \
    && mkdir -p /usr/src/app/bin \
    && mkdir -p /usr/src/app/lib

WORKDIR /usr/src/app

COPY target/lib /usr/src/app/lib
COPY target/service.jar /usr/src/app/

CMD ["-Xmx256m", "-jar", "/usr/src/app/service.jar"]

EXPOSE 3000
