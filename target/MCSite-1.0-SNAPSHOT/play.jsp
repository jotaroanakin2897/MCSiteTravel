<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Citta" %>


<!DOCTYPE html>

<html>

<body>

<jsp:include page="base.jsp"></jsp:include>






<br>
<form action="quiz" >

  <h1 name ="username" value ="'${username}'">Benvenuto nel gioco ${username}</h1>

    <input type="hidden" name="username" value="${username}">




    <div class="content-section">
      <h1>Seleziona la citta'</h1>

      <select name="cittaselect">

      <%
          ArrayList<Citta> q = (ArrayList<Citta>) request.getAttribute("citta");


          // Iterating through subjectList

          if(request.getAttribute("citta") != null)
          {
              Iterator<Citta> iterator = q.iterator();

              while(iterator.hasNext())
              {
                  Citta qvalue = iterator.next();
      %>

          <option value="<%=qvalue.getCitta()%>">
              <%=qvalue.getCitta()%>
          </option>
      <%
              }
          }
      %>

      </select>

          <input type="submit" value="Inizia il quiz!"><br>
  </div>
</form>



