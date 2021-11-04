package demo.yang.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MapperUtils {
    private MapperUtils(){}

    private static SqlSessionFactory factory;

//    private static Map<Thread,SqlSession> map = new ConcurrentHashMap<>();

    //线程的域对象，可以保存某一个线程的私有数据
    //内部就是封装了一个HashMap，每个线程有自己独立的HashMap
    private static ThreadLocal<SqlSession> tl = new ThreadLocal<>();

    static {
        //加载核心配置文件(创建流对象/类加载器)
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("SqlMapConfig.xml");
            //解析这个配置文件，获取SqlSessionFactory工厂对象
            factory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("初始化SqlSessionFactory失败");
        }
    }

    public static SqlSession openSession(){
        return openSession(false);
    }
    public static SqlSession openSession(boolean autoCommit){

        SqlSession sqlSession = factory.openSession(autoCommit);
        //将其和当前线程绑定
        tl.set(sqlSession);
        return sqlSession;
    }

    public static <T> T getMapper(Class<T> cls){
        //获取SqlSession对象
        SqlSession sqlSession = openSession();
        //获取接口代理对象
        return sqlSession.getMapper(cls);
    }

    public static void commit(){
        //从map集合中获取和当前线程绑定的sqlsession对象
        SqlSession sqlSession = tl.get();
        if (sqlSession != null){
            sqlSession.commit();
        }
    }

    public static void rollback(){
        //从map集合中获取和当前线程绑定的sqlsession对象
        SqlSession sqlSession = tl.get();
        if (sqlSession != null){
            sqlSession.rollback();
        }
    }

    public static void close(){
        //从map集合中获取和当前线程绑定的sqlsession对象
        SqlSession sqlSession = tl.get();
        if (sqlSession != null){
            sqlSession.close();
            //和当前线程解绑
            tl.remove();
        }
    }
}
