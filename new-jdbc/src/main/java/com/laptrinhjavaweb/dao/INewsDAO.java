package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.PageAble;

public interface INewsDAO extends GenericDAO<NewsModel> {
	NewsModel findOne(Long id);

	List<NewsModel> findByCategoryID(Long categoryId);

	Long save(NewsModel newsModel);

	void update(NewsModel updateNews);
	
	void delete(long id);
	
	List<NewsModel> findAll(PageAble pageAble);
	
	int getTotalItem();
}
