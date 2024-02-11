package controller;

import DAO.UserHistoryDAO;
import DTO.UserHistory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/saveUserLocation")
public class SaveUserLocationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        double lat = Double.parseDouble(request.getParameter("lat"));
        double lng = Double.parseDouble(request.getParameter("lng"));
        String queryDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        UserHistory userHistory = new UserHistory(0, lat, lng, queryDate);
        new UserHistoryDAO().saveUserLocation(userHistory);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().write("위치 정보가 성공적으로 저장되었습니다.");
    }
}
