# Networks Lab

This repository contains various network programming experiments implemented in Java. Each experiment focuses on a specific networking concept, such as TCP/UDP protocols, concurrent servers, and client-server communication models.

## Experiments Overview

### 1. [EXP5: Client-Server Communication Using TCP](./EXP5-Client_Server_Communication_Using_TCP/)
**Description:** Demonstrates reliable, connection-oriented communication between a client and a server using Transmission Control Protocol (TCP). The client sends data to the server, and the server processes it and sends a response back.

### 2. [EXP6: Client-Server Communication Using UDP](./EXP6-Client_Server_Communication_Using_UDP/)
**Description:** Demonstrates connectionless communication using User Datagram Protocol (UDP). The client sends sentences with abbreviations, and the server translates them into full text and sends them back to the client.

### 3. [EXP7: MultiUser Chat TCP](./EXP7-MultiUser_Chat_TCP/)
**Description:** Implements a multithreaded chat room where multiple clients can connect to a central TCP server and exchange messages with one another simultaneously.

### 4. [EXP8: Concurrent Time Server UDP](./EXP8-Concurrent_Time_Server_UDP/)
**Description:** A concurrent time server built using UDP. Clients send requests to the server, and the server responds to multiple clients concurrently with the current system date and time.

### 5. [EXP9: Concurrent File Server](./EXP9-Concurrent_File_Server/)
**Description:** A multithreaded file server over TCP. The client requests a file by providing its path/name. The server handles multiple client requests simultaneously, reads the requested file, and transmits its contents back to the client.

### 6. [EXP10: Packet Capturing](./EXP10-Packet_Capturing/)
**Description:** Focuses on network packet capturing and analysis (likely using tools or libraries like Wireshark/Pcap) to inspect the structure and payload of network packets.

## How to Run

1. Navigate to the specific experiment's directory.
2. Compile the Java files using `javac *.java`.
3. Start the server first using `java <ServerClassName>`.
4. Open a new terminal and run the client using `java <ClientClassName>`.