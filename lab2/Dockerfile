FROM alpine:3.11
RUN apk add --no-cache openssh-client ansible git sshpass
RUN mkdir -p /root/.ssh
RUN echo "Host *" > /root/.ssh/config && echo " StrictHostKeyChecking no" >> /root/.ssh/config
COPY ./ansible /ansible

ENTRYPOINT ["ansible-playbook", "playbook_dir/playbook.yaml", "-i", "playbook_dir/inventory.yaml"]
