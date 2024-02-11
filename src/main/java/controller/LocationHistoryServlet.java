package controller;

import DAO.UserHistoryDAO;
import DTO.UserHistory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LocationHistoryServlet", urlPatterns = {"/userLocationHistory"})
public class LocationHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserHistoryDAO userHistoryDAO = new UserHistoryDAO();
        List<UserHistory> userHistories = userHistoryDAO.getUserLocationHistory();
        request.setAttribute("userHistories", userHistories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userLocationHistory.jsp");
        dispatcher.forward(request, response);
    }
}
