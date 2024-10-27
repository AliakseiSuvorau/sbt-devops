FROM alpine:3.11
RUN apk add --no-cache openssh-client ansible git sshpass docker-cli
COPY ./ansible /ansible

WORKDIR /ansible

CMD ["ansible-playbook", "building_playbook.yaml", "deploy_playbook.yaml"]