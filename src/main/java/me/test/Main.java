/**
 * 
 */
package me.test;

import java.util.List;

import me.config.User;
import me.util.DBUtil;
import me.util.EntityOperation;
import me.util.ReflectHelper;

/**
 * @author BlackStone
 *
 */
public class Main {
	/** 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @ClassName: Main 
	 * @Description: TODO(这里用一句话描述这个类的作用) 
	 * @date 2017年12月21日 下午3:01:21 
	 * 
	 */
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
        EntityOperation ep=EntityOperation.getInstance();
        ep.setBasicConfig("isOutputMsg", "");
        
        //插入test
        User user=new User();
        user.setUserName("blackstone"+System.currentTimeMillis());
        user.setPassword("test");
        user.setRoleId("1");
        ep.add(user);
        //查找测试
        List<Object> list=ep.find(User.class,"user_name not like 'blackstone%'");
        
        for(int i=0;i<list.size();i++){
        	User userone=(User)list.get(i);
        	 //System.out.println(String.format("userNmae=%s password=%s", userone.getUserName(),userone.getPassword()));
        	
        }
       // System.out.println(list.size());
        
        //删除测试
        ep.del(User.class,"user_name like 'blackstone%'");
        
        //更新测试
        ep.update(User.class, "password='admin'", "user_name='971406187@qq.com'");
	
	}
}
