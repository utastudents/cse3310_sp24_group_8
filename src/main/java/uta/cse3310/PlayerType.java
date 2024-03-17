package uta.cse3310;

//class with 2 fields for nick and color
public class PlayerType {
    private String nickname;
    private String color;
    
    private Status status;
    public enum Status {
        Waiting, Playing
    }

    
    public PlayerType(String nickanme, String color, Status staus){
        this.nickname = nickanme;
        this.color = color;
        this.status = status;
    }

}
