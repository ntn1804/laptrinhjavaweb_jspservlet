package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;

public interface INewsService {
	List<NewsModel> findByCategoryID(Long categoryId);

	NewsModel save(NewsModel newsModel);

	NewsModel update(NewsModel updateNews);
}
