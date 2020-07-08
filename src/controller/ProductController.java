package controller;

import framework.controller.WebController;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductController extends WebController {

    public void index(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Product : INDEX");
    }

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


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
}

//  base/product/list
//  <a href="base/product/list?id=10&limit=10&page=1">More info</a>
// base/product/list/10
