package PMS.PlayerMatchSysteem.model;

public class Player {
    private int Player_id;
    private String Name;

    public Player(int Player_id, String Name) {
        this.Player_id = Player_id;
        this.Name = Name;
    }

    public Player() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        Name = Name;
    }

    public int getID() {
        return Player_id;
    }

    public void setID(int ID) {
        this.Player_id = ID;
    }

    @Override
    public String toString() {
        return "Player{ ID= "+Player_id+" | Name= " + Name + " }";
    }
}
