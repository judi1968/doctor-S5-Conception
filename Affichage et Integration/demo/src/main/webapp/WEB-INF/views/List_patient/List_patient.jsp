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
            transition: background-color 0.3s ease, transform 0.2s ease;
            animation: slideIn 1s ease-in-out;
        }
        h1{
            color: #ba0000;
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
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #ba0000;
            color: white;
        }

        tr:hover {
            background-color: #f5f5f5;
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

        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }
    </style>
</head>
<body>
   <center>
    <h1 style="margin-top: 5px;">Liste patient</h1>
   </center>
    <table>
        <thead>
            <tr>
                <th>Nom</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <% for (Patient patient : patients) { %>
                <tr>
                    <td><%= patient.getNomPatient() %> </td>
                    <td><a href="/predir_maladie?id_patient=<%= patient.getIdPatient() %>" style="background: #ba0000; padding: 15px 10px 15px 10px ;border-radius: 5px; color: white; margin-bottom: 15px; text-decoration: none;"> Predire maladie </a>
                </tr>
            <% } %>
        </tbody>
    </table>

</body>
</html>
