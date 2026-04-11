# CLI Word Count Tool (jwc)

A high-performance command-line utility built in Java that replicates the functionality of the standard Unix `wc` tool. This project was developed with a focus on **efficient stream processing**, **memory management**, and **clean code architecture**.

## 🚀 Key Features

* **Multi-Metric Analysis**: Supports standard flags:
    * `-c`: Count bytes in a file.
    * `-l`: Count lines (newline characters).
    * `-w`: Count words (sequences separated by whitespace).
    * `-m`: Count characters with full **UTF-8 multibyte support**.
* **Performance Optimized**: Features a **single-pass processing algorithm** that calculates all metrics simultaneously to minimize disk I/O.
* **Memory Efficient**: Processes files of any size (even multi-gigabyte files) with a constant memory footprint (O(1) space complexity) using `BufferedInputStream`.
* **Modern Java Stack**: Leverages Java 21 features for clean and concise logic.

## 🏗️ Architecture

The project follows a **Layered Architecture** and **SOLID** principles:

* **Repository Layer**: Handles low-level file access using Java NIO.2.
* **Service Layer**: Contains the core logic for stream analysis and metric calculation.
* **Display Layer**: Manages CLI output formatting.
* **Dependency Injection**: Decoupled components for better testability and maintenance.

## 🛠️ Installation & Usage

### Prerequisites
* **JDK 21** or higher
* **Maven**

### Build
```bash
mvn clean package
Run
The tool can be executed via the JAR file:

Bash
# Get all metrics (lines, words, bytes, chars)
java -jar target/jwc.jar test.txt

# Get specific metric (e.g., word count)
java -jar target/jwc.jar -w test.txt