<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Error</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="index.jsp">Home</a>
                <a class="nav-link" href="matches?page=1">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <div class="form-container center" style="padding: 48px 24px;">
            <h1 class="error-title">${errorModel.getTitle()}</h1>
            <p class="error-message">${errorModel.getMessage()}</p>

            <div class="error-details" style="margin-top:16px; color: #666;">
                <p>Code: <strong>${errorModel.getHttpStatus()}</strong></p>
            </div>
            <div style="display:flex; gap:12px; margin-top:20px;">
                <a class="homepage-action-button" href="index.jsp">
                    <button class="btn start-match">Go to Home</button>
                </a>
            </div>

            <p style="margin-top:18px; color:#777; font-size:14px;">
                If the problem persists, contact the administrator.
            </p>
        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
    </div>
</footer>
</body>
</html>