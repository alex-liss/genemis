FROM jenkins/jenkins:lts-alpine-jdk17

USER root

RUN apk -U add docker