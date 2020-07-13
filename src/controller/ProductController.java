package controller;

import config.RouteMap;
import framework.controller.WebController;
import model.Product;
import service.Auth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController extends WebController {

    public void index(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if(!Auth.isAuthenticated()) {

            redirect(resp, RouteMap.HOME);
            return;
        }

        System.out.println("Product : INDEX");
    }

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!Auth.isAuthenticated()) {

            redirect(resp, RouteMap.HOME);
            return;
        }

        String pageIndexQuery   = getQueryValue(req, "page_index");
        int pageIndex           = (pageIndexQuery != null) ? Integer.parseInt(pageIndexQuery) : 1;

        String pageLimitQuery   = getQueryValue(req, "page_limit");
        int pageLimit           = (pageLimitQuery != null) ? Integer.parseInt(pageLimitQuery) : 2;

        session(req, "product_list_next_page", String.valueOf(pageIndex + 1));
        session(req, "product_list_prev_page", String.valueOf(pageIndex - 1));
        session(req, "product_list_page_limit", String.valueOf(pageLimit));

        // select all product
        ArrayList<Product> listProductCollection = Product.listAllProduct(pageIndex, pageLimit);
        session(req, "product_list", listProductCollection);

        session(req, "product_list_has_next_page", listProductCollection.size() == pageLimit);
        session(req, "product_list_has_prev_page", pageIndex > 1);


        // return only a subset of product LIMIT
        // display on screen
        display(req, resp, "product.jsp");
    }

    public void create(HttpServletRequest req, HttpServletResponse resp) {

    }

    public void remove(HttpServletRequest req, HttpServletResponse resp) {

    }

    public void details(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {

        String productId = getQueryValue(req, "productid");

        if(productId == null) {
            redirect(resp, RouteMap.PRODUCT_NOT_FOUND);
            return;
        }

        int id          = Integer.parseInt(productId);
        Product product = Product.fetchProductById(id);


        if(product == null) {

            redirect(resp, RouteMap.PRODUCT_NOT_FOUND);
            return;
        }

        session(req, "product_details_product", product);
        display(req, resp, "product_details.jsp");
    }

    public void notfound(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        display(req, resp, "product_not_found.jsp");
    }

    public void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        display(req, resp, "product_search.jsp");
    }

    public void q(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String productName = req.getParameter("product_name");
        ArrayList<Product> productCollection = Product.listAllProductByName(productName);

    }
}

//  base/product/list
//  <a href="base/product/list?id=10&limit=10&page=1">More info</a>
// base/product/list/10
