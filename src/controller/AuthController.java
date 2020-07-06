package controller;

import framework.controller.WebController;
import model.User;
import service.Auth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class AuthController extends WebController {

    public void index(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        display(req, resp, "index.jsp");
    }

    public void secondNewMethodForVcExample() {
        // Test
    }



    public void signin(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        display(req, resp, "signin.jsp");
    }

    public void post_signin(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String userEmail    = req.getParameter("user_email");
        String userPassword = req.getParameter("user_password");

        Auth.authenticate(userEmail, userPassword);

        if(!Auth.isAuthenticated()) {

            session(req, "message", "User does not exists");
            this.signin(req, resp);
            return;
        }

        req.getSession().setAttribute("user_name", Auth.getUser().getFullName());
        redirect(resp, "/base/product/list");
    }

    // @MVCRoute( path = "signup", method="GET")
    public void signup(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.getRequestDispatcher("/signup.jsp").forward(req, resp);
    }

    // @MVCRoute( path = "signup", method="post")
    public void post_signup(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {

        String userFirstName        = req.getParameter("user_fname");
        String userLastName         = req.getParameter("user_lname");
        String userPassword         = req.getParameter("user_pass");
        String userPasswordRepeat   = req.getParameter("user_pass_repeat");
        String userEmail            = req.getParameter("user_email");
        String userName             = req.getParameter("user_name");
        boolean isRegistrationSuccess = User.create(userName, userPassword, userFirstName, userLastName, userEmail);
        if(isRegistrationSuccess) {
            req.setAttribute("is_loged_in", true);
            req.setAttribute("login_message", "You have loged in successfuly");
        }

        // Fetch collection logic
        // ArrayList<Object> userCollection = DatabaseOrm.fetchAll(User.class);
        // System.out.println(userCollection.size());

        // Insert data logic
        // User prazExampleOrmUser = new User("georgi.georgiv", "Goshko", "Georgiev", "g.g@mail.bg", "password");
        // DatabaseOrm.insert(prazExampleOrmUser);

        this.signup(req, resp);
    }


    private void processUserSignIn() {

    }

    private void processUserSignUp() {

    }
}
