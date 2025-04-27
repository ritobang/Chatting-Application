# Chatting Application (Client-Server Based)

This project is a simple real-time chat application built using **Java**.  
It uses **Socket Programming** and **Swing** for the Graphical User Interface (GUI).

The application consists of two modules:
- **Server**
- **Client**

---

## Features

- Real-time text messaging between client and server.
- Attractive and modern GUI built using Swing.
- Message timestamps are shown.
- Automatic scrolling to the latest messages.
- "Online" status indicator.
- Close button for exiting the application.
- Simulated chat header with icons for video, call, and options.

---

## Requirements

- Java 8 or above
- Basic knowledge of Socket Programming and Java Swing
- Icons/images used in the GUI must be available in a folder named `icons/`

---

## How to Run

1. **Clone the repository** or download the source code.
2. **Compile the code** using the following commands:
   ```bash
   javac server.java
   javac client.java
3. **Run the code** using the following commands
   ```bash
   java server.java
   java client.java

---

## Notes

- Server must be started **before** starting the client.
- Both programs communicate over **localhost (`127.0.0.1`)** using **port `6001`**.
- **Only one client** is supported at a time (single client-server communication).
- Icons used in the header (profile picture, video call, phone call, and menu) should be placed inside an `icons/` folder.
- GUI automatically scrolls down to show the latest message.
- The "back" button on the header can be used to **exit** the application.

---

## Future Improvements

- Enable **multiple clients** to connect to the server simultaneously (multi-threading).
- Implement **file sharing** (images, documents, videos).
- Add **encryption** for secure message transmission.
- Integrate **emoji** and **sticker** support in the chat.
- Add **read receipts** and **typing indicators**.
- Improve the **UI/UX** with better themes and transitions.
- Introduce **chat history saving** (local database or file).
- Support for **group chats** and **private chats**.
