package com.yc.favorite.bean;
import org.apache.ibatis.session.SqlSession;

import com.yc.dao.FavoriteMpper2;
import com.yc.dao.TagFavoriteMpper2;
import com.yc.dao.TagMpper;
import com.yc.favorite.bean.Favorite;
import com.yc.favorite.bean.Tag;
import com.yc.favorite.bean.TagFavorite;
import com.yc.util.MyBatisHelper;

public class FavoriteBiz {
	
	public void addFavorite(Favorite f) {
		
		
		SqlSession session=MyBatisHelper.openSession();
		FavoriteMpper2 fm=session.getMapper(FavoriteMpper2.class);
		TagMpper tm=session.getMapper(TagMpper.class);
		TagFavoriteMpper2 tfm=session.getMapper(TagFavoriteMpper2.class);
		
		try {

			fm.insert(f);
			//拆分分类tags
			String []tags=f.getFtags().split("[,，；;]");
			for(String tag:tags) {
				Tag tagObj=new Tag();
				//直接修改分类数量
				if(tm.updateCount(tag)==0) {
					tagObj.setTname(tag);
					tm.insert(tagObj);
				}else {
					tagObj=tm.selectByName(tag);
				}
				//根据tid fid向中间表写入记录
				TagFavorite tf=new TagFavorite();
				tf.setFid(tagObj.getTid());
				tf.setTid(f.getFid());
				tfm.insert(tf);
				session.commit();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
