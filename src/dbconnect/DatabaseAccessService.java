package dbconnect;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnect.dao.FreshFishDao;
import dbconnect.domain.FreshFishes;

/**
 * Servlet implementation class ConnectionService
 */
@WebServlet("/DatabaseAccessService")
public class DatabaseAccessService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DatabaseAccessService() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		session.removeAttribute("Message");
		session.removeAttribute("ErrorMessage");
		session.removeAttribute("FreshFishes");

		String targetFishName = "いわし";
		int targetFishCode = 501;

		FreshFishDao fishDao = new FreshFishDao();
		FreshFishes fishes = new FreshFishes();
		try {
			fishes = fishDao.findByFishCodeOrFishName(targetFishName, targetFishCode);
			session.setAttribute("Message", "データの接続に成功しました.");
		} catch (SQLException e) {
			e.printStackTrace();
			session.setAttribute("ErrorMessage", "データベースへの問い合わせに失敗しました.");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("ErrorMessage", "データベースへの接続に失敗しました.");
		}
		session.setAttribute("FreshFishes", fishes);
		response.sendRedirect("index.jsp");
	}

}
