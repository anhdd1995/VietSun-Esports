<%-- 
    Document   : HomePage
    Created on : Feb 17, 2022, 2:19:13 AM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <script type="text/javascript" src="https://unpkg.com/xlsx@0.15.1/dist/xlsx.full.min.js"></script>
        <style>
            body{
                padding: 1em;
                background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAcAAAAHCAYAAADEUlfTAAAAQElEQVQIW2P89OvDfwYo+PHjJ4zJwMHBzsAIk0SXAKkCS2KTAEu++vQSbizIKGQAl0SXAJkGlsQmAbcT2Shk+wH0sCzAEOZW1AAAAABJRU5ErkJggg==);
            }
            a{
                color: #739931;
            }
            .page{
                max-width: 60em;
                margin: 0 auto;
            }
            table th,
            table td{
                text-align: left;
            }
            table.layout{
                width: 100%;
                border-collapse: collapse;
            }
            table.display{
                margin: 1em 0;
            }
            table.display th,
            table.display td{
                border: 1px solid #B3BFAA;
                padding: .5em 1em;
            }

            table.display th{
                background: #D5E0CC;
            }
            table.display td{
                background: #fff;
            }

            table.responsive-table{
                box-shadow: 0 1px 10px rgba(0, 0, 0, 0.2);
            }

            @media (max-width: 30em){
                table.responsive-table{
                    box-shadow: none;
                }
                table.responsive-table thead{
                    display: none;
                }
                table.display th,
                table.display td{
                    padding: .5em;
                }

                table.responsive-table td:nth-child(1):before{
                    content: 'Number';
                }
                table.responsive-table td:nth-child(2):before{
                    content: 'Name';
                }
                table.responsive-table td:nth-child(1),
                table.responsive-table td:nth-child(2){
                    padding-left: 25%;
                }
                table.responsive-table td:nth-child(1):before,
                table.responsive-table td:nth-child(2):before{
                    position: absolute;
                    left: .5em;
                    font-weight: bold;
                }

                table.responsive-table tr,
                table.responsive-table td{
                    display: block;
                }
                table.responsive-table tr{
                    position: relative;
                    margin-bottom: 1em;
                    box-shadow: 0 1px 10px rgba(0, 0, 0, 0.2);
                }
                table.responsive-table td{
                    border-top: none;
                }
                table.responsive-table td.organisationnumber{
                    background: #D5E0CC;
                    border-top: 1px solid #B3BFAA;
                }
                table.responsive-table td.actions{
                    position: absolute;
                    top: 0;
                    right: 0;
                    border: none;
                    background: none;
                }
            }
        </style>
    </head>
    <body>
    <center><button onclick="ExportToExcel('xlsx')">Export to excel</button></center>
        <table id="tbl_exporttable_to_xls" style="width: 60%; margin: auto">
            <tr>
                <th colspan="8""><center>VIETSUN ESPORTS MONITOR SYSTEM</center></th>
            </tr>
            <tr>
                <th>Xếp hạng</th>
                <th>Đội</th>
                <th>Player</th>
                <th>Vị trí</th>
                <th>Ingame</th>
                <th>Rank hiện tại</th>
                <th>Điểm rank</th>
                <th>Tỉ lệ thắng</th>
            </tr>
            <c:forEach items="${arrRanking}" var="player">
                <tr>
                    <td>${player.getRow_number()}</td>
                    <td>${player.getTeamName()}</td>
                    <td>${player.getPlayerName()}</td>
                    <td>${player.getPosition()}</td>
                    <td>${player.getSummonerName()}</td>
                    <td>${player.getRank()}</td>
                    <td>${player.getLeaguePoints()}</td>
                    <td>${player.getWinrate()}</td>
                </tr>
            </c:forEach>
        </table>
        

    <script>

        function ExportToExcel(type, fn, dl) {
            var elt = document.getElementById('tbl_exporttable_to_xls');
            var wb = XLSX.utils.table_to_book(elt, { sheet: "sheet1" });
            return dl ?
                XLSX.write(wb, { bookType: type, bookSST: true, type: 'base64' }) :
                XLSX.writeFile(wb, fn || ('Monitor.' + (type || 'xlsx')));
        }

    </script>
    </body>
</html>
