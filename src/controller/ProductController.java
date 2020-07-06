package controller;

import framework.controller.WebController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductController extends WebController {

    public void index(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Product : INDEX");
    }

    public void list(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Product : LIST");
    }

    public void create(HttpServletRequest req, HttpServletResponse resp) {

    }

    public void remove(HttpServletRequest req, HttpServletResponse resp) {

    }
}

//  base/product/list
//  <a href="base/product/list?id=10&limit=10&page=1">More info</a>
// base/product/list/10
