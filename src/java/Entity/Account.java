/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
public class Account {
    private String PUUID;
    private String accountId;
    private String summonerId;
    private String summonerName;
    private int summonerLevel;

    public String getPUUID() {
        return PUUID;
    }

    public void setPUUID(String PUUID) {
        this.PUUID = PUUID;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public Account() {
    }

    public Account(String PUUID, String accountId, String summonerId, String summonerName, int summonerLevel) {
        this.PUUID = PUUID;
        this.accountId = accountId;
        this.summonerId = summonerId;
        this.summonerName = summonerName;
        this.summonerLevel = summonerLevel;
    }
    
    
    
}
