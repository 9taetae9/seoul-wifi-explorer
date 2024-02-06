package com.example.seoulwifiexplorer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WifiSpotServlet", urlPatterns = {"/wifispot"})
public class WifiSpotServlet extends HttpServlet {
    void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    double userLat = Double.parseDouble(request.getParameter("lat"));
    double userLng = Double.parseDouble(request.getParameter("lng"));



    // Query the database for nearest wifi spots
    // perform the SQL query using the DatabaseUtil class





    //Foward the results to the JSP page
    request.setAttribute("wifiSpots", wifiSpotsList);
    RequestDispatcher dispatcher = request.getRequestDispatcher("wifispot.jsp");
    dispatcher.forward(request, response);
}
