<%@include file="login/header.jspf" %>  
<body class="login-img3-body" style="background-image:url(/resources/images/background.jpg)">
    <div class="container">
      <form class="login-form" action="<c:url value='j_spring_security_check' />" method="post">
        <div class="login-wrap">
            <p class="login-img"><i class="icon_lock_alt"></i></p>
            <%@include file="template/message.jspf" %>
            <div class="input-group">
              <span class="input-group-btn"><i class="icon_profile"></i></span>
              <input type="text" name='j_username' class="form-control" placeholder="Username" autofocus>
            </div>
            <div class="input-group">
                <span class="input-group-btn"><i class="icon_key_alt"></i></span>
                <input type="password" name='j_password' class="form-control" placeholder="Password">
            </div>
            <button class="btn btn-success btn-lg btn-block" type="submit">Login</button>
        </div>
      </form>
    </div>
  </body>
  <%@include file="login/footer.jspf" %>