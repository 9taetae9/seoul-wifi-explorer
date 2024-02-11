package controller;

import DAO.UserHistoryDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteUserHistoryServlet")
public class DeleteUserHistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserHistoryDAO userHistoryDAO = new UserHistoryDAO();
        userHistoryDAO.deleteUserHistory(id);

        response.sendRedirect("userLocationHistory");
    }
}
