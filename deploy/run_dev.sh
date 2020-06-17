set -e
cd redex-backend
sudo killall instruments 2>/dev/null
git pull origin develop
nohup mvn spring-boot:run &