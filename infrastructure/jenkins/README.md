# Jenkins

These instructions are for setting up Jenkins and a build agent as docker containers.

### 1. Generate Keys

- Start cmd and run ``ssh-keygen -t rsa -f jenkins``
- Provide the password
- Two new files must be created in the same directory: _jenkins_ and _jenkins.pub_

### 2. Start Jenkins and Agent

- Create .env file and copy the content of env.template file into it;
- Replace \<USER_HOME\> with your user home directory;
- Replace \<AGENT_PUBKEY\> with the content of jenkins.pub file;
- Run ``docker-compose up -d``;
- Navigate to the Jenkins [start page](http://localhost:9090);
- Run ``docker logs jenkins`` and copy the password to unlock jenkins;
- Finish installation steps: install desired plugins, create admin user;
The plugins are:
  - Folders
  - Configuration as a Code
  - Build Timeout
  - Config File Provider
  - Credentials Binding
  - SSH Agent
  - Gradle
  - NodeJS
  - HTML Publisher
  - JUnit
  - Pipeline
  - GitHub Branch Source
  - Pipeline: GitHub Groovy Libraries
  - Pipeline. Stage View
  - Source Code Management (Git, GitHub, GitHub, etc.)
  - SSH Build Agents
  - Locale

### 3. Configure Credentials to Build Agent

- In the Jenkins UI navigate to _Manage Jenkins -> Manage Credentials_;
- Select _System_ under _Stores scoped to Jenkins_;
- Select _Global credentials (unrestricted)_
- Click _Add Credentials_:
    - Select SSH Username with private key.
    - Scope -> System
    - ID -> JenkinsAgent
    - Username -> jenkins
    - Private Key -> Enter directly -> Add
    - Paste the contents of jenkins file from the Section **Generate Keys** in the text box
    - Enter passphrase used to generate the keys

### 4. Configure Agent

- Navigate to _Manage Jenkins -> Manage Nodes and Clouds -> New Node_;
- Enter node name and select node type (Usually only _Permanent Agent_ is available);
    - Remote Root Directory -> /home/jenkins/agent
    - Usage -> Use this node as much as possible
    - Launch method -> Launch agents via SSH
    - Host -> agent
    - Credentials -> select jenkins, which were created during the previous step
    - Host Key Verification Strategy -> Non verifying Verification Strategy
    - Expand Advanced
    - In terminal execute the following command ``docker exec <container_id> which java`` where <container_id> is the id of agent container
    - Copy the result from terminal and paste it into _JavaPath_ field
    - Save
- The agent must start and its status can be obtained from the UI

### 5. Configure SSH to GitHub
- Start cmd and run ``ssh-keygen -t rsa -f github``
- Provide the password
- Two new files must be created in the same directory: _github_ and _github.pub_
- In GitHub under _account settings_ create new SSH key and paste the content of _github.pub_
- Go through **Configure Credentials** section again, use _github_ in this case.
  Make sure that the credentials id is _github_ as it is hardcoded in Jenkinsfile.
- In Jenkins go to _security settings_ and set disable host verification by choosing _No Verification_ option
