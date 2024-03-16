package uta.cse3310;

public class Game {
    public PlayerType[][] button; // Grid of PlayerType
    public String[] msg;
    public GameStats ingameStats;
    public GameStats finalGameStats;

    public Game(GameStats ingameStats) {
        this.ingameStats = ingameStats;
        initialize();
    }

    public Game(GameStats finalGameStats) {
        this.finalGameStats = finalGameStats;
        initialize();
    }

    private void initialize() {
        button = new PlayerType[50][50]; // 50x50 grid
        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button[i].length; j++) {
                button[i][j] = PlayerType.NOPLAYER;
            }
        }
        msg = new String[1];
        msg[0] = "Welcome to the Word Search Game!";
    }

    public void update(UserEvent userEvent) {
        // Your logic to handle user events and update game state here
    }
}
