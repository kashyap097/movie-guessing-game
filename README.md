
# 🎬 Bollywood Movie Guessing Game (Full-Stack Web App)

A classic, interactive, and nostalgic **"BOLLYWOOD" style Hangman game** built using the Spring Boot framework. Instead of a traditional boring layout, this application brings back childhood paper-game memories combined with popular Indian pop-culture references (KBC rewards and viral memes).

Live Demo: `https://movie-guessing-game-yni0.onrender.com/game` *(Replace with your actual live link)*

---

## ✨ Core Features

*   **🎰 Nostalgic "BOLLYWOOD" Life Engine:** Traditional 9-lives system. Every wrong guess strikes through a letter from the word "BOLLYWOOD" using real-time CSS/Thymeleaf synchronization.
*   **💡 Intelligent Vowel Hint System:** To provide an engaging start, all vowels (`A, E, I, O, U`) are automatically revealed and pre-filled into the movie blanks at the beginning of every new game.
*   **🎹 Smart Dynamic Keyboard:** Keys turn disabled and greyed out (`.used` CSS state) once chosen, preventing redundant inputs and improving UX.
*   **🎬 Pop-Culture Video Rewards (Local HTML5 Integration):**
    *   **On Win:** Celebrates victory by automatically playing Amitabh Bachchan’s iconic *"7 Crore!"* video clip with audio.
    *   **On Lose:** Lightens up the defeat with a funny viral meme video (*"Khatam, Tata, Bye-Bye"*).
*   **🧩 Robust Word-Space & Digit Handling:** Accurately separates multi-word titles with clean gaps and reveals numerical digits automatically (e.g., *KGF 2*).
*   **🔒 Multi-User Session Isolation:** Uses standard `HttpSession` management to ensure multiple global players can enjoy individual isolated game instances concurrently.

---

## 🛠️ Tech Stack & Architecture

*   **Backend:** Java 17, Spring Boot (MVC Framework), Spring Web
*   **Frontend Template Engine:** Thymeleaf
*   **Styling & UI:** Clean HTML5, Modern Responsive CSS3 (Flexbox/Grid Layouts)
*   **Containerization & Deployment:** Docker, Render Cloud Platform

---

## 📂 Project Architecture Overview

```text
src/main/java/com/moviegame
├── MovieGameApplication.java
├── controller/
│   └── GameController.java       # Handles HTTP requests, Sessions, and route rendering
└── model/
    └── GameState.java            # Primary core business logic and state validator
src/main/resources
├── static/
│   └── videos/                   # Contains local .mp4 audio/video reward clips
├── templates/
│   └── game.html                 # Thymeleaf dynamic views with semantic UI logic
└── application.properties
Dockerfile                        # Optimized multi-stage Docker build configuration
```

---

## ⚡ Local Setup Instructions

### Prerequisites
*   Java Development Kit (JDK 17 or higher)
*   Apache Maven installed (or use the included wrapper)

### Step-by-Step Execution
1. Clone or download this repository.
2. Place your downloaded reward videos (`7crore.mp4` and `lose.mp4`) inside the `src/main/resources/static/videos/` directory.
3. Open your terminal in the root directory and build the package:
   ```bash
   mvn clean package
   ```
4. Execute the Spring Boot application executable jar:
   ```bash
   java -jar target/movie-guessing-game-0.0.1-SNAPSHOT.jar
   ```
5. Open your web browser and navigate to:
   ```text
   http://localhost:8080/game
   ```

---

## ☁️ Deployment Note (Dockerized for Render)

This application is ready to deploy globally using the attached `Dockerfile`. It implements a robust multi-stage Docker build that abstracts dependencies seamlessly using a native Linux runtime environment:

```dockerfile
FROM maven:3.9.6-eclipse-temurin-17 AS build
...
RUN mvn clean package -DskipTests
...
ENTRYPOINT ["java", "-jar", "app.jar"]
```
*Note: Due to Render's spin-down policy on free tiers, if the application is inactive for 15+ minutes, the initial page load may take around 30-40 seconds to spin up from sleep mode.*
