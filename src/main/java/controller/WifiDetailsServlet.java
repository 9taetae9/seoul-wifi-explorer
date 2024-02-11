package controller;

import DAO.WifiSpotDAO;
import DTO.WifiSpot;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WifiDetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String managementNumber = request.getParameter("managementNumber");
        System.out.println("Received management number: " + managementNumber);

        WifiSpotDAO wifiSpotDAO = new WifiSpotDAO();
        WifiSpot wifiSpot = wifiSpotDAO.getWifiSpotByManagementNumber(managementNumber);

        request.setAttribute("wifiSpotDetails", wifiSpot);
        RequestDispatcher dispatcher = request.getRequestDispatcher("wifiDetails.jsp");
        dispatcher.forward(request, response);
    }
}
