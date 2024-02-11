package com.example.seoulwifiexplorer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/fetchWifiInfo")
public class FetchWifiInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ApiStatusChecker checker = new ApiStatusChecker();

        if (!checker.isApiOperational()) {
            request.setAttribute("error", "현재 Open API 와이파이 서비스 이용이 불가능합니다. 잠시 후 다시 시도해주세요.");
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            return;
        }


        WifiDataFetcher fetcher = new WifiDataFetcher();
        try {
            int totalCount = fetcher.getTotalCount();

            if(totalCount > 0 ) {
                int totalFetched = fetcher.fetchAndStoreWifiSpots(totalCount);
                request.setAttribute("message", totalFetched + "개의 WIFI 정보를 정상적으로 저장하였습니다.");
            } else{
                request.setAttribute("message", "저장할 WIFI 정보가 없습니다");
            }
            request.getRequestDispatcher("/fetchWifiResult.jsp").forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "WIFI 정보를 가져오는데 실패하였습니다.");
        }
    }
}
