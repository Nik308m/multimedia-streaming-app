# 📺 NikTube 🎵

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen?logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Active-blue?logo=postgresql)
![JSP](https://img.shields.io/badge/View%20Layer-JSP-red)
![License](https://img.shields.io/badge/License-MIT-yellow)

**NikTube** is a full-stack, YouTube-inspired media platform built with **Java 17** and **Spring Boot**, where users can upload, list, stream, and download both **video** and **music** files — stored directly as binary data in a **PostgreSQL** database rather than the filesystem. 🚀

The server-rendered UI is built with **JSP** (Java Server Pages) via Spring MVC, backed by JPA/Hibernate for persistence. It supports uploads of up to **3GB per file**, making it capable of handling real video content, not just toy files. 🎬

---

## ✨ Features

- 📤 Upload video files with a name and description
- 🎵 Upload music files with a name and description
- 🔍 Look up and stream media by name
- ⬇️ Download videos and music directly from the database
- 📄 Browse a full list of all uploaded videos or music tracks
- 🗄️ Media stored as binary (BLOB) data in PostgreSQL via JPA/Hibernate
- 🔄 MySQL and H2 configuration also included for flexibility

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.2.1 |
| Persistence | Spring Data JPA + Hibernate |
| Database | PostgreSQL (MySQL / H2 also supported) |
| View Layer | JSP (Java Server Pages) via `tomcat-jasper` |
| Build Tool | Maven |

---

## 🛠️ Installation

1. ☕ Install **Java 17**
2. 🐘 Install **PostgreSQL** and create a database named `NikTube`
3. ⬇️ Clone the repository:
   ```bash
   git clone https://github.com/Nik308m/NikTube.git
   cd NikTube
   ```
4. 🔐 Set your database credentials as environment variables rather than hardcoding them:
   ```bash
   export DB_PASSWORD=your_password_here
   ```
5. 🖥️ Open the project in **IntelliJ IDEA** (preferred), Eclipse, or NetBeans
6. 📥 Let your IDE resolve all Maven dependencies
7. ▶️ Run the application (or `mvn spring-boot:run`)
8. 🌐 Open your browser and navigate to:
   ```
   http://localhost:8080/home
   ```

> 💡 **Troubleshooting tip:** Watch the console output for database connection errors — most setup issues come from PostgreSQL not running or credentials not matching.

---

## 📝 Usage

- 📂 Visit `/uploadvideo` or `/uploadmusic` to upload media files
- 📄 Visit `/ListVideos` or `/ListMusics` to browse everything that's been uploaded
- ▶️ Visit `/streamvideo` or `/streammusic` to stream a selected file

### REST endpoints

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/videopage/upload` | Upload a video file (`videoFile`, `vname`, `vdescription`) |
| `POST` | `/musicpage/upload` | Upload a music file (`musicFile`, `mname`, `mdescription`) |
| `GET` | `/videopage/video/{name}` | Download/stream a video by name |
| `GET` | `/musicpage/music/{name}` | Download/stream a music file by name |
| `GET` | `/videopage/ListOfVideos` | Get JSON list of all videos |
| `GET` | `/musicpage/ListOfMusics` | Get JSON list of all music tracks |

---

## 🗺️ Roadmap

- [ ] Move database credentials to environment variables (in progress)
- [ ] Add authentication for uploads
- [ ] Add pagination for large media lists
- [ ] Add automated tests
- [ ] Migrate frontend from JSP to a modern SPA or Thymeleaf

---

## 🤝 Contributing

Contributions to NikTube are welcome!

- 🐛 Report bugs or issues by opening a GitHub issue
- 🛠️ Submit pull requests with proposed changes or enhancements

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

## 🙏 Acknowledgments

Special thanks to:

- **Omkar Jejurkar** 🙌 — for invaluable help setting up the database layer and resolving common connectivity issues during development.

---

## 📧 Contact

Built by **Nikhil**. Questions or feedback are welcome:

- 📧 Email: *nik.308m@gmail.com*
- 💼 LinkedIn: *https://www.linkedin.com/in/nikhil-patil-308k/*
- 🐙 GitHub: *https://github.com/Nik308m*

