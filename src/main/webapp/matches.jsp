<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <td>${page.getPlayerName(0, 0)}</td>
                <td>${page.getPlayerName(0, 1)}</td>
                <td><span class="winner-name-td">${page.getPlayerName(0, 2)}</span></td>
            </tr>
            <tr>
                <td>${page.getPlayerName(1, 0)}</td>
                <td>${page.getPlayerName(1, 1)}</td>
                <td><span class="winner-name-td">${page.getPlayerName(1, 2)}</span></td>
            </tr>
            <tr>
                <td>${page.getPlayerName(2, 0)}</td>
                <td>${page.getPlayerName(2, 1)}</td>
                <td><span class="winner-name-td">${page.getPlayerName(2, 2)}</span></td>
            </tr>
            <tr>
                <td>${page.getPlayerName(3, 0)}</td>
                <td>${page.getPlayerName(3, 1)}</td>
                <td><span class="winner-name-td">${page.getPlayerName(3, 2)}</span></td>
            </tr>
            <tr>
                <td>${page.getPlayerName(4, 0)}</td>
                <td>${page.getPlayerName(4, 1)}</td>
                <td><span class="winner-name-td">${page.getPlayerName(4, 2)}</span></td>
            </tr>
        </table>

        <div class="pagination">
            <c:choose>
                <c:when test="${not empty param.filter_by_player_name}">
                    <a class="prev" href="matches?page=${page.getReqPage()-1}&filter_by_player_name=${param['filter_by_player_name']}" style="display: ${page.getReqPage() >= 2 ? 'inline-block' : 'none'};"> < </a>
                    <a class="num-page" href="matches?page=1&filter_by_player_name=${param['filter_by_player_name']}" style="display: ${page.getReqPage() == 1 ? 'none' : 'inline-block'};"> 1 </a>
                    <a class="num-page" style="display: ${page.getStartPage() <=2 ? 'none' : 'inline-block'};"> ... </a>
                    <a class="num-page" href="matches?page=${page.getStartPage()}&filter_by_player_name=${param['filter_by_player_name']}" style="display: ${page.getStartPage() == 1 ? 'none' : 'inline-block'};"> ${page.getStartPage()} </a>
                    <a class="num-page current"> ${page.getReqPage()} </a>
                    <a class="num-page" href="matches?page=${page.getEndPage()}&filter_by_player_name=${param['filter_by_player_name']}" style="display: ${page.getEndPage() == page.getTotalPages() ? 'none' : 'inline-block'};"> ${page.getEndPage()} </a>
                    <a class="num-page" style="display: ${page.getEndPage() >= page.getTotalPages()-1 ? 'none' : 'inline-block'};"> ... </a>
                    <a class="num-page" href="matches?page=${page.getTotalPages()}&filter_by_player_name=${param['filter_by_player_name']}" style="display: ${page.getReqPage() >= page.getTotalPages() ? 'none' : 'inline-block'};"> ${page.getTotalPages()} </a>
                    <a class="next" href="matches?page=${page.getReqPage()+1}&filter_by_player_name=${param['filter_by_player_name']}" style="display: ${page.getReqPage() >= page.getTotalPages() ? 'none' : 'inline-block'};"> > </a>
                </c:when>
                <c:otherwise>
                    <a class="prev" href="matches?page=${page.getReqPage()-1}" style="display: ${page.getReqPage() >= 2 ? 'inline-block' : 'none'};"> < </a>
                    <a class="num-page" href="matches?page=1" style="display: ${page.getReqPage() == 1 ? 'none' : 'inline-block'};"> 1 </a>
                    <a class="num-page" style="display: ${page.getStartPage() <=2 ? 'none' : 'inline-block'};"> ... </a>
                    <a class="num-page" href="matches?page=${page.getStartPage()}" style="display: ${page.getStartPage() == 1 ? 'none' : 'inline-block'};"> ${page.getStartPage()} </a>
                    <a class="num-page current"> ${page.getReqPage()} </a>
                    <a class="num-page" href="matches?page=${page.getEndPage()}" style="display: ${page.getEndPage() == page.getTotalPages() ? 'none' : 'inline-block'};"> ${page.getEndPage()} </a>
                    <a class="num-page" style="display: ${page.getEndPage() >= page.getTotalPages()-1 ? 'none' : 'inline-block'};"> ... </a>
                    <a class="num-page" href="matches?page=${page.getTotalPages()}" style="display: ${page.getReqPage() >= page.getTotalPages() ? 'none' : 'inline-block'};"> ${page.getTotalPages()} </a>
                    <a class="next" href="matches?page=${page.getReqPage()+1}" style="display: ${page.getReqPage() >= page.getTotalPages() ? 'none' : 'inline-block'};"> > </a>
                </c:otherwise>
            </c:choose>
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

