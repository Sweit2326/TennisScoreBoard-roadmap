## 🎾 Табло теннисного матча

Веб-приложение для подсчёта очков в теннисном матче с веб-интерфейсом на Java (Servlets, JSP, Hibernate, H2). Проект выполнен в рамках [Java Backend Roadmap](https://zhukovsd.github.io/java-backend-learning-course/) Сергея Жукова.

### ⚡ Функционал приложения
- **Создание нового матча** – ввод имён двух уникальных игроков (игрок не может играть сам с собой).
- **Подсчёт очков в текущем матче** – обновление счёта по очкам, геймам и сетам с учётом правил тенниса (best of 3, тай-брейк при 6/6).
- **Просмотр завершённых матчей** – постраничный список с поиском по имени игрока.
- **Автоматическое сохранение** завершённых матчей в базу данных.

### 🛠️ Технологии
- **Java** 17+ (Servlets, JSP)
- **Maven** (сборка, управление зависимостями)
- **Hibernate** (ORM)
- **H2 Database** (in-memory SQL)
- **JUnit 5** (юнит-тесты)
- **HTML/CSS** (вёрстка без фреймворков, адаптивный дизайн)
- **Apache Tomcat** (контейнер сервлетов)
- **Git/GitHub** (контроль версий)

### 📚 Ресурсы
- [Техническое задание проекта](https://zhukovsd.github.io/java-backend-learning-course/projects/tennis-scoreboard/)
- [Готовые HTML/CSS макеты](https://github.com/zhukovsd/tennis-scoreboard-html-layouts)
- [Правила тенниса](https://www.gotennis.ru/read/world_of_tennis/pravila.html)
