package favorite;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yc.dao.FavoriteMpper2;
import com.yc.dao.TagMpper;
import com.yc.favorite.bean.Favorite;
import com.yc.favorite.bean.FavoriteBiz;
import com.yc.favorite.bean.Tag;
import com.yc.util.MyBatisHelper;

public class BaseTest {
	@Test
	public void Test1() {
		
		SqlSession session=MyBatisHelper.openSession();
		
		FavoriteMpper2 fm=session.getMapper(FavoriteMpper2.class);
		Favorite f=new Favorite();
		f.setFlabel("淘宝");
		f.setFurl("taobao.com");
		f.setFdesc("居家网站");
		f.setFtags("购物,生活");
		
		TagMpper tm=session.getMapper(TagMpper.class);
		Tag t=new Tag();
		t.setTcount(1);
		t.setTname("生活");
		tm.insert(t);
		
		fm.insert(f);
		session.commit();
		session.close();
		
		
	}
	@Test
	public void Test2() {
		FavoriteBiz fb=new FavoriteBiz();
		Favorite f=new Favorite();
		f.setFlabel("淘宝");
		f.setFurl("taobao.com");
		f.setFdesc("居家网站");
		f.setFtags("购物,生活");
		fb.addFavorite(f);
	}
	
	@Test
	public void Test3() {
		FavoriteBiz fb=new FavoriteBiz();
		Favorite f=new Favorite();
		f.setFlabel("网易");
		f.setFurl("123.com");
		f.setFdesc("号网站");
		f.setFtags("购物,生活,门户");
		fb.addFavorite(f);
	}
	
	@Test
	public void Test4() {

		SqlSession session=MyBatisHelper.openSession();
		
		FavoriteMpper2 fm=session.getMapper(FavoriteMpper2.class);
		fm.selectByTid(null);
		fm.selectByTid(1);
		fm.selectByTid(0);
	}
	
}
