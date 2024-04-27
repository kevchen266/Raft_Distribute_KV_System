# Raft-Java Distribute Key Value System on Linux Implementation

This project is a Linux environment implementation, including complete setup and testing steps, 
as well as using Spring Boot to perform HTTP read/write tests.
Refer to the [raft-java](https://github.com/wenweihu86/raft-java).

## Features

- Deploy and test the Raft implementation on Linux systems.
- Use a Spring Boot application to test read and write operations via HTTP.

## Quick Start

### Prerequisites

- Linux operating system
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
### Open Source License
- This project is licensed under the Apache License 2.0. See the LICENSE file for more details.
### Acknowledgments
- Thanks to the original authors and contributors of the raft-java project.
-
### Notes

1. **Transparency and Respect for Original Work**: In the README, provide a link to the original project and appropriate acknowledgments, indicating that your project is based on it.
2. **License Information**: Ensure that your project includes a license file and mention it in the README. If your project uses code from the original project, you should retain the same license (Apache License 2.0).
3. **Clear Deployment and Usage Instructions**: Provide detailed deployment and testing steps to ensure that other users can easily utilize your code.

This README file will help users understand the purpose of your project, how to get started, and how to contribute.
