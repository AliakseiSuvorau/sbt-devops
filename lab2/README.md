# Guidelines

1. Preparing ansible
   * Creating ansible image with Dockerfile:
    ```bash
    docker build -t ansible .
    ```
   * Running a docker container with ansible
    ```bash
    docker run --name ansible_container -v /var/run/docker.sock:/var/run/docker.sock ansible
    ```
2. Deploying app
   * Inside ansible playbooks one needs to:
      * Pull maven jdk17 docker image (See `ansible/building_playbook.yaml`)
      * Run a docker container, copy source code inside it and compile it (See `ansible/building_playbook.yaml`)
      * Run app in another docker container (See `ansible/deploy_playbook.yaml`)