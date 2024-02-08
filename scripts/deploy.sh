#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/bobmate
cd $REPOSITORY

APP_NAME=bobmate

APP_LOG=/home/ubuntu/bobmate/application.log
ERROR_LOG=/home/ubuntu/bobmate/application.log
DEPLOY_LOG=/home/ubuntu/bobmate/application.log

JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep 'SNAPSHOT.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 종료할 애플리케이션이 없습니다."
else
  echo "> kill -9 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 10
fi

echo "> Deploy - $JAR_PATH "
nohup java -jar -Duser.timezone=Asia/Seoul $JAR_PATH --logging.level.org.hibernate.SQL=DEBUG > $APP_LOG 2>$ERROR_LOG &
