<%@ page import="judi.example.demo.Models.Objects.ResultatTraitementAutomatique" %>
<%@ page import="judi.example.demo.Models.Objects.DentResultatTraitement" %>
<%
    ResultatTraitementAutomatique resultat = (ResultatTraitementAutomatique)request.getAttribute("resultat");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultat du traitement automatique</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            /* display: flex; */
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        table {
            width: 80%;
            margin: 30px auto;
            border-collapse: collapse;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #008CBA;
            color: white;
        }

        tr:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>Prix totale</th>
                <td><%= resultat.getPrix_traitement() %></td>
            </tr>
        </thead>
    </table>
    <br>
    <table>
        <thead>
            <tr>
                <th></th>
                <th><center>Detail du traitement</center></th>
                <th></th>
            </tr>
            <tr>
                <th>ID Dent</th>
                <th>Type anomalie</th>
                <th>Prix</th>
            </tr>
        </thead>
        <tbody>
            <% for (DentResultatTraitement dentResultatTraitement : resultat.getDentResultatTraitementsAnomalieTraite()) { %>
                <tr>
                    <td><%= dentResultatTraitement.getIdDent() %> </td>
                    <td><%= dentResultatTraitement.getSituation_precendent_String() %> </td>
                    <td><%= dentResultatTraitement.getPrix_traitement() %> </td>
                </tr>
            <% } %>
        </tbody>
    </table>

</body>
</html>
