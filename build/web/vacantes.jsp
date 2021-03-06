<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
    <title>Lista de todas las vacantes</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/justified-nav.css" rel="stylesheet">

  </head>

  <body>

    <div class="container">

      <!-- The justified navigation menu is meant for single line per list item.
           Multiple lines will require custom code not provided by Bootstrap. -->
      <div class="masthead">
        <h3 class="text-muted">My Company</h3>
        <nav>
          <ul class="nav nav-justified">
            <li><a href="homepage">Inicio</a></li>            
            <li><a href="usuario?action=validaLogin">Administración</a></li>                         
            <li><a href="#">Acerca</a></li>            
          </ul>
        </nav>
      </div>
      <form method ="GET" action="vacante" class="navbar-form navbar-right">
        <div class="form-group">
          <input type="text" name="query" placeholder="Buscar oferta..." class="form-control">
          <input type="hidden" name="action" value="search">
        </div>        
        <button type="submit" class="btn btn-success">Buscar</button>
      </form>
      <br><br><br>

      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><b>Lista de Vacantes</b></h3>
        </div>
        <div class="panel-body">
          <table class="table table-striped">
            <thead>
              <tr>
                <th class="left">ID</th>
                <th>Vacante</th>
                <th>Publicado</th>                
                <th></th>
              </tr>
            </thead>
            <tbody>
                <c:forEach items="${vacantes}" var="vacantes" varStatus="status">
                    <tr>
                      <td class="left">${vacantes.id}</td>
                      <td>${vacantes.nombre}</td>
                      <td>${vacantes.fechaPublicacion}</td>
                      <td>
                        <a class="btn btn-default" href="vacante?action=ver&id=${vacantes.id}" role="button">Ver Detalles</a>
                        <c:if test="${usuario != null}">
                            <a class="btn btn-default" href="vacante?action=eliminar&id=${vacantes.id}" role="button">Delete</a>
                        </c:if>
                      </td>  
                    </tr>
                </c:forEach>
                <!--<tr>
                  <td class="left">My id</td>
                  <td>Name of the job</td>
                  <td>fecha</td>
                  <td>
                    <a class="btn btn-default" href="#" role="button">Ver Detalles</a>                                                    
                    <a class="btn btn-default" href="#" role="button">Eliminar</a>                         
                  </td>  
                </tr>
                <tr>
                  <td class="left">My id</td>
                  <td>Nombre vacante</td>
                  <td>fecha</td>
                  <td>
                    <a class="btn btn-default" href="#" role="button">Ver Detalles</a>                                                    
                    <a class="btn btn-default" href="#" role="button">Eliminar</a>                         
                  </td>  
                </tr>-->
            </tbody>           
          </table>
        </div>
      </div>

      <!-- Site footer -->
      <footer class="footer">
        <p>&copy; 2016 My Company, Inc.</p>
      </footer>

    </div> <!-- /container -->

  </body>
</html>
