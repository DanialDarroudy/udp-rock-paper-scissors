# UDP Rock–Paper–Scissors – Modular Client-Server Game

A scalable client-server Rock–Paper–Scissors game built in Java using the UDP protocol.  
Features a custom dependency injection (DI) container, microkernel modularity, and a PacketDispatcher for efficient client routing.  
Supports multiple clients, queuing them until paired for a two-player match.

---

## 🚀 Features
- **UDP protocol** for lightweight, connectionless communication  
- **Custom DI container** for dependency management  
- **Microkernel architecture** enabling modular extensions  
- **PacketDispatcher** to identify and route packets to the correct client  
- **Client & message queues** for pairing and concurrency management  
- **Two-player gameplay** with server handling many clients simultaneously  

---

## 🏗️ Architecture Overview
- **Server**
  - Listens for UDP packets  
  - Uses PacketDispatcher to route messages  
  - Manages client queue and pairs players for matches  
  - Applies DI container for modular lifecycle management  

- **Client**
  - Connects to server via UDP  
  - Sends chosen moves (Rock, Paper, Scissors)  
  - Receives results and game state updates  

---

## 🛠️ Technologies
- Java  
- UDP protocol  
- Custom DI framework  
- Microkernel modularity  
- Queues for client/message management  

