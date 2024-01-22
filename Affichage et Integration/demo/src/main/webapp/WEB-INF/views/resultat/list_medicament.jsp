<%@ page import="judi.example.demo.Models.Objects.MedicamentPatientResultat" %>
<%
MedicamentPatientResultat[] medicament_patient = (MedicamentPatientResultat[])request.getAttribute("medicament_patient");
double prixTotale = (double)request.getAttribute("prixTotale");
String nomPatient = (String)request.getAttribute("patient_name");
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
            transition: background-color 0.3s ease, transform 0.2s ease;
            animation: slideIn 1s ease-in-out;
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
            background-color: #ba0000;
            color: white;
        }

        tr:hover {
            background-color: #d2d2d2;
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
        tr{
            margin: 5px;
        }
    </style>
</head>
<body>
   <center>
    <h1 style="color: #ba0000;">Les medicaments</h1>
   </center>
   <table>
    <thead>
        <tr>
            <th>Nom patient</th>
            <td><%= nomPatient %></td>
        </tr>
        <tr><th>Prix totale</th><td><%= prixTotale %> Ar</td></tr>

    </thead>
   </table>
    <table>
        <thead>
            <tr>
                <th>Parametre</th>
                <th>Note</th>
                <th>Medicament</th>
                <!-- <th>Prix unite</th> -->
                <th>Effet unite</th>
                <th>Quantite</th>
                <th>Effet totale</th>
                <th>Prix</th>
            </tr>
        </thead>
        <tbody>
            <% for (MedicamentPatientResultat medicamentPatientResultat : medicament_patient) {%>
                <tr>
                    <td><%= medicamentPatientResultat.getNomParametre() %></td>
                    <td><%= medicamentPatientResultat.getNoteParametre() %></td>
                    <td><%= medicamentPatientResultat.getNomMedicament() %></td>
                    <% // medicamentPatientResultat.getPrixInitiale() %> 
                    <td><%= medicamentPatientResultat.getEffetInitialeMedicament() %></td>
                    <td><%= medicamentPatientResultat.getQuantite() %></td>
                    <td><%= medicamentPatientResultat.getEffetTotaleMedicament() %></td>
                    <td><%= medicamentPatientResultat.getPrixTotale() %> Ar</td>
                </tr>
            <% } %>
        </tbody>
    </table>

</body>
</html>
