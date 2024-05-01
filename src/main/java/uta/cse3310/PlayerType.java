package uta.cse3310;

//class with 2 fields for nick and color
public class PlayerType {
    //Color and player are immutible once you select these they do not change
    private final String nickname;
    private final String color;
    private int score;
    

    //this is too help navigate lobbies
    private Status status;
    public enum Status {
        Waiting, Playing
    }

    //constructor of variables
    public PlayerType(String nickname, String color, Status status){
        //player or color cant be empty
        if (nickname == null || nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("Nickname must not be null or empty.");
        }
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color must not be null or empty.");
        }

        this.nickname = nickname;
        this.color = color;
        this.status = status;
    }

    public void incrementScore(int points) {
        this.score += points;
    }

    //getters and setters
    public String getNickname(){
        return nickname;
    }

    public String getColor(){
        return color;
    }

    public Status getStatus() {
        return status;
    }

    public int getScore() {
        return score;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setScore(int score) {
        this.score = score; 
    }

}
