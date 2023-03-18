import java.io.Serializable;

public class Player implements Serializable {
    private String playerName;
    private int gamesPlayed;
    private int gamesWon;

    public Player(String playerName) {
        this.playerName = playerName;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    public void incrementGamesWon() {
        this.gamesWon++;
    }

    @Override
    public String toString() {
        return String.format("%s - Games Played: %d, Games Won: %d", playerName, gamesPlayed, gamesWon);
    }
}