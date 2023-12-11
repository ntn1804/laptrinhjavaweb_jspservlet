package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.PageAble;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin-news" })
public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 2686801510274002166L;

	@Inject
	private INewsService newsService;
	
	@Inject
	private ICategoryService categoryService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = "";
		NewsModel model = FormUtil.toModel(NewsModel.class, request);
		if (model.getType().equals(SystemConstant.LIST)) {
			PageAble pageAble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(newsService.findAll(pageAble));
			model.setTotalItem(newsService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			view = "/views/admin/news/list.jsp";
		} else if (model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = newsService.findOne(model.getId());
			}
			request.setAttribute("categories", categoryService.findAll());
			view = "/views/admin/news/edit.jsp";
		}
		if(request.getParameter("message") != null) {
			String messageResponse = "";
			String alert = "";
			String message = request.getParameter("message");
			if(message.equals("insert_success")) {
				messageResponse = "Insert success";
				alert = "success";
			} else if (message.equals("update_success")) {
				messageResponse = "Update success";
				alert = "success";
			} else if (message.equals("delete_success")) {
				messageResponse = "Delete success";
				alert = "success";
			} else if (message.equals("error_system")) {
				messageResponse = "Error system";
				alert = "danger";
			}
			request.setAttribute("messageResponse", messageResponse);
			request.setAttribute("alert", alert);
		}
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
