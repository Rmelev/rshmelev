package ru.job4j.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * First servlet.
 */
public class EchoServlet extends HttpServlet {
    /**
     * Logger.
     */
    public static final Logger LOG = LoggerFactory.getLogger(EchoServlet.class);
    /**
     * list of users.
     */
    private List<String> users = new CopyOnWriteArrayList<>();

    /**
     * doGet.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - exp.
     * @throws IOException - erxp.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder("<table>");
        for (String login : this.users) {
            sb.append("<tr><td>" + login + "  " + Math.random() + "</td></tr>");
        }
        sb.append("</table>");
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Title</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/echo' method='post'>"
                + "Name : <input type='text' name='login'/>"
                + "<input type='submit'>"
                + "<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' "
                + "codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,115,0' "
                + "width='550' height='412' type='application/x-shockwave-flash' id='video_player_1006191' "
                + "class='viboom-overroll'><param name='id' value='video_player_1006191'/><param name='movie' "
                + "value='http://www.yapfiles.ru/static/play.swf'/><param name='flashvars' value='st=vMDEwMDYxOTEt94ef'/>"
                + "<param name='allowScriptAccess' value='always'/><param name='allowfullscreen' value='true'/>"
                + "<param name='wmode' value='transparent'/><param name='quality' value='high'/>"
                + "<embed src='http://www.yapfiles.ru/static/play.swf' flashvars='st=vMDEwMDYxOTEt94ef' quality='high' "
                + "allowscriptaccess='always' allowfullscreen='true' width='550' height='412' wmode='transparent' "
                + "pluginspage='http://www.adobe.com/go/getflashplayer' type='application/x-shockwave-flash'/>"
                + "</object></form>"
                + "<br/>"
                + sb.toString()
                + "</body>"
                + "</html>");
        writer.flush();
    }

    /**
     * doPost.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - exp.
     * @throws IOException - exp.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        users.add(login);
        doGet(req, resp);
    }
}
