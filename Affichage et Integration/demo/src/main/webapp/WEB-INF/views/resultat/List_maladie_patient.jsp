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
            animation: slideIn 1s ease-in-out;
            transition: background-color 0.3s ease, transform 0.2s ease;

        }
        @keyframes slideIn {
            from {
                transform: translateY(-50px);
                opacity: 0;
            }
            to {
                transform: translateY(0);
                opacity: 1;
            }
        }

        table {
            width: 30%;
            margin: 30px auto;
            border-collapse: collapse;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #dddddd;
        }

        th {
            background-color: #ba0000;
            color: white;
        }

        tr:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
    <center>
        <h1 style="color: #ba0000;">Liste des maladies</h1>
    </center>
    <table>
        <thead>
            <tr>
                <th>maladie</th>
                <th style="padding: 5px;"><a href="/resultMedicament?id_patient=<%= id_patient %>&id_maladie=0" style="color: #ffffff; padding: 15px 10px 15px 10px ;border-radius: 5px; background-color: #ba0000; margin-bottom: 15px; text-decoration: none;"> Voir tout les medicaments </th>
            </tr>
        </thead>
        <tbody>
            
            <% for (PatientMaladie patientMaladie : patientMaladies) { %>
                <tr>
                    <td><%= patientMaladie.getNomMaladie() %> </td>
                    <td><a href="/resultMedicament?id_patient=<%= id_patient %>&id_maladie=<%= patientMaladie.getIdMaladie() %>" style="background: #ba0000; padding: 15px 10px 15px 10px ;border-radius: 5px; color: white; margin-bottom: 15px; text-decoration: none;"> Voir tout les medicaments </a>
                </tr>
            <% } %>
        </tbody>
    </table>

</body>
</html>
