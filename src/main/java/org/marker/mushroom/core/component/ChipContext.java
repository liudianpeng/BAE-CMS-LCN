package org.marker.mushroom.core.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.marker.mushroom.core.IChip;
import org.marker.mushroom.core.SystemStatic;
import org.marker.mushroom.core.config.impl.DataBaseConfig;
import org.marker.mushroom.dao.ISupportDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 碎片
 * */
@Service(SystemStatic.SYSTEM_CMS_CHIP)
public class ChipContext implements IChip{

	private static final Logger log = LoggerFactory.getLogger(ChipContext.class);
	
	@Autowired ISupportDao commonDao;
	 
	private boolean isSyn = false;
	
	private HashMap<String, String> data;
	
	
	 
	public synchronized void syn(){
		if(!isSyn){
			String prefix = DataBaseConfig.getInstance().getPrefix();
			List<Map<String, Object>> list = commonDao.queryForList("select * from "+prefix+"chip");
			data = new HashMap<String, String>();
			for(Map<String, Object> o: list){
				String mark = o.get("mark").toString();
				data.put(mark, o.get("content").toString());
			}
			log.info("syn chip data ");
			isSyn = true;
		}
	}
 
	public Object getVector() {
		syn();
		return data;
	}
}


