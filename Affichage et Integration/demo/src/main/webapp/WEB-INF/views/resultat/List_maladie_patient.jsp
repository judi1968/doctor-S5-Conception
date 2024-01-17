<%@ page import="judi.example.demo.Models.Objects.PatientMaladie" %>
<%
    PatientMaladie[] patientMaladies = (PatientMaladie[])request.getAttribute("patientMaladies");
    int id_patient = (int)request.getAttribute("id_patient");
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
                <th></th>
                <th><center>Liste des maladies du patient</center></th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <th></th>
                <th>Nom</th>
                <th><a href="/resultMedicament?id_patient=<%= id_patient %>&id_maladie=0" style="background: #ffffff; padding: 15px 10px 15px 10px ;border-radius: 5px; color: #008CBA; margin-bottom: 15px; text-decoration: none;"> Voir tout les medicaments </th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            
            <% for (PatientMaladie patientMaladie : patientMaladies) { %>
                <tr>
                    <td></td>
                    <td><%= patientMaladie.getNomMaladie() %> </td>
                    <td><a href="/resultMedicament?id_patient=<%= id_patient %>&id_maladie=<%= patientMaladie.getIdMaladie() %>" style="background: #008CBA; padding: 15px 10px 15px 10px ;border-radius: 5px; color: white; margin-bottom: 15px; text-decoration: none;"> Voir tout les medicaments </a>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>

</body>
</html>
