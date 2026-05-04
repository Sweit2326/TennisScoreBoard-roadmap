<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
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
        <h1>Matches</h1>
        <form method="post" action="matches">
        <div class="input-container">
            <input class="input-filter" name="filter_by_player_name" placeholder="Filter by name" type="text"/>
            <div>
                <a href="matches?page=1">
                    <button class="btn-filter">Reset Filter</button>
                </a>
            </div>
        </div>
        </form>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <tr>
                <td>${page.getPlayerName(1, 0)}</td>
                <td>${page.getPlayerName(1, 1)}</td>
                <td><span class="winner-name-td">${page.getWinnerName(1)}</span></td>
            </tr>
            <tr>
                <td>${page.getPlayerName(2, 0)}</td>
                <td>${page.getPlayerName(2, 1)}</td>
                <td><span class="winner-name-td">${page.getWinnerName(2)}</span></td>
            </tr>
            <tr>
                <td>${page.getPlayerName(3, 0)}</td>
                <td>${page.getPlayerName(3, 1)}</td>
                <td><span class="winner-name-td">${page.getWinnerName(3)}</span></td>
            </tr>
            <tr>
                <td>${page.getPlayerName(4, 0)}</td>
                <td>${page.getPlayerName(4, 1)}</td>
                <td><span class="winner-name-td">${page.getWinnerName(4)}</span></td>
            </tr>
            <tr>
                <td>${page.getPlayerName(5, 0)}</td>
                <td>${page.getPlayerName(5, 1)}</td>
                <td><span class="winner-name-td">${page.getWinnerName(5)}</span></td>
            </tr>
        </table>

        <div class="pagination">
            <a class="prev" href="matches?page=${page.getPrevPageNumber()}" style="display: ${page.getPrevPageNumber() <= 0 ? 'none' : 'inline-block'};"> < </a>
            <a class="num-page" href="matches?page=${page.getPrevPageNumber()}" style="display: ${page.getPrevPageNumber() <= 0 ? 'none' : 'inline-block'};">${page.getPrevPageNumber()}</a>
            <a class="num-page current" href="matches?page=${page.getPageNumber()}">${page.getPageNumber()}</a>
            <a class="num-page" href="matches?page=${page.getNextPageNumber()}" style="display: ${page.isNextPageAvailable() ? 'inline-block' : 'none'};">${page.getNextPageNumber()}</a>
            <a class="next" href="matches?page=${page.getNextPageNumber()}" style="display: ${page.isNextPageAvailable() ? 'inline-block' : 'none'};"> > </a>
        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>

