package com.roadmap.fourth.service;

import com.roadmap.fourth.model.MatchScore;

import java.util.UUID;

public class MatchScoreCalculationService {

    /*
    Логика подсчета очков:
    1: +15  2: +15  3: +10  4: +1 гейм
    Если счет 40:40 то игра продолжается до перевеса в 2 очка
    Если игрок выйграл очко то получает 40A(advantage), если проиграл счет возвращается к 40.

    При выйгрыше 6+ геймов выйгрывается сет.
    При выйгрыше 6:<=5 сетов выйгрывается матч.

    При 6:6 геймах играется тайбрейк:
    -- очки считаются как 1, 2, 3..
    -- игра продолжается до 7 очков.
    */

    int playerId;
    int opponentId = 0;
    MatchScore match;

    public MatchScore pointWonBy(MatchScore match, int playerId) {
        this.match = match;
        this.playerId = playerId;
        if (playerId == 0) {opponentId = 1;}

        if (match.isTieBreak()) {
            handleTieBreakPoint();
        } else handleNormalGamePoint();

        return this.match;
    }

    private void handleNormalGamePoint() {
        int playerPoints = match.getPoints(playerId);
        int opponentPoints = match.getPoints(opponentId);

        switch (playerPoints) {
            case 0:
                match.setPoints(playerId, 15);
                break;
            case 15:
                match.setPoints(playerId, 30);
                break;
            case 30:
                match.setPoints(playerId, 40);
                break;
            case 40:
                if (opponentPoints == 40 || opponentPoints == 41) {
                    handleDeucePoint(playerPoints, opponentPoints);
                } else isGameWon(playerPoints, opponentPoints);
                break;
            case 41:
                handleDeucePoint(playerPoints, opponentPoints);
                break;
            default:
                System.out.println("Something strange happened.");
        }


    }

    private void handleDeucePoint(int playerPoints, int opponentPoints) {
        if (playerPoints == 40 && opponentPoints == 40) {
            match.setPoints(playerId, 41);
        } else if (playerPoints == 41 && opponentPoints == 40) {
            isGameWon(0, 0);
        } else {
            match.setPoints(playerId, 40);
            match.setPoints(opponentId, 40);
        }
    }

    private void handleTieBreakPoint() {
        int playerPoints = match.getPoints(playerId);

        if (playerPoints >= 6) {
            match.setTieBreak(false);
            match.setGames(playerId, match.getGames(playerId)+1);
            isSetWon();
        } else {
            match.setPoints(playerId, playerPoints+1);
        }
    }

    private void isGameWon(int playerPoints, int opponentPoints) {
        if (playerPoints == 40 && opponentPoints == 40) {
            handleDeucePoint(playerPoints, opponentPoints);
        } else if (match.getGames(playerId) == 6) {
            if (match.getGames(playerId) == 6 && match.getGames(opponentId) == 6) {
                match.setTieBreak(true);
                match.setPoints(playerId, 0);
                match.setPoints(opponentId, 0);
                return;
            }
            isSetWon();
        } else {
            match.setGames(playerId, match.getGames(playerId) + 1);

            if (match.getGames(playerId) == 6 && match.getGames(opponentId) == 6) {
                match.setTieBreak(true);
            }

            match.setPoints(playerId, 0);
            match.setPoints(opponentId, 0);
        }
    }

    private void isSetWon() {
        if (match.getSets(playerId) == 2) {
            match.setSets(playerId, match.getSets(playerId)+1);
            match.setGames(playerId, 0);
            match.setPoints(playerId, 0);
            match.setWinner(playerId);
            match.setMatchFinished(true);
        } else {
            match.setSets(playerId, match.getSets(playerId)+1);
            match.setGames(playerId, 0);
            match.setGames(opponentId, 0);
            match.setPoints(playerId, 0);
            match.setPoints(opponentId, 0);
        }
    }
}
