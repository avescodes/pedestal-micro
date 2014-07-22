FROM dockerfile/java
MAINTAINER Ryan Neufeld <ryan@rkn.io>

RUN sudo apt-get update
RUN sudo apt-get install -y supervisor

ADD deploy/supervisord.conf /etc/supervisor/conf.d/supervisord.conf

ADD target/{{name}}-0.0.1-SNAPSHOT-standalone.jar /srv/{{name}}.jar

EXPOSE 8080

CMD ["/usr/bin/supervisord"]
