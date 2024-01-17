<%@ page import="judi.example.demo.Models.Objects.Parametre" %>
<%
  Parametre[] parametres = (Parametre[]) request.getAttribute("parametres");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Solonify</title>
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


        .button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            margin: 5px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input, select {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .button-auto {
            background-color: #4CAF50;
            color: white;
        }

        .button-manual {
            background-color: #008CBA;
            color: white;
        }
        a{
          text-decoration: none;
        }
    </style>
</head>
<body>
  <div class="container">
    
    <div class="form-container">
      <a href="/List_patient" style="background: #008CBA; padding: 15px 10px 15px 10px ;border-radius: 5px; color: white; margin-bottom: 15px;"> Liste des patients</a>
      <br>
      <div style="margin: 20px;"></div>
      <form action="/add_patient" method="post">
        <div class="form-group">
          <button type="submit" class="button button-manual">Inserer</button>
          <label for="nom">Nom :</label>
          <input type="text" id="nom" name="nom" >
        </div>
        <div class="form-group">
          <label for="age">Age :</label>
          <input type="number" id="age" name="age" >
        </div>
        <p>Parametre :  <button onclick="addSelect('parametre-div')" type="button">Ajouter</button>  </p>
        <div class="form-group" id="parametre-div" style="display: flex;">
          <select name="parametres" id="Parametre" style="float: left; margin-left: 5px">
            <% for (Parametre parametre : parametres) { %>
              <option value="<%= parametre.getIdParametre() %>"><%= parametre.getNomParametre() %></option>
              <% } %>
            </select>
          <input type="number" id="niveau" name="niveau" placeholder="niveau" value="0">
        </div>
        <div class="buttons-container">
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
