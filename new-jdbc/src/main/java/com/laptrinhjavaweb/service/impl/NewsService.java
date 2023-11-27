package com.laptrinhjavaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.service.INewsService;

public class NewsService implements INewsService {

	@Inject
	private INewsDAO newsDao;

	@Override
	public List<NewsModel> findByCategoryID(Long categoryId) {
		return newsDao.findByCategoryID(categoryId);
	}

	@Override
	public NewsModel save(NewsModel newsModel) {
		Long newId = newsDao.save(newsModel);
		return newsDao.findOne(newId);
	}

	@Override
	public NewsModel update(NewsModel updateNews) {
		NewsModel oldNews = newsDao.findOne(updateNews.getId());
		updateNews.setCreatedBy(oldNews.getCreatedBy());
		updateNews.setCreatedDate(oldNews.getCreatedDate());
		newsDao.update(updateNews);
		return newsDao.findOne(updateNews.getId());
	}

}
