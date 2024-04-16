package uta.cse3310;

public class ServerEvent {
    private PlayerType userName;
    private int gameId;

    public ServerEvent(PlayerType userName, int gameId) {
        this.userName = userName;
        this.gameId = gameId;
    }

    public PlayerType getUserName() {
        return userName;
    }

    public int getGameId() {
        return gameId;
    }
}
