# Guidelines

[//]: # (1. Preparing ansible)

[//]: # (   * Creating ansible image with Dockerfile:)

[//]: # (    ```bash)

[//]: # (    docker build -t ansible .)

[//]: # (    ```)

[//]: # (   * Running a docker container with ansible)

[//]: # (    ```bash)

[//]: # (    docker run --name ansible_container -v /var/run/docker.sock:/var/run/docker.sock ansible)

[//]: # (    ```)

[//]: # (2. Deploying app)

[//]: # (   * Inside ansible playbooks one needs to:)

[//]: # (      * Pull maven jdk17 docker image &#40;See `ansible/building_playbook.yaml`&#41;)

[//]: # (      * Run a docker container, copy source code inside it and compile it &#40;See `ansible/building_playbook.yaml`&#41;)

[//]: # (      * Run app in another docker container &#40;See `ansible/deploy_playbook.yaml`&#41;)

1. Running all the containers:
    ```bash
    docker-compose up 
    ```
2. Viewing docker networks list: `docker network ls`:
    ```
    NETWORK ID     NAME           DRIVER    SCOPE
   fe4b6132df4d   bridge         bridge    local
   f4861783b031   host           host      local
   7a1a5a0247d5   lab2_default   bridge    local
   f01d862afffd   none           null      local
    ```
3. Inspecting the necessary network and grepping its ip-address: `docker inspect lab2_default | grep IPv4Address`:
    ```
    "IPv4Address": "172.18.0.3/16",
    ```
4. Hardcoding it (without the port) into `ansible/inventory.yaml` in `all.hosts.deploy.ansible_host` and restarting the container with ansible