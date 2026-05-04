<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Match Score</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <script src="js/app.js"></script>
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
        <h1>Current match</h1>
        <div class="current-match-image"></div>
        <section class="score">
            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">Points</th>
                </tr>
                </thead>
                <tbody>
                <tr class="player1">
                    <td class="table-text">${match.getPlayerName(0)}</td>
                    <td class="table-text">${match.getSets(0)}</td>
                    <td class="table-text">${match.getGames(0)}</td>
                    <td class="table-text">${match.getPointsValue(0)}</td>
                    <td class="table-text">
                        <form method="post" action="match-score">
                            <input type="hidden" name="playerID" value="0">
                            <input type="hidden" name="matchUUID" value="${match.getMatchUUID()}">
                            <div class="score-btn" style="display: ${match.isMatchFinished() ? 'none' : 'block'};" onclick="this.closest('form').submit()">Score</div>
                        </form>
                    </td>
                </tr>
                <tr class="player2">
                    <td class="table-text">${match.getPlayerName(1)}</td>
                    <td class="table-text">${match.getSets(1)}</td>
                    <td class="table-text">${match.getGames(1)}</td>
                    <td class="table-text">${match.getPointsValue(1)}</td>
                    <td class="table-text">
                        <form method="post" action="match-score">
                            <input type="hidden" name="playerID" value="1">
                            <input type="hidden" name="matchUUID" value="${match.getMatchUUID()}">
                            <div class="score-btn" style="display: ${match.isMatchFinished() ? 'none' : 'block'};" onclick="this.closest('form').submit()">Score</div>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
    </div>
</footer>
</body>
</html>

