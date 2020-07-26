# shellcheck disable=SC2164
cd redex-backend
sudo killall java 2>/dev/null
git pull origin develop
nohup mvn spring-boot:run &> ../redex-log.out &
exit
