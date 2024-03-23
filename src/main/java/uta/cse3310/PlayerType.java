package uta.cse3310;

//class with 2 fields for nick and color
public class PlayerType {
    private String nickname;
    private String color;
    

    //this is too help navigate lobbies
    private Status status;
    public enum Status {
        Waiting, Playing
    }

    //constructor of variables
    public PlayerType(String nickanme, String color, Status status){
        this.nickname = nickanme;
        this.color = color;
        this.status = status;
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

    public void setStatus(Status status) {
        this.status = status;
    }

}
