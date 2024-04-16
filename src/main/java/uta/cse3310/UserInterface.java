package uta.cse3310;

public class UserInterface {
    //class to get the status of a player, ready (true) or not (false)
    private boolean readyUp;
    PlayerType players;

    public UserInterface(boolean readyUp){

        this.readyUp = readyUp;
    }

    public boolean getReadyUp()
    {
        return readyUp;
    }

    public void setReadyUp(boolean readyUp){
        this.readyUp = readyUp;
    }

}
