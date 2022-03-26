/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Account;
import Entity.Player;
import Entity.Ranking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class GetData {

    public final String api_key = "RGAPI-deac1d0c-74dc-4172-a52f-6e55dbf5d950";

    public void closeConnection(Connection con, ResultSet rs, PreparedStatement ps) throws Exception {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }

        } catch (Exception e) {
            throw e;
        }
    }

//    public ArrayList<Player> getListPlayerInfo() throws Exception{
//        Connection con = null;
//        ResultSet rs = null;
//        PreparedStatement ps = null;
//        Player player = null;
//        ArrayList<Player> returnList = new ArrayList<>();
//        try{
//            String query = "Select * from Player_Info";
//            Connect dbc = new Connect();
//            con = dbc.getConnection();
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//            String picture = "";
//            while (rs.next()) {
//                player = new Player(rs.getString("proId"), 
//                                    rs.getString("playerName"), 
//                                    rs.getString("PUUID"), 
//                                    rs.getString("position"));
//                returnList.add(player);
//            }
//            closeConnection(con, rs, ps);
//            return returnList;
//        }catch(Exception e){
//            closeConnection(con, rs, ps);
//            throw e;
//        }
//    }
    public ArrayList<Ranking> getRankingList() throws Exception {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Ranking ranking = null;
        ArrayList<Ranking> returnList = new ArrayList<>();
        try {
            String query = "SELECT ROW_NUMBER() OVER (ORDER BY tb2.rankId DESC, tb2.leaguePoints DESC) as Row_Number, tb1.teamName, tb1.playerName, tb1.summonerName, tb2.Rank, tb2.leaguePoints, CONCAT_WS(' ',tb2.Winrate,'%') as Winrate, tb1.playerPosition FROM\n"
                    + "(SELECT p.teamName, p.playerName,  p.playerPosition, a.summonerId , a.summonerName FROM Player_Info p INNER JOIN Account_Info a ON p.PUUID = a.PUUID) tb1\n"
                    + "INNER JOIN\n"
                    + "(SELECT a.summonerId, a.Rank, a.leaguePoints, b.rankId, a.Winrate FROM\n"
                    + "(SELECT summonerId, CONCAT_WS(' ', tier, rankDivision) as Rank, leaguePoints, CAST((CAST(wins as FLOAT)/(CAST(wins as FLOAT)+CAST(losses AS FLOAT)))*100 AS INT) as Winrate FROM Rank_Info) a LEFT JOIN\n"
                    + "(SELECT rankId, CONCAT_WS(' ', tier, rankDivision) as Rank FROM SORT_RANK) b ON (a.Rank = b.Rank)) tb2 ON (tb1.summonerId = tb2.summonerId) ORDER BY tb2.rankId DESC, tb2.leaguePoints DESC";
            Connect dbc = new Connect();
            con = dbc.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ranking = new Ranking(rs.getInt("Row_number"),
                        rs.getString("teamName"),
                        rs.getString("playerName"),
                        rs.getString("summonerName"),
                        rs.getString("Rank"),
                        rs.getInt("leaguePoints"),
                        rs.getString("Winrate"),
                        rs.getString("playerPosition"));
                returnList.add(ranking);
            }
            closeConnection(con, rs, ps);
            return returnList;
        } catch (Exception e) {
            closeConnection(con, rs, ps);
            throw e;
        }

    }

    public ArrayList<Player> getPlayerInfo() throws Exception {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Player player = null;
        ArrayList<Player> returnList = new ArrayList<>();
        try {
            String squery = "SELECT * FROM Player_Info";
            Connect dbc = new Connect();
            con = dbc.getConnection();
            ps = con.prepareStatement(squery);
            rs = ps.executeQuery();
            while (rs.next()) {
                player = new Player(rs.getString("teamName"),
                        rs.getString("playerName"),
                        rs.getString("PUUID"),
                        rs.getString("playerPosition"));
                returnList.add(player);
            }
            closeConnection(con, rs, ps);
            return returnList;
        } catch (Exception e) {
            closeConnection(con, rs, ps);
            throw e;
        }
    }

//    public ArrayList<Account> getAccountInfo() throws Exception {
//        Connection con = null;
//        ResultSet rs = null;
//        PreparedStatement ps = null;
//        Account account = null;
//        ArrayList<Account> returnList = new ArrayList<>();
//        try {
//            String squery = "SELECT a.PUUID, a.accountId, a.summonerId, a.summonerName, CONCAT_WS(' ', r.tier, r.rankDivision) as [rank], a.summonerLevel,"
//                    + " r.wins, r.losses, r.leaguePoints  FROM Account_Info a INNER JOIN Rank_Info r ON r.summonerId = a.summonerId";
//            Connect dbc = new Connect();
//            con = dbc.getConnection();
//            ps = con.prepareStatement(squery);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                account = new Account(rs.getString("PUUID"), rs.getString("accountId"), rs.getString("summonerId"), rs.getString("summonerName"),
//                        rs.getString("rank"), rs.getInt("summonerLevel"),
//                        rs.getInt("wins"), rs.getInt("losses"), rs.getInt("leaguePoints"));
//                returnList.add(account);
//            }
//            closeConnection(con, rs, ps);
//            return returnList;
//        } catch (Exception e) {
//            closeConnection(con, rs, ps);
//            throw e;
//        }
//    }
    public ArrayList<Account> getAccountInfo() throws Exception {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Account account = null;
        ArrayList<Account> returnList = new ArrayList<>();
        try {
            String squery = "SELECT * FROM Account_Info";
            Connect dbc = new Connect();
            con = dbc.getConnection();
            ps = con.prepareStatement(squery);
            rs = ps.executeQuery();
            while (rs.next()) {
                account = new Account(rs.getString("PUUID"), rs.getString("accountId"),
                        rs.getString("summonerId"), rs.getString("summonerName"), rs.getInt("summonerLevel"));
                returnList.add(account);
            }
            closeConnection(con, rs, ps);
            return returnList;
        } catch (Exception e) {
            closeConnection(con, rs, ps);
            throw e;
        }
    }

    public void clearRecord(String table) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String squery = "DELETE" + " " + table;
            Connect dbc = new Connect();
            con = dbc.getConnection();
            ps = con.prepareStatement(squery);
            ps.executeUpdate();
            closeConnection(con, rs, ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int updateAccount() throws Exception {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Player> arrPlayer = getPlayerInfo();
        int z = 0;
        try {
            clearRecord("Account_Info");
            for (int i = 0; i < arrPlayer.size(); i++) {
                String squery = "USE [VNPlayers]\n"
                        + "BEGIN\n"
                        + "DECLARE @URL NVARCHAR(MAX) = 'https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/"
                        + arrPlayer.get(i).getPUUID() + "?api_key=" + api_key + "';\n"
                        + "Declare @Object as Int;\n"
                        + "Declare @ResponseText as Varchar(8000);\n"
                        + "\n"
                        + "Exec sp_OACreate 'MSXML2.XMLHTTP', @Object OUT;\n"
                        + "Exec sp_OAMethod @Object, 'open', NULL, 'get',\n"
                        + "       @URL,\n"
                        + "       'False'\n"
                        + "Exec sp_OAMethod @Object, 'send'\n"
                        + "Exec sp_OAMethod @Object, 'responseText', @ResponseText OUTPUT\n"
                        + "IF((Select @ResponseText) <> '')\n"
                        + "BEGIN\n"
                        + "     DECLARE @json NVARCHAR(MAX) = (Select @ResponseText)\n"
                        + "	 \n"
                        + "     INSERT INTO Account_Info    "
                        + "     SELECT puuid, accountId, id, [name] as [summonerName], summonerLevel\n"
                        + "     FROM OPENJSON(@json)\n"
                        + "          WITH(id nvarchar(max), accountId nvarchar(max), puuid nvarchar(max), name nvarchar(max), summonerLevel BIGINT);\n"
                        + "END\n"
                        + "ELSE\n"
                        + "BEGIN\n"
                        + "     DECLARE @ErroMsg NVARCHAR(30) = 'No data found.';\n"
                        + "     Print @ErroMsg;\n"
                        + "END\n"
                        + "Exec sp_OADestroy @Object\n"
                        + "END";
                Connect dbc = new Connect();
                con = dbc.getConnection();
                ps = con.prepareStatement(squery);
                z = ps.executeUpdate();
                closeConnection(con, rs, ps);
            }
            return z;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int updateRank() throws Exception {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        int z = 0;
        ArrayList<Account> arrAccount = getAccountInfo();
        try {
            clearRecord("Rank_Info");
            for (int i = 0; i < arrAccount.size(); i++) {
                String squery = "USE [VNPlayers]\n"
                        + "BEGIN\n"
                        + "DECLARE @URL NVARCHAR(MAX) = 'https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/" + arrAccount.get(i).getSummonerId() + "?api_key=" + api_key + "';\n"
                        + "Declare @Object as Int;\n"
                        + "Declare @ResponseText as Varchar(8000);\n"
                        + "\n"
                        + "Exec sp_OACreate 'MSXML2.XMLHTTP', @Object OUT;\n"
                        + "Exec sp_OAMethod @Object, 'open', NULL, 'get',\n"
                        + "       @URL,\n"
                        + "       'False'\n"
                        + "Exec sp_OAMethod @Object, 'send'\n"
                        + "Exec sp_OAMethod @Object, 'responseText', @ResponseText OUTPUT\n"
                        + "IF((Select @ResponseText) <> '')\n"
                        + "BEGIN\n"
                        + "     DECLARE @json NVARCHAR(MAX) = (Select @ResponseText)\n"
                        + "	 \n"
                        + "     INSERT INTO Rank_Info    "
                        + "     SELECT summonerId, tier, [rank] as [rankDivision], wins, losses, leaguePoints\n"
                        + "     FROM OPENJSON(@json)\n"
                        + "          WITH(summonerId nvarchar(100), tier varchar(15), [rank] varchar(3), wins int, losses int, leaguePoints int);\n"
                        + "END\n"
                        + "ELSE\n"
                        + "BEGIN\n"
                        + "     DECLARE @ErroMsg NVARCHAR(30) = 'No data found.';\n"
                        + "     Print @ErroMsg;\n"
                        + "END\n"
                        + "Exec sp_OADestroy @Object\n"
                        + "END";
                Connect dbc = new Connect();
                con = dbc.getConnection();
                ps = con.prepareStatement(squery);
                z = ps.executeUpdate();
                closeConnection(con, rs, ps);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

        return z;
    }

}
