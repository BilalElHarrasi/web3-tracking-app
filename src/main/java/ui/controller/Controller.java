package ui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import domain.db.DbException;
import domain.model.Person;
import domain.db.PersonService;


/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L; //GI - !
    public PersonService service = new PersonService();

    /**
     * // * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //verwerkRequest(request, response);
        verwerkRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        verwerkRequest(request, response);
    }

    protected void verwerkRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        String doel;

        if (command == null || command == "") {
            doel = "index.jsp";
        }

        switch (command) {
            case "Index":
                doel = toIndex(request, response);
                break;
            case "toOverzicht":
                doel = toOverzicht(request, response);
                break;
            case "toRegister":
                doel = toRegister(request, response);
                break;
            case "voegToe":
                doel = voegToe(request, response);
                break;
            case "logIn":
                doel = loginHandeler(request, response);
                break;
            default:
                doel = toIndex(request, response);
        }

        RequestDispatcher view = request.getRequestDispatcher(doel);
        view.forward(request, response);
    }



    //____________________index____________________//

    private String toIndex(HttpServletRequest request, HttpServletResponse response) {
        return "index.jsp";
    }

    private String toRegister(HttpServletRequest request, HttpServletResponse response) {
        return "register.jsp";
    }
    private String toOverzicht(HttpServletRequest request, HttpServletResponse response) {
        return "contacts.jsp";
    }
    //____________________voegtoe____________________//

    private String voegToe(HttpServletRequest request, HttpServletResponse response) {
            ArrayList<String> errors = new ArrayList<>();

            Person person = new Person();
            setUserid(person, errors, request);
            setFirstName(person, errors, request);
            setLastName(person, errors, request);
            setEmail(person, errors, request);
            setPassword(person, errors, request);

            if (errors.size() == 0) {
                try {
                    service.add(person);
                    return "Controller?command=toOverzicht";
                } catch (DbException e) {
                    errors.add(e.getMessage());
                }
            }
            request.setAttribute("errors", errors);
            return "Controller?command=toRegister";


    }

    //____________________SETTERS LIST____________________//

    private void setUserid(Person p, ArrayList<String> errors, HttpServletRequest request) {

        try {
            p.setUserid(request.getParameter("userid"));
            request.setAttribute("userid", request.getParameter("userid"));

        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }


    private void setFirstName(Person p, ArrayList<String> errors, HttpServletRequest request) {
        //String serie = request.getParameter("serie");

        try {
            p.setFirstName(request.getParameter("firstName"));
            request.setAttribute("firstName", request.getParameter("firstName"));

        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    private void setLastName(Person p, ArrayList<String> errors, HttpServletRequest request) {
        try {
            p.setLastName(request.getParameter("lastName"));
            request.setAttribute("lastName", request.getParameter("lastName"));

        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    private void setEmail(Person p, ArrayList<String> errors, HttpServletRequest request) {
        try {
            p.setEmail(request.getParameter("email"));
            request.setAttribute("email", request.getParameter("email"));

        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    private void setPassword(Person p, ArrayList<String> errors, HttpServletRequest request) {
        try {
            p.setPassword(request.getParameter("password"));
            request.setAttribute("password", request.getParameter("password"));

        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }



    //____________________login____________________//

    public String loginHandeler(HttpServletRequest request, HttpServletResponse response) {

        try {
            String userId = request.getParameter("userId").trim();
            Person person = service.get(userId);

            if (person != null && person.isCorrectPassword(request.getParameter("password").trim())) {
                service.update(person);

                HttpSession session = request.getSession();
                session.setAttribute("user", person);
            } else {
                request.setAttribute("error", "Wrong password");
                request.setAttribute("userIdPrevious", userId);
                return "contacts.jsp";
            }
        } catch (DbException e) {
            request.setAttribute("error", e.getMessage());
        }

        return "index.jsp";
    }
}
