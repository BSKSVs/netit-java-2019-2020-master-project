package controller;

import framework.controller.WebController;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ProductController extends WebController {

    public void index(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Product : INDEX");
    }

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Product : LIST");

        // select all product
        ArrayList<Product> listProductCollection = Product.listAllProduct();
        session(req, "product_list", listProductCollection);

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
