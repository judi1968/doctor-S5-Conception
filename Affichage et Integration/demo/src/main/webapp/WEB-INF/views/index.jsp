<%@ page import="judi.example.demo.Models.Objects.Parametre" %>
<%
  Parametre[] parametres = (Parametre[]) request.getAttribute("parametres");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dokotera</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            text-align: center;
        }

        h1 {
            color: #fe4522;
            animation: fadeIn 1s ease-in-out;
        }

        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            /* box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); */
            width: 400px;
            animation: slideIn 1s ease-in-out;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            margin: 5px;
            transition: background-color 0.3s ease, transform 0.2s ease;
            animation: fadeIn 1s ease-in-out;
        }

        .button:hover {
            background-color: #333;
            color: #fff;
            transform: scale(1.05);
        }

        .form-group {
            margin-bottom: 15px;
            animation: fadeIn 1s ease-in-out;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
        }

        input, select {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #fe4522;
            border-radius: 5px;
            transition: border-color 0.3s ease, transform 0.2s ease;
            animation: fadeIn 1s ease-in-out;
        }

        input:focus, select:focus {
            border-color: #333;
            transform: scale(1.05);
        }

        .button-auto, .button-manual {
            background-color: #fe4522;
            color: white;
            transition: background-color 0.3s ease, transform 0.2s ease;
            animation: fadeIn 1s ease-in-out;
        }

        .button-auto:hover, .button-manual:hover {
            background-color: #4CAF50;
            transform: scale(1.05);
        }

        a {
            text-decoration: none;
            color: #fff;
        }

        .cards-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            animation: fadeIn 1s ease-in-out;
        }

        .card {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 15px;
            width: 30%;
            animation: fadeIn 1s ease-in-out;
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
    <div class="container card">
        <h1>Dokotera</h1>
        <div class="form-container ">
            <a href="/List_patient" class="button button-auto">Liste des patients</a>
            <form action="/add_patient" method="post">
                <div class="form-group">
                    <label for="nom">Nom :</label>
                    <input type="text" id="nom" name="nom">
                </div>
                <div class="form-group">
                    <label for="age">Age :</label>
                    <input type="number" id="age" name="age">
                </div>
                <p>Parametre : <button onclick="addSelect('parametre-div')" type="button" class="button button-auto">Ajouter</button></p>
                <div class="form-group" id="parametre-div" style="display: flex;">
                    <select name="parametres" id="Parametre" style="float: left; margin-left: 5px;">
                        <% for (Parametre parametre : parametres) { %>
                        <option value="<%= parametre.getIdParametre() %>"><%= parametre.getNomParametre() %></option>
                        <% } %>
                    </select>
                    <input type="number" id="niveau" name="niveau" placeholder="niveau" value="0">
                </div>
                <div class="buttons-container">
                    <button type="submit" class="button button-manual">Inserer</button>
                </div>
            </form>
        </div>
    </div>
</body>
<script>
    function addSelect(selectId) {
        var originalSelect = document.getElementById(selectId);
        var clonedSelect = originalSelect.cloneNode(true);

        // Change l'ID pour éviter les duplications
        var newIndex = originalSelect.parentElement.children.length;
        clonedSelect.id = selectId + newIndex;

        // Ajoute le nouvel élément sous le select original
        originalSelect.parentElement.appendChild(clonedSelect);
    }
</script>
</html>
