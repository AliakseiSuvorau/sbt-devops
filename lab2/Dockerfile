FROM ubuntu:22.04

RUN apt-get update && apt-get install -y openssh-client ansible openjdk-17-jdk

RUN mkdir -p /root/.ssh
RUN echo "Host *" > /root/.ssh/config && echo " StrictHostKeyChecking no" >> /root/.ssh/config
COPY ./ansible /ansible

ENTRYPOINT ["ansible-playbook", "playbook_dir/playbook.yaml", "-i", "playbook_dir/inventory.yaml"]
