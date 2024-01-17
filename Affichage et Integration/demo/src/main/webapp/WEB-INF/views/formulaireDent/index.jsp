<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="judi.example.demo.Models.Objects.DentSituation" %>
<%
DentSituation[] dents = (DentSituation[])request.getAttribute("dents");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire Traitement Dentaire</title>
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

        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        select {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            background-color: #008CBA;
            color: white;
        }
    </style>
</head>
<body>

    <div class="form-container">
        <form action="/modify_action" method="post">
            <div class="form-group">
                <button type="button" class="button" onclick="addSelect('changer')">Ajouter</button>
                <label for="changer">Changer :</label>
                <select id="changer" name="changer" required>
                    <% for (DentSituation dentSituation : dents) { %>
                        <option value="<%= dentSituation.getIdDent() %>" ><%= dentSituation.getIdDent() %></option>
                    <% } %>
                </select>

            </div>
            <div class="form-group">
                <button type="button" class="button" onclick="addSelect('traiter')">Ajouter</button>
                <label for="traiter">Traiter :</label>
                <select id="changer" name="changer" required>
                    <% for (DentSituation dentSituation : dents) { %>
                        <option value="<%= dentSituation.getIdDent() %>" ><%= dentSituation.getIdDent() %></option>
                    <% } %>
                </select>

            </div>
            <button type="submit" class="button">Soumettre</button>
        </form>
    </div>
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
</body>
</html>
