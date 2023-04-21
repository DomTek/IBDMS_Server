package ibdms_server;

import java.io.*;

/**
 *
 * @author Nighthawk
 */
// Serrializable makes it possible to serialize and send this class object to the client
public class Drone implements Serializable {

    private int ID;
    private String name;
    private int posX;
    private int posY;

    public Drone(int ID, String name, int posX, int posY) {
        this.ID = ID;
        this.name = name;
        this.posX = posX;
        this.posY = posY;
    }

    public boolean isAtFire(Fire fire) {
        return this.posX == fire.getPosX() && this.posY == fire.getPosY();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

}
