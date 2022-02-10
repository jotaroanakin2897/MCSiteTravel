<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html>

<body>

<jsp:include page="base.jsp"></jsp:include>

<br>

  <h1>Pagina Di Registrazione</h1>

  <div class="content-section">
    <form method="POST" action="register">
      <label >Username</label>
      <input type="text" name="username" id="nome" placeholder="es: MarioRossi" required>
      <br>
      <label >Email</label>
      <input type="email" name="email" id="email" placeholder="es: mariorossi93@gmail.com" required>
      <br>
      <label >Password</label>
      <input type="password" name="password" id="password"  required>
      <br>
      <input type="submit" value="Registrati">
    </form>

    <a>${error}</a>

    <div class="border-top pt-3">
      <small class="text-muted">
        Hai già un account? <a class="ml-2" href="login.jsp">Effettua il login</a>
      </small>
    </div>
  </div>
