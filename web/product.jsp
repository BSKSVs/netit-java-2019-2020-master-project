<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Product" %>
<%@ page import="service.Auth" %>
<%@ page import="service.Util" %>
<jsp:include page="components/header.jsp"></jsp:include>

<h1> Product list</h1>

<%
    ArrayList<Product> productCollection = (ArrayList<Product>) request.getSession().getAttribute("product_list");

    for (Product element : productCollection) {

        out.print("<h3>" + element.getTitle() + "</h3>");
        out.print("<div>" + element.getDescription() + "</div>");
        out.print("<div>" + element.getPrice() + "</div>");

        if (Auth.isAuthenticated()) {

            String buyAction = Util.getFullPath("product/buy?id=" + element.getId());
            String buyComponent = "<a href=\"" + buyAction + "\">Buy</a>";
            out.print(buyComponent);
        }

        out.print("<hr>");
    }

    // if(productCollection.size() > 0) {
    boolean hasNextPage = (boolean) request.getSession().getAttribute("product_list_has_next_page");
    boolean hasPrevPage = (boolean) request.getSession().getAttribute("product_list_has_prev_page");

    if(hasNextPage) {
        String nextPageIndex    = (String) request.getSession().getAttribute("product_list_next_page");
        String pageLimit        = (String) request.getSession().getAttribute("product_list_page_limit");
        String nextPage = Util.getFullPath("product/list?page_index=" + nextPageIndex + "&page_limit=" + pageLimit);
        String nextPageLink = "<a href='" + nextPage + "'>Next Page</a>";
        out.print(nextPageLink);
    }

    if(hasPrevPage) {
        String prevPageIndex    = (String) request.getSession().getAttribute("product_list_prev_page");
        String pageLimit        = (String) request.getSession().getAttribute("product_list_page_limit");
        String prevPage         = Util.getFullPath("product/list?page_index=" + prevPageIndex + "&page_limit=" + pageLimit);
        String prevPageLink     = "<a href='" + prevPage + "'>Prev Page</a>";
        out.print(prevPageLink);
    }

    // }
%>




<jsp:include page="components/footer.jsp"></jsp:include>