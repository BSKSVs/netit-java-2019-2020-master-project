<%@ page import="model.Product" %><%--
  Created by IntelliJ IDEA.
  User: mail
  Date: 7/13/2020
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h2>Product Details REQUEST SESSION</h2>
    <%
        Product detailsProduct =  (Product)request.getSession().getAttribute("product_details_product");
        out.print(detailsProduct.getDescription());
    %>

    <h2>Product Details HTTP Session</h2>
    <%
        Product sessionProduct = (Product)session.getAttribute("product_details_product");
        out.print(sessionProduct.getDescription());

    %>

</body>
</html>
