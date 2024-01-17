<%@ page import="judi.example.demo.Models.Objects.Patient" %>
<%
    Patient[] patients = (Patient[])request.getAttribute("patients");
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
                <th><center>Liste des patients</center></th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <th></th>
                <th>Nom</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            
                
                
            <% for (Patient patient : patients) { %>
                <tr>
                    <td></td>
                    <td><%= patient.getNomPatient() %> </td>
                    <td><a href="/predir_maladie?id_patient=<%= patient.getIdPatient() %>" style="background: #008CBA; padding: 15px 10px 15px 10px ;border-radius: 5px; color: white; margin-bottom: 15px; text-decoration: none;"> Predire maladie </a>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>

</body>
</html>
