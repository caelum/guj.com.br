package br.com.guj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jforum.SessionFacade;
import net.jforum.entities.UserSession;
import net.jforum.util.preferences.ConfigKeys;

import org.vraptor.VRaptorServlet;

import br.com.guj.feeds.FeedReader;
import de.nava.informa.core.ItemIF;

public class GUJServlet extends VRaptorServlet {
    private List<ItemIF> infoq = new ArrayList<ItemIF>();
    private List<ItemIF> news = new ArrayList<ItemIF>();
    private List<ItemIF> forum = new ArrayList<ItemIF>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Config.loadConfigs();

        this.startInfoq();
        this.startNews();
        this.startForum();
    }

    private void startForum() {
        long interval = Config.getIntvalue("forum.refresh.interval") * 1000 * 60;

        Timer infoqTimer = new Timer(true);
        infoqTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	try {
	                forum = new ArrayList<ItemIF>(FeedReader.read(Config.getValue("forum.url")));

	                int max = Config.getIntvalue("forum.items");

	                if (forum.size() > max) {
	                    forum = forum.subList(0, max);
	                }
            	}
            	catch (Exception e) {
            		e.printStackTrace();
            	}
            }
        }, new Date(), interval);
    }

    private void startNews() {
        long interval = Config.getIntvalue("news.refresh.interval") * 1000 * 60;

        Timer infoqTimer = new Timer(true);
        infoqTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	try {
	                news = new ArrayList<ItemIF>(FeedReader.read(Config.getValue("news.url")));

	                int max = Config.getIntvalue("news.items");

	                if (news.size() > max) {
	                    news = news.subList(0, max);
	                }
            	}
            	catch (Exception e) {
            		e.printStackTrace();
            	}
            }
        }, new Date(), interval);
    }

    private void startInfoq() {
        long infoqInterval = Config.getIntvalue("infoq.refresh.interval") * 1000 * 60;

        Timer infoqTimer = new Timer(true);
        infoqTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	try {
	                infoq = new ArrayList<ItemIF>(FeedReader.read(Config.getValue("infoq.url")));

	                int max = Config.getIntvalue("infoq.items");

	                if (infoq.size() > max) {
	                    infoq = infoq.subList(0, max);
	                }
            	}
            	catch (Exception e) {
            		e.printStackTrace();
            	}
            }
        }, new Date(), infoqInterval);
    }

    /**
     * @see org.vraptor.VRaptorServlet#service(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("infoq", this.infoq);
        request.setAttribute("news", this.news);
        request.setAttribute("forum", this.forum);

        boolean isLogged = "1".equals(request.getSession().getAttribute(ConfigKeys.LOGGED));
        request.setAttribute("logged", isLogged);

        if (isLogged) {
            UserSession userSession = SessionFacade.getUserSession(request.getSession().getId());
            request.setAttribute("userSession", userSession);
        }

        super.service(request, response);
    }
}
