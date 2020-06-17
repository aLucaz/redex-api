set -e
cd redex-backend
killall -9 java
git pull origin develop
nohub mvn spring-boot:run &