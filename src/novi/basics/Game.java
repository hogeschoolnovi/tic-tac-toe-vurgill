package novi.basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static novi.basics.main2.*;

public class Game {

    private char[] board;
    private int maxRounds;

    private Player player1;
    private Player player2;

    private int drawCount;

    private Player activePlayer;

    public Game(Player player1, Player player2) {
        // speelbord opslaan (1 - 9)
        // uitleg: omdat we altijd met een bord starten met 9 getallen, kunnen we het Tic Tac Toe bord ook direct een waarde geven
        // zie demo project code voor de andere manier met de for loop
        board = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};

        // maximale aantal rondes opslaan
        maxRounds = board.length;

        this.player1 = player1;
        this.player2 = player2;

        drawCount = 0;
        activePlayer = player1;
    }


    public void play() {

        // opslaan hoeveel keer er gelijk spel is geweest

        // -- vanaf hier gaan we het spel opnieuw spelen met dezelfde spelers (nadat op het eind keuze 1 gekozen is)


        // speelbord tonen
        printBoard();


        // starten met de beurt (maximaal 9 beurten)
        for (int round = 0; round < maxRounds; round++) {
            // naam van de actieve speler opslaan
            String activePlayerName = activePlayer.getName();
            // actieve speler vragen om een veld te kiezen (1 - 9)
            System.out.println(activePlayerName + ", please choose a field");
            // gekozen veld van de actieve speler opslaan
            int chosenField = PLAYERINPUT.nextInt();
            int chosenIndex = chosenField - 1;
            // als het veld bestaat
            if (chosenIndex < 9 && chosenIndex >= 0) {
                //als het veld leeg is, wanneer er geen token staat
                if (board[chosenIndex] != player1.getToken() && board[chosenIndex] != player2.getToken()) {
                    // wanneer de speler een token op het bord kan plaatsen
                    // de token van de actieve speler op het gekozen veld plaatsen
                    board[chosenIndex] = activePlayer.getToken();
                    //player.score += 10;
                    // het nieuwe speelbord tonen
                    printBoard();
                    // als het spel gewonnen is


                    // tonen wie er gewonnen heeft (de actieve speler)
                    // de actieve speler krijgt een punt
                    // de scores van de spelers tonen
                    // wanneer we in de laatste beurt zijn en niemand gewonnen heeft
                    // aantal keer gelijk spel ophogen
                    // aantal keer gelijk spel tonen
                    // de beurt doorgeven aan de volgende speler (van speler wisselen)
                    // als de actieve speler, speler 1 is:
                    if (activePlayer == player1) {
                        PLAYERPOSITION.add(chosenField);
                        // maak de actieve speler, speler 2
                        activePlayer = player2;

                    }
                    // anders
                    else {
                        // maak de actieve speler weer speler 1
                        activePlayer = player1;
                        PLAYER2POSITION.add(chosenField);
                    }


                } //of al bezet is
                else {
                    maxRounds++;
                    System.out.println("this field is not available, choose another");
                }
                //versie 2: als het veld leeg is, wanneer de waarde gelijk is aan chosenField
                /*if(board[chosenIndex] != '1' + chosenIndex) {
                    board[chosenIndex] = activePlayerToken;
                }*/
            }
            // als het veld niet bestaat
            else {
                // het mamimale aantal beurten verhogen
                maxRounds++;
                // foutmelding tonen aan de speler
                System.out.println("the chosen field does not exist, try again");
            }
            String result = chekWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }


            // -- terug naar het begin van de volgende beurt
        }
    }

    public void printBoard() {
        for (int fieldIndex = 0; fieldIndex < board.length; fieldIndex++) {
            System.out.print(board[fieldIndex] + " ");
            // als we het tweede veld geprint hebben of het vijfde veld geprint hebben
            // dan gaan we naar de volgende regel
            if (fieldIndex == 2 || fieldIndex == 5) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public String chekWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomrow = Arrays.asList(7, 8, 9);
        List leftColom = Arrays.asList(1, 4, 7);
        List middleColom = Arrays.asList(2, 5, 8);
        List rightColom = Arrays.asList(9, 6, 3);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomrow);
        winning.add(leftColom);
        winning.add(middleColom);
        winning.add(rightColom);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (PLAYERPOSITION.containsAll(l)) {
                player1.addscore();
                return player1.getName() + " score: " + player1.getScore() + "\n" + player2.getName() + " score: " + player2.getScore() + "\n" + "drawcount: " + "" + drawCount;
              

            } else if (PLAYER2POSITION.containsAll(l)) {
                player2.addscore();
                return player2.getName() + " score: " + player2.getScore() + "\n" + player1.getName() + " score: " + player1.getScore() + "\n" + "drawcount: " + "" + drawCount;

            } else if (PLAYERPOSITION.size() + PLAYER2POSITION.size() == 9) {
                drawCount++;
                return "drawcount is:" + drawCount;
            }
        }


        return " ";

    }
}