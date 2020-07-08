<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Product" %>
<jsp:include page="components/header.jsp"></jsp:include>

<h1> Product list</h1>

<%
    ArrayList<Product> productCollection = (ArrayList<Product>) request.getSession().getAttribute("product_list");

    for(int i = 0; i < productCollection.size(); i++) {

        out.print("<h3>" + productCollection.get(i).getTitle() + "</h3>");
        out.print("<div>" + productCollection.get(i).getDescription() + "</div>");
        out.print("<div>" + productCollection.get(i).getPrice()+ "</div>");
        out.print("<hr>");
    }
%>


<jsp:include page="components/footer.jsp"></jsp:include>