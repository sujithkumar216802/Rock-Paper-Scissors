package com.sujithkumar.rockpaperscissors;

import android.text.Editable;

import androidx.lifecycle.ViewModel;

public class viewmodel extends ViewModel {
    private boolean computer=false,currentroundover=false,gameover=false;
    private int round,currentround,score1,score2,currentuser,player1option,player2option,correct,wrong;
    private Editable name1;
    private Editable name2;

    public boolean isComputer() {
        return computer;
    }

    public void setComputer(boolean computer) {
        this.computer = computer;
    }

    public int getRound() {

        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getCurrentround() {
        return currentround;
    }

    public void setCurrentround(int currentround) {
        this.currentround = currentround;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public Editable getName1() {
        return name1;
    }

    public void setName1(Editable name1) {
        this.name1 = name1;
    }

    public Editable getName2() {
        return name2;
    }

    public void setName2(Editable name2) {
        this.name2 = name2;
    }

    public int getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(int currentuser) {
        this.currentuser = currentuser;
    }

    public int getPlayer1option() {
        return player1option;
    }

    public void setPlayer1option(int player1option) {
        this.player1option = player1option;
    }

    public int getPlayer2option() {
        return player2option;
    }

    public void setPlayer2option(int player2option) {
        this.player2option = player2option;
    }

    public boolean isCurrentroundover() {
        return currentroundover;
    }

    public void setCurrentroundover(boolean currentroundover) {
        this.currentroundover = currentroundover;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }
}
