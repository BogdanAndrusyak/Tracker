package ru.parsentev.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO: comment.
 * Created by Bogdan on 8/8/2016.
 */
public class JsonController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        PrintWriter append = writer.append("{\"countries\":[" +
                "{\"countryName\":\"Ukraine\"}, " +
                "{\"countryName\":\"Russia\"}" +
                "], " +
                "\"cities\":[" +
                    "{\"countryName\":\"Ukraine\", \"citiesList\":[" +
                            "{\"cityName\":\"Kiev\"}, " +
                            "{\"cityName\":\"Lviv\"}, " +
                            "{\"cityName\":\"Kharkiv\"}, " +
                            "{\"cityName\":\"Odessa\"}, " +
                            "{\"cityName\":\"Ivano-Frankivsk\"}" +
                        "]" +
                    "}, " +
                    "{\"countryName\":\"Russia\", \"citiesList\":[" +
                            "{\"cityName\":\"Moscow\"}, " +
                            "{\"cityName\":\"Saint Petersburg\"}, " +
                            "{\"cityName\":\"Kazan\"}, " +
                            "{\"cityName\":\"Nizhny Novgorod\"}, " +
                            "{\"cityName\":\"Bryansk\"}" +
                        "]" +
                    "}" +
                "]}");
        writer.flush();
    }
}
