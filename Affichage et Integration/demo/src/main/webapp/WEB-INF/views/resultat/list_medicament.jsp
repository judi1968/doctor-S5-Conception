<%@ page import="judi.example.demo.Models.Objects.MedicamentPatientResultat" %>
<%
MedicamentPatientResultat[] medicament_patient = (MedicamentPatientResultat[])request.getAttribute("medicament_patient");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultat des medicaments</title>
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
                <th></th>
                <th></th>
                <th><center>Liste des medicaments</center></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <th>Nom parametre</th>
                <th>Note parametre</th>
                <th>Nom medicament</th>
                <th>Prix initiale</th>
                <th>Effet initiale</th>
                <th>Quantite</th>
                <th>Effet totale</th>
                <th>Prix totale</th>
            </tr>
        </thead>
        <tbody>
            
                
            
            
            <% for (MedicamentPatientResultat medicamentPatientResultat : medicament_patient) {%>
                <tr>
                    <td><%= medicamentPatientResultat.getNomParametre() %></td>
                    <td><%= medicamentPatientResultat.getNoteParametre() %></td>
                    <td><%= medicamentPatientResultat.getNomMedicament() %></td>
                    <td><%= medicamentPatientResultat.getPrixInitiale() %></td>
                    <td><%= medicamentPatientResultat.getEffetInitialeMedicament() %></td>
                    <td><%= medicamentPatientResultat.getQuantite() %></td>
                    <td><%= medicamentPatientResultat.getEffetTotaleMedicament() %></td>
                    <td><%= medicamentPatientResultat.getPrixTotale() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>

</body>
</html>
