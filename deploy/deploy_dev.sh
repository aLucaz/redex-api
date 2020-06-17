set -e
# shellcheck disable=SC2046
eval $(ssh-agent -s)
echo "$PRIVATE_KEY" | tr -d '\r' | ssh-add - > /dev/null

echo "DEPLOYING DEVELOP BRANCH INTO ${SERVER}"
ssh ubuntu@"${SERVER}" 'bash -s' < ./deploy/run_dev.sh