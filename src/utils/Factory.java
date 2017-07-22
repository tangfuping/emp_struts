package utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * 工厂模式 ：建立接口和实现类对应之间的关系
 * 
 * @author tang
 * 
 */
public class Factory {
	private static Properties props = new Properties();
	static {
		InputStream ips = null;
		/**
		 * 反射机制，对于本类的对象拿到类加载器ClassLoader
		 */

		try {
			ClassLoader loader = Factory.class.getClassLoader();
			ips = loader.getResourceAsStream("utils/dao.properties");
			// 加载到文件夹中
			props.load(ips);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("文件加载失败");
		}
	}

	// 获取文件内容
	public static String getValue(String key) {
		return props.getProperty(key);
	}

	// 初始化工厂类的方法
	public static Object getInstance(String type) {
		Object obj = null;
		try {
			String classname = getValue(type);
			Class c = Class.forName(classname);
			obj = c.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

}
