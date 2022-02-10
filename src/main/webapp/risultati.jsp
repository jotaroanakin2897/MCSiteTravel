<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.*" %>


<!DOCTYPE html>

<html>

<body>

<jsp:include page="base.jsp"></jsp:include>




<h1>RISULTATI</h1>
<br><br>

  <table border="1" style="width: 99%; ">


      <% ArrayList<ReplyResult> results = (ArrayList<ReplyResult>) request.getAttribute("risultati");
    if(request.getAttribute("risultati") != null)
    {
        Iterator<ReplyResult> iterator = results.iterator();

        while(iterator.hasNext())
        {

            ReplyResult rvalue = iterator.next();
          %>

    <tr style="border-bottom: 1px solid #ddd" >


      <td style="padding: 10px;" width="55%">
        <b><%=rvalue.getQuestion()%></b>
          <% if(rvalue.isEsatto())
          {
          %>
        <a style="color: green">La risposta era <b><%=rvalue.getRightreply()%></b>, risposta esatta!</a>

        <%
        }
        else
        {
        %>
        <a style="color: red">Hai dato la risposta <b><%=rvalue.getGivenreply().getReply()%></b>, ma era <b><%=rvalue.getRightreply()%></b></a>

        <%
        }%>
      </td>


        <%
            }
          }
        %>
      </table>
<%int punteggio=(int)request.getAttribute("punteggio");
  %>

  <br><br><br>
  <h3>
<%
  if(punteggio <= 5)
    {
%>
    <a> Il tuo punteggio totale e': <%=punteggio%> / 10 , Puoi fare di meglio!</a>
    <%
    }
  else
    {
    %>
      <a>   Il tuo punteggio totale e': <%=punteggio%> / 10 , Continua cosi'!</a>
    <%
      }
%>
  </h3>











