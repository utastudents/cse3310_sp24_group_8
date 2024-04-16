package uta.cse3310;

public class UserInterface {
    private boolean readyUp;
    PlayerType players;

    public UserInterface() {
        this.readyUp = false; //default to not ready
    }

    public UserInterface(boolean readyUp){
        this.readyUp = readyUp;
    }

    public boolean getReadyUp() {
        return readyUp;
    }

    public void setReadyUp(boolean readyUp){
        this.readyUp = readyUp;
    }
}

