<%@ page import="Model.Svolgimento_Quiz" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>


<!DOCTYPE html>

<html>

<body>

<jsp:include page="base.jsp"></jsp:include>
<br>

  <div class="content-section">
      <h1>CLASSIFICA</h1>

      <table border="1" style="width: 99%; ">
      <thead>
      <tr>
          <th>Utente</th>
          <th>Citta'</th>
          <th>Punteggio</th>
      </tr>
      </thead>


      <% ArrayList<Svolgimento_Quiz> records = (ArrayList<Svolgimento_Quiz>) request.getAttribute("records"); %>
      <%

    if(request.getAttribute("records") != null)
    {
        Iterator<Svolgimento_Quiz> iterator = records.iterator();

        while(iterator.hasNext())
        {
            Svolgimento_Quiz sqvalue = iterator.next();

      %>
          <tr style="border-bottom: 1px solid #ddd" >
          <tr style="border-bottom: 1px solid #ddd" >

              <td style="padding: 10px;" width="15%"><br><b> <%=sqvalue.getUser().getUsername()%></b>
                  <br>

              </td>

              <td style="padding: 10px;" width="5%"><br><b> <%=sqvalue.getQ().getCity()%></b>
                  <br>

              </td>

              <td style="padding: 10px;" width="15%"><br><b> <%=sqvalue.getPunteggio()%></b>
                  <br>

              </td>


          </tr>


          <%
                  }
              }
          %>
      </table>


  </div>
