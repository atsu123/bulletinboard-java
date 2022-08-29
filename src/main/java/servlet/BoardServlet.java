package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bulletinboard.AddCommentLogic;
import bulletinboard.Board;
import bulletinboard.FindCommentLogic;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //���X�i�[�N���X�Ɉړ�������
        request.setCharacterEncoding("UTF-8");

        // �����̃R�����g���m�F
        FindCommentLogic fcl = new FindCommentLogic();
        List<Board> list = fcl.executeFindComment();

        // �Z�b�V�����X�R�[�v�ɃR�����g���X�g��ۑ�
        HttpSession session = request.getSession();
        session.setAttribute("listAttribute", list);

        RequestDispatcher rd = request.getRequestDispatcher("/jsp/main.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // ���͂��ꂽ�l���擾
        String name = request.getParameter("name");
        String comment = request.getParameter("comment");

        //JavaBeans�Ɋi�[
        Board bo = new Board();
        bo.setName(name);
        bo.setComment(comment);

        // mysql�Ɋi�[
        AddCommentLogic acl = new AddCommentLogic();
        acl.executeAddComment(bo);

        // �����͂��ꂽ�R�����g�Ɗ����̃R�����g��mysql����擾
        FindCommentLogic fcl = new FindCommentLogic();
        List<Board> list = fcl.executeFindComment();

        // �Z�b�V�����X�R�[�v�ɃR�����g���X�g��ۑ�
        HttpSession session = request.getSession();
        session.setAttribute("listAttribute", list);

        RequestDispatcher rd = request.getRequestDispatcher("/jsp/main.jsp");
        rd.forward(request, response);
    }

}