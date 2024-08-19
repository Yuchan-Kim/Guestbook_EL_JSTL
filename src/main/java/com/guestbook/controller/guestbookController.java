package com.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guestbook.dao.guestbookDao;
import com.guestbook.vo.guestbookVo;

/**
 * Servlet implementation class guestbookController
 */
@WebServlet("/guests")
public class guestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//action
		String action = request.getParameter("action");
		System.out.println(action);
		
		guestbookDao guestbookDao = new guestbookDao();
		
		if("register".equals(action)) {
			
			System.out.println("등록 프로세스");
			
			String name = request.getParameter("name");
			String pw = request.getParameter("pw");
			String comments = request.getParameter("comments");
			
			guestbookVo guest = new guestbookVo(name,pw,comments);
			guestbookDao.registerInfo(guest);
			response.sendRedirect("/GuestBook_ElJstl/guests?action=addPeople2");
			
		}else if("addPeople2".equals(action)) {
			
			List<guestbookVo> guestList = guestbookDao.getPersonList();
			
			request.setAttribute("guestList", guestList);
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addPeople.jsp");
			rd.forward(request, response);
			
		}else if("delete".equals(action)) { //delete form으로 가는 과정 
			
			int num = Integer.parseInt(request.getParameter("num"));
			guestbookVo guest =guestbookDao.getPersonInfo(num);
			
			request.setAttribute("guest", guest);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteInfo.jsp");
			rd.forward(request, response);
			
		}else if("deleteInfo".equals(action)) { //비밀 번호 체크후 지우는 과정
			
			int no = Integer.parseInt(request.getParameter("num"));
			String pw = request.getParameter("pw");
			
			if (guestbookDao.checkPw(no, pw)) {
				guestbookDao.deletePerson(no);
			}else {
				System.out.println("비밀번호 불일치.");
			}
			
			response.sendRedirect("/GuestBook_ElJstl/guests?action=addPeople2");
			
		}

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
