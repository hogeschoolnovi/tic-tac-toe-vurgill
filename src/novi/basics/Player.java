package novi.basics;

public class Player {
    // attributen verzamelen
    String name;
    char token;
    int score;

    // methodes acties die de speler kan uitvoeren
    // constructer

    public Player(String name, char token){
      this.name = name;
      this.token = token;
      score = 0;
    }
    // get methoden
    public String getName(){
        return name;
    }
    // get token


    public char getToken() {
        return token;
    }

    public int getScore() {
        return score;
    }

    public void addscore(){
        score++;
    }
}


