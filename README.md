# 🔐 Xenosecure

Xenosecure is a professional-grade Java-based file encryption tool designed to protect your files using cryptography, multithreading, security, and software architecture.

## ✨ Features

- AES and RSA encryption
- Hybrid encryption mode (AES + RSA)
- File and folder encryption
- File signing and verification (digital signature)
- Secure shredding and cleanup
- CLI with argument parser
- Multithreaded folder encryption
- Gzip compression before encryption
- Encrypted configuration storage
- Key export/import
- Full JUnit test suite
- Docker and Maven support

## 🧱 Tech Stack

- Java 17+
- Maven
- JUnit 5
- Docker

## 🚀 Getting Started

```bash
git clone https://github.com/goodwill-sakhile/xenosecure.git
cd xenosecure
mvn clean install
java -jar target/securecryptor.jar --encrypt myfile.txt
