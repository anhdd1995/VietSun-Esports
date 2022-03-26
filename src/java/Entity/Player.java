/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
public class Player {
    private String proId;
    private String playerName;
    private String PUUID;
    private String position;

    public Player() {
    }

    public Player(String proId, String playerName, String PUUID, String position) {
        this.proId = proId;
        this.playerName = playerName;
        this.PUUID = PUUID;
        this.position = position;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPUUID() {
        return PUUID;
    }

    public void setPUUID(String PUUID) {
        this.PUUID = PUUID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    
}
