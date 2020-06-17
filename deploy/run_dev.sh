set -e
cd redex-backend
killall -9 java
git pull origin develop
nohup mvn spring-boot:run &