# Raft-Java Distribute Key Value System on Linux Implementation

## Features
- This project deploy a Java-based Raft consensus algorithm distributed system on Linux environment, incorporating leader election, log replication, and snapshot update functionalities:
- Utilized RocksDB for persistent storage in the Java-based system, ensuring robustness and fault tolerance in the architecture.
- Designed and optimized a Java-based Key-Value storage interface supporting linearly consistent read and write operations, enhancing overall system performance through asynchronous Apply, ReadIndex, FollowerRead, Prevote, etc.
- Created a Spring Boot project that serves as the server side to handle HTTP read and write requests from users, and as the client side to access the Raft project server side via RPC on the Linux server.

##System Workflow
<img width="600" alt="圖片1" src="https://github.com/kevchen266/Raft_Distribute_KV_System/assets/72414426/6c6a9d6c-d20e-462c-bcd8-b08abe6275f9">


## Quick Start

### Prerequisites

- Linux Centos operating system
- Java JDK 1.8 or higher
- Maven 3.6 or higher

### Deployment Guide

1. Clone this repository to your local machine:
```bash
git clone https://github.com/yourGitHubusername/your-repository-name.git
cd your-repository-name
cd raft-java-example
mvn clean install
sh deploy.sh
```

### Testing Operation
- Write
```bash
cd env/client
./bin/run_client.sh "list://127.0.0.1:8051,127.0.0.1:8052,127.0.0.1:8053" hello world
```
- Read
```bash
./bin/run_client.sh "list://127.0.0.1:8051,127.0.0.1:8052,127.0.0.1:8053" hello
```

### Spring Boot HTTP Testing
- Start Spring boot app
```bash
cd spring-boot-application
mvn spring-boot:run
```
- Use Postman or any HTTP client to perform read and write test:
```bash
POST /raft/write?key=your_key&value=your_value
GET /raft/read?key=your_key
```
![HTTPд����](https://github.com/kevchen266/Raft_Distribute_KV_System/assets/72414426/29c1852a-2181-4a7f-b8f1-d335a4581669)
![HTTP������](https://github.com/kevchen266/Raft_Distribute_KV_System/assets/72414426/5de2c416-e3b0-43f7-898c-5457aef1f420)


### Open Source License
- This project is licensed under the Apache License 2.0. See the LICENSE file for more details.
### Acknowledgments
- Thanks to the original authors and contributors of the raft-java project.
-
### Notes
Refer to the [raft-java](https://github.com/wenweihu86/raft-java).
1. **Transparency and Respect for Original Work**: In the README, provide a link to the original project and appropriate acknowledgments, indicating that your project is based on it.
2. **License Information**: Ensure that your project includes a license file and mention it in the README. If your project uses code from the original project, you should retain the same license (Apache License 2.0).
3. **Clear Deployment and Usage Instructions**: Provide detailed deployment and testing steps to ensure that other users can easily utilize your code.

This README file will help users understand the purpose of your project, how to get started, and how to contribute.
