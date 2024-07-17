FROM jenkins/ssh-agent:alpine-jdk17

USER root

RUN apk add --no-cache docker
RUN addgroup jenkins docker

USER jenkins