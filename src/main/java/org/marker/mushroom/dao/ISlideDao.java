package org.marker.mushroom.dao;

import org.marker.mushroom.beans.Article;

import java.util.List;
import java.util.Map;

public interface ISlideDao extends ISupportDao{
	
	public boolean update(Article entity);

	public List<Map<String,Object>> findAll() ;

}
