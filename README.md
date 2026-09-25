# Networks Lab

This repository contains various network programming experiments implemented in Java. Each experiment focuses on a specific networking concept, such as TCP/UDP protocols, concurrent servers, and client-server communication models.

## Experiments Overview

---

### EXP1: Study of Linux Network Commands

**Aim:** To study and use essential Linux networking commands for network configuration, monitoring, diagnostics, and analysis.

---

#### 1. `ifconfig`
**Aim:** To display and configure network interface parameters in Linux.

**Theory:**
`ifconfig` (Interface Configuration) is a classic Linux command for configuring, managing, and querying network interface parameters. It displays information such as IP address, MAC address, MTU, and packet statistics for each network interface (`eth0`, `wlan0`, `lo`, etc.).

**Key Commands:**
```bash
ifconfig                      # Show all active interfaces
ifconfig -a                   # Show all interfaces (including inactive)
ifconfig eth0                 # Show specific interface
ifconfig eth0 up              # Enable interface
ifconfig eth0 down            # Disable interface
ifconfig eth0 192.168.1.100   # Assign IP address
```

---

#### 2. `ifplugstatus`
**Aim:** To detect whether a network cable is plugged into a network interface.

**Theory:**
`ifplugstatus` checks the physical link status of a network interface. It reports whether an Ethernet cable is connected or disconnected. It is useful for quickly verifying the physical layer connectivity of a network adapter without using `ifconfig` or `ip`.

**Key Commands:**
```bash
ifplugstatus             # Check all interfaces
ifplugstatus eth0        # Check a specific interface
ifplugstatus -v          # Verbose output
```

---

#### 3. `iftop`
**Aim:** To monitor real-time network bandwidth usage on a network interface.

**Theory:**
`iftop` is a real-time network bandwidth monitoring tool. It displays a frequently updated list of network usage, showing which connections are consuming the most bandwidth. It operates similarly to the `top` command but for network traffic, displaying source/destination pairs and their data rates.

**Key Commands:**
```bash
sudo iftop                    # Monitor default interface
sudo iftop -i eth0            # Monitor specific interface
sudo iftop -n                 # Disable hostname resolution
sudo iftop -N                 # Show port numbers, not service names
sudo iftop -B                 # Display bandwidth in bytes
```
> **Installation:** `sudo apt install iftop`

---

#### 4. `ping`
**Aim:** To test network connectivity between systems using the `ping` command.

**Theory:**
The `ping` command tests whether a remote host is reachable and measures the round-trip time (RTT) of packets. It works using **ICMP (Internet Control Message Protocol)** by sending Echo Request packets and listening for Echo Reply responses. If replies are received, the host is reachable; otherwise, there is a network issue.

**Key Commands:**
```bash
ping google.com          # Ping a domain name
ping 8.8.8.8             # Ping an IP address
ping -c 4 google.com     # Send only 4 packets
ping -i 2 google.com     # Set 2-second interval between packets
ping -s 100 google.com   # Send packets of 100 bytes
ping -t 64 google.com    # Set Time To Live (TTL) to 64
```

---

#### 5. `ip`
**Aim:** To configure and manage network interfaces, routing, and addresses using the `ip` command.

**Theory:**
The `ip` command is the modern replacement for `ifconfig` and `route`. It is part of the `iproute2` package and provides a comprehensive interface for managing network devices, IP addresses, routing tables, and tunnels. It is more powerful and versatile than `ifconfig`.

**Key Commands:**
```bash
ip addr show             # Show all IP addresses
ip addr show eth0        # Show address of a specific interface
ip link show             # Show network interfaces
ip link set eth0 up      # Bring interface up
ip link set eth0 down    # Bring interface down
ip route show            # Display routing table
ip route add default via 192.168.1.1   # Add default gateway
```

---

#### 6. `traceroute`
**Aim:** To trace the path taken by packets from source to a destination host.

**Theory:**
`traceroute` maps the journey of a packet from the local machine to a destination by identifying each intermediate hop (router). It sends packets with incrementally increasing TTL (Time To Live) values. Each router decrements the TTL; when it reaches 0, the router sends back an ICMP "Time Exceeded" message, revealing its identity. This technique exposes the full network path.

**Key Commands:**
```bash
traceroute google.com         # Trace path to a domain
traceroute 8.8.8.8            # Trace path to an IP
traceroute -n google.com      # Disable hostname resolution
traceroute -m 15 google.com   # Limit to 15 hops
traceroute -w 2 google.com    # Set 2-second timeout per hop
```

---

#### 7. `mtr`
**Aim:** To analyze network connectivity and performance by combining `ping` and `traceroute`.

**Theory:**
`mtr` (My Traceroute) is an advanced network diagnostic tool that combines the functionality of `ping` (latency measurement) and `traceroute` (path discovery). Unlike `traceroute`, which runs once, `mtr` continuously sends packets and provides real-time dynamic updates including packet loss percentage and round-trip time statistics at each hop.

**Key Commands:**
```bash
mtr google.com           # Interactive real-time mode
mtr -r google.com        # Report mode (non-interactive)
mtr -n google.com        # Do not resolve hostnames
mtr -c 10 google.com     # Send 10 packets
mtr -w google.com        # Wide report format
mtr -u google.com        # Use UDP instead of ICMP
```
> **Installation:** `sudo apt install mtr`

---

#### 8. `netstat`
**Aim:** To study and analyze network connections, routing tables, and interface statistics using `netstat`.

**Theory:**
`netstat` (Network Statistics) displays information about network connections, routing tables, interface statistics, masquerade connections, and multicast memberships. It is a fundamental tool for network diagnostics and security monitoring, showing which ports are open and which processes are connected to the network.

**Key Commands:**
```bash
netstat -a               # Show all connections and listening ports
netstat -t               # Show TCP connections only
netstat -u               # Show UDP connections only
netstat -l               # Show only listening ports
netstat -n               # Show numeric addresses (no DNS)
netstat -r               # Display routing table
netstat -s               # Show statistics per protocol
netstat -p               # Show PID and program name
```

---

#### 9. `whois`
**Aim:** To retrieve domain registration and ownership information using `whois`.

**Theory:**
The `whois` command queries public WHOIS databases maintained by domain registrars to fetch registration details about domain names and IP addresses. It communicates using the WHOIS protocol over **TCP port 43** and can reveal the domain owner, registrar, name servers, and registration/expiry dates.

**Key Commands:**
```bash
whois google.com         # Query a domain name
whois 8.8.8.8            # Query an IP address
whois example.com > info.txt  # Save output to a file
```

---

#### 10. `nmap`
**Aim:** To explore and analyze network hosts and services using `nmap`.

**Theory:**
`nmap` (Network Mapper) is a powerful open-source tool for network discovery and security auditing. It sends specially crafted packets to target systems and analyzes responses to discover active hosts, open/closed/filtered ports, running services, and even the operating system. It is widely used by network administrators and security professionals.

**Key Commands:**
```bash
nmap 192.168.1.1              # Scan a single host
nmap 192.168.1.0/24           # Scan an entire subnet
nmap -sS 192.168.1.1          # TCP SYN (stealth) scan
nmap -sU 192.168.1.1          # UDP scan
nmap -p 80,443 google.com     # Scan specific ports
nmap -A google.com            # Aggressive scan (OS + version detection)
nmap -O 192.168.1.1           # OS detection
nmap -F 192.168.1.1           # Fast scan (common ports only)
```
> **Installation:** `sudo apt install nmap`

---

#### 11. `nmcli`
**Aim:** To configure and manage network connections using `nmcli` in Linux.

**Theory:**
`nmcli` (Network Manager Command Line Interface) is used to control the NetworkManager service from the terminal. It manages network connections (wired, Wi-Fi, VPN), IP addresses, DNS servers, and gateways. It is a modern and powerful alternative to `ifconfig` and `ip`, working with both devices (hardware interfaces) and connections (saved network profiles).

**Key Commands:**
```bash
nmcli general status          # Check NetworkManager status
nmcli device status           # List all network devices
nmcli connection show         # Show all saved connections
nmcli device show             # Display detailed device info
nmcli device wifi list        # Scan for Wi-Fi networks
nmcli device wifi connect "SSID" password "password"  # Connect to Wi-Fi
nmcli connection down "profile"  # Disconnect a connection
```

---

#### 12. `speedtest-cli`
**Aim:** To measure internet bandwidth (download and upload speed) using `speedtest-cli`.

**Theory:**
`speedtest-cli` is a command-line tool that measures internet connection speed by connecting to the nearest Speedtest.net server and measuring download speed, upload speed, and ping/latency. It is useful for verifying ISP performance, diagnosing slow connectivity, and benchmarking network links.

**Key Commands:**
```bash
speedtest-cli                  # Run full speed test
speedtest-cli --simple         # Show only ping, download, upload
speedtest-cli --list           # List available test servers
speedtest-cli --server 1234    # Use a specific server ID
speedtest-cli --bytes          # Display results in bytes/sec
```
> **Installation:** `sudo apt install speedtest-cli` or `pip install speedtest-cli`

---

#### 13. `nslookup`
**Aim:** To query DNS servers and obtain domain name or IP address mapping using `nslookup`.

**Theory:**
`nslookup` (Name Server Lookup) is a network utility for querying DNS (Domain Name System) servers to retrieve domain-to-IP mappings and vice versa. It can be used to find the IP address of a hostname, the hostname for an IP address, and to query specific DNS record types like A, MX, NS, and CNAME records.

**Key Commands:**
```bash
nslookup google.com              # Find IP for a domain
nslookup 8.8.8.8                 # Reverse lookup: IP to hostname
nslookup -type=MX google.com     # Query Mail Exchange records
nslookup -type=NS google.com     # Query Name Server records
nslookup -type=A google.com      # Query A (IPv4 address) records
nslookup google.com 8.8.8.8      # Use a specific DNS server (8.8.8.8)
```

---

#### 14. `tcpdump`
**Aim:** To capture and analyze network packets using `tcpdump`.

**Theory:**
`tcpdump` is a command-line packet analyzer (sniffer) that captures network packets in real time and displays detailed information about each packet. It operates at a low level, capturing TCP, UDP, ICMP, and other traffic to/from specific hosts or ports. It requires root privileges and is widely used for network troubleshooting, security analysis, and debugging.

**Key Commands:**
```bash
sudo tcpdump                          # Capture all packets on default interface
sudo tcpdump -i eth0                  # Capture on a specific interface
sudo tcpdump -c 10                    # Capture only 10 packets
sudo tcpdump host 192.168.1.1         # Filter by host
sudo tcpdump port 80                  # Filter by port
sudo tcpdump -w capture.pcap          # Save packets to a file
sudo tcpdump -r capture.pcap          # Read from a saved file
sudo tcpdump -n port 443              # Capture HTTPS without DNS resolution
```
> **Installation:** `sudo apt install tcpdump`

---

### 2. [EXP5: Client-Server Communication Using TCP](./EXP5-Client_Server_Communication_Using_TCP/)

**Description:** Demonstrates reliable, connection-oriented communication between a client and a server using Transmission Control Protocol (TCP). The client sends a randomly generated square matrix to the server, and the server determines its type (upper triangular, lower triangular, or diagonal) and sends the result back to the client.

**Aim:**
To implement a TCP client-server program where the client sends a randomly generated square matrix to the server, and the server determines its type (upper triangular, lower triangular, or diagonal).

**Theory:**
*   **TCP Communication:** Provides reliable communication, ordered data transfer, and error detection/retransmission.
*   **Matrix Types:**
    *   **Upper Triangular Matrix:** All elements below the diagonal are zero ($i > j \Rightarrow A[i][j] = 0$).
    *   **Lower Triangular Matrix:** All elements above the diagonal are zero ($i < j \Rightarrow A[i][j] = 0$).
    *   **Diagonal Matrix:** All non-diagonal elements are zero ($i \neq j \Rightarrow A[i][j] = 0$).

**Algorithm:**
*   **Client Side:**
    1. Input integer `N` for the matrix order.
    2. Generate an `N x N` matrix with random values.
    3. Display the generated matrix.
    4. Send `N` to the server.
    5. Send the matrix elements to the server.
    6. Receive the matrix type result from the server.
    7. Display the result.
*   **Server Side:**
    1. Create a server socket.
    2. Bind the socket and listen for incoming connections.
    3. Accept the client connection.
    4. Receive the order `N`.
    5. Receive the matrix.
    6. Check the matrix type (upper, lower, diagonal, or none).
    7. Send the result back to the client.
    8. Close the connection.

### 2. [EXP6: Client-Server Communication Using UDP](./EXP6-Client_Server_Communication_Using_UDP/)
**Description:** Demonstrates connectionless communication using User Datagram Protocol (UDP). The client sends sentences with abbreviations, and the server translates them into full text and sends them back to the client.

**Aim:**
To design and implement a UDP client-server program where the client sends a sentence with abbreviations, and the server translates it into formal English.

**Theory:**
*   **UDP Communication:** User Datagram Protocol (UDP) is a connectionless protocol, providing faster communication without the overhead of connection establishment.
*   **Translation Process:** The client sends a string containing abbreviations (like "tbh", "ig", "lol"). The server uses a predefined dictionary to replace these abbreviations with their full forms and sends the translated sentence back.

**Algorithm:**
*   **Client Side:**
    1. Create a UDP socket.
    2. Input a sentence from the user containing abbreviations.
    3. Send the sentence to the server using `sendto()`.
    4. Receive the translated sentence using `recvfrom()`.
    5. Display the final translated result.
*   **Server Side:**
    1. Create a UDP socket.
    2. Bind the socket to a port.
    3. Receive the sentence from the client.
    4. Parse the sentence and replace abbreviations with full forms based on a dictionary.
    5. Send the translated sentence back to the client.
### 3. [EXP7: MultiUser Chat TCP](./EXP7-MultiUser_Chat_TCP/)
**Description:** Implements a multithreaded chat room where multiple clients can connect to a central TCP server and exchange messages with one another simultaneously.

**Aim:**
To implement a multi-user chat server using TCP socket programming, where multiple clients communicate simultaneously through a central server using the `select()` system call.

**Theory:**
*   **Multi-user Chat:** A centralized application where multiple clients establish TCP connections. When a client sends a message, the server broadcasts it to all other connected clients.
*   **I/O Multiplexing (`select()`):** Unlike simple servers blocked on a single `recv()`, `select()` allows the server to monitor multiple sockets simultaneously and serve only the ones that are ready (have data to read), avoiding blockages.

**Algorithm:**
*   **Client Side:**
    1. Create a TCP socket and connect to the server.
    2. Create two execution paths (e.g., using threads or `fork()`): one for reading user input (Sender) and one for receiving broadcast messages (Receiver).
    3. Continuously send/receive messages until terminated.
*   **Server Side:**
    1. Create a TCP socket, bind, and listen for incoming connections.
    2. Initialize the master file descriptor set and add the server socket to it.
    3. Continuously loop and call `select()` to monitor sockets.
    4. If the server socket is ready, accept the new client connection and add it to the descriptor set.
    5. If a client socket is ready, receive the message and broadcast it to all other connected clients.

### 4. [EXP8: Concurrent Time Server UDP](./EXP8-Concurrent_Time_Server_UDP/)
**Description:** A concurrent time server built using UDP. Clients send requests to the server, and the server responds to multiple clients concurrently with the current system date and time.

**Aim:**
To implement a concurrent Time Server application using UDP socket programming, where the client sends a time request, and the server retrieves its current system time and sends it back to the client.

**Theory:**
*   **Time Server:** An application providing the current date and time to clients. It reads the system time using functions like `time()` and `ctime()`.
*   **Concurrency in UDP:** Because UDP is connectionless, the server processes each packet independently. It doesn't maintain client states, allowing it to natively handle multiple clients sending requests simultaneously by responding to each individually.

**Algorithm:**
*   **Client Side:**
    1. Create a UDP socket.
    2. Send a dummy time request to the server using `sendto()`.
    3. Wait to receive the formatted time response using `recvfrom()`.
    4. Display the received time.
*   **Server Side:**
    1. Create a UDP socket and bind it to a port.
    2. Wait for incoming client requests using `recvfrom()`.
    3. Upon receiving a request, retrieve the current system time (`time()`).
    4. Convert the time to a human-readable string (`ctime()`).
    5. Send the time string back to the requesting client using `sendto()`.
    6. Loop back to wait for the next request.

### 5. [EXP9: Concurrent File Server](./EXP9-Concurrent_File_Server/)
**Description:** A multithreaded file server over TCP. The client requests a file by providing its path/name. The server handles multiple client requests simultaneously, reads the requested file, and transmits its contents back to the client.

**Aim:**
To implement a concurrent file server using UDP socket programming, where multiple clients can request files simultaneously, and the server sends the contents (or an error) along with its Process ID (PID).

**Theory:**
*   **File Transfer:** The client requests a file, and the server searches for it. If found, it reads the contents and sends them; otherwise, it sends an error.
*   **Concurrent Execution (`fork()`):** Although UDP is connectionless, a concurrent file server can be implemented by creating a child process (`fork()`) for every incoming datagram request. The child process handles the file transfer and sends its `getpid()` to the client, while the parent continues listening for new requests.

**Algorithm:**
*   **Client Side:**
    1. Create a UDP socket.
    2. Prompt the user for the desired filename.
    3. Send the filename to the server.
    4. Receive the file contents (or error message) and the server's PID.
    5. Display the received data.
*   **Server Side:**
    1. Create a UDP socket and bind it to a port.
    2. Receive a filename request from a client.
    3. Create a child process using `fork()`.
    4. In the child process: open the requested file.
    5. If it exists, read contents, attach the PID, and send it to the client. If not, send a "File Not Found" error with the PID.
    6. In the parent process: immediately return to listening for new requests.

### 6. [EXP10: Packet Capturing](./EXP10-Packet_Capturing/)
**Description:** Focuses on network packet capturing and analysis (likely using tools or libraries like Wireshark/Pcap) to inspect the structure and payload of network packets.

**Aim:**
To develop a packet capturing application using raw sockets in Linux that captures network packets and displays Ethernet, IP, TCP, UDP, and ICMP header information.

**Theory:**
*   **Raw Sockets (`SOCK_RAW`):** Typically, applications use TCP or UDP sockets which process data before passing it up. A Raw Socket receives packets directly from the network interface before the OS stack processes them, requiring root privileges.
*   **Packet Sniffing:** Allows capturing traffic to inspect the Ethernet frame, extract source/destination MAC and IP addresses, and decode transport layer headers (TCP/UDP/ICMP).

**Algorithm:**
*   **Application Flow:**
    1. Open a raw socket with `socket(AF_PACKET, SOCK_RAW, htons(ETH_P_ALL))` (requires root access).
    2. Continuously capture incoming packets in a loop.
    3. Extract the Ethernet header to read source and destination MAC addresses.
    4. Identify the network layer protocol. If it's IPv4, extract the IP header to get source and destination IP addresses.
    5. Check the protocol field in the IP header (e.g., TCP, UDP, ICMP).
    6. Extract port numbers and relevant details based on the transport protocol.
    7. Display the parsed packet information to the console.

## How to Run

1. Navigate to the specific experiment's directory.
2. Compile the Java files using `javac *.java`.
3. Start the server first using `java <ServerClassName>`.
4. Open a new terminal and run the client using `java <ClientClassName>`.