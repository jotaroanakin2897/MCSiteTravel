


<!DOCTYPE html>

<html>

<body>

<script>
    function useUser()
    {
        document.getElementById("AuserUsed").hidden=true;
        document.getElementById("AemailUsed").hidden=false;
        document.getElementById("emailUsed").checked=false;
        document.getElementById("inputUser-Mail").name="username";
        document.getElementById("email-usertext").innerText="username";

    }

    function useEmail()
    {
        document.getElementById("AuserUsed").hidden=false;
        document.getElementById("AemailUsed").hidden=true;
        document.getElementById("userUsed").checked=false;
        document.getElementById("inputUser-Mail").name="email";
        document.getElementById("email-usertext").innerText="email";

    }



</script>

<jsp:include page="base.jsp"></jsp:include>

<br>

  <h1>Login</h1>

  <div class="content-section">
      <form action="login" METHOD="POST">
          <br>

          <a  id="AuserUsed"> Use username to log:
              <input type="checkbox" id="userUsed" name="userUsed"  onchange="useUser(this)">
          </a>

          <a hidden="true" id="AemailUsed">
              Use email to log: <input  type="checkbox" id="emailUsed" name="emailUsed"  onchange="useEmail(this)" checked>
          </a>

          <br>

          Please enter your <a id="email-usertext">Email</a>
          <input id="inputUser-Mail" type="text" name="email"/><br>

          Please enter your password
          <input type="password" name="password"/>

          <input type="submit" value="submit"><br>



      </form>

      <a>${error}</a>
    <div class='border-top pt-3'>
      Hai bisogno di un account? <a class="ml-2" href="register">Registrati</a>
    </div>

  </div>
