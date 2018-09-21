package com.together.mybatis;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * 对于properties的优先级定义如下:
 * If a property exists in more than one of these places, MyBatis loads them in the following order 
 * • Properties specified in the body of the properties element are read first, 
 * • Properties loaded from the classpath resource or url attributes of the properties element are 
 *     read second, and override any duplicate properties already specified ,
 * • Properties passed as a method parameter are read last, and override any duplicate properties
 *
 * that may have been loaded from the properties body and the resource/url attributes.   
 * Thus, the highest priority properties are those passed in as a method parameter, followed by 
 * resource/url  attributes and finally the properties specified in the body of the properties element.
 *
 *
 * 对于mybatis local cache和second level cache的区分请参考:
 * http://moi.vonos.net/java/mybatis-caching/
 * @author jiangjian
 */

public class Main {

    public static void main(String[] args) throws IOException {
        String resource = "SqlMapConfig.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        Properties properties = new Properties();
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, properties);


        testCache(sqlSessionFactory);

        testDynamicSQL(sqlSessionFactory);

       testOpenSession(sqlSessionFactory);
    }

    private static void testOpenSession(SqlSessionFactory sqlSessionFactory) {
        System.out.println("\n\n--------------if test --------------------");
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        List<Blog> blogs = blogMapper.selectByUsername("xxxxx");
        System.out.println("before add size: " + blogs.size());

        blogMapper.save(new Blog("xxxxx"));

        blogs = blogMapper.selectByUsername("xxxxx");
        System.out.println("after add size: " + blogs.size());
    }

    private static void testDynamicSQL(SqlSessionFactory sqlSessionFactory) {
        System.out.println("\n\n--------------if test --------------------");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog1 = blogMapper.selectBlogByIdAndAuthorName(1, "xxx");
        System.out.println(blog1);
        sqlSession.commit();

        Blog blog2 = blogMapper.selectBlogByIdAndAuthorName(1, null);
        System.out.println(blog2);
        System.out.println("blog1 == blog2 ? " + (blog1 == blog2));
        sqlSession.commit();

        System.out.println("\n\n----------------choose test------------------");
        Blog blog3 = blogMapper.selectBlogByIdAndAuthor(1, new Author(null, "xx"));
        System.out.println(blog3);
        sqlSession.commit();

        System.out.println("\n\n----------------where test------------------");
        Blog blog4 = blogMapper.SelectBlogByAuthor(new Author(null, "xxx"));
        System.out.println(blog4);
        sqlSession.commit();

        System.out.println("\n\n----------------set test (in update statement)------------------");
        System.out.println("before update: " + blog2);
        Blog blog5 = new Blog();
        blog5.setId(1);
        blog5.setUsername("tom");
        blog5.setStatus(new Random().nextInt(1000));
        blogMapper.update(blog5);

        Blog updatedBlog = blogMapper.selectBlogById(1);
        System.out.println("after update: " + updatedBlog);
        sqlSession.commit();

        System.out.println("\n\n----------------foreach test------------------");
        List<Blog> blogList = blogMapper.selectByIds(Arrays.asList(1, 2));
        System.out.println(">>>size:" + blogList.size());
        blogList.stream().forEach(System.out::println);
        sqlSession.commit();

        sqlSession.close();
    }

    private static void testCache(SqlSessionFactory sqlSessionFactory) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectBlogById(1);
        System.out.println(blog);
        sqlSession.commit();
        sqlSession.close();

        System.out.println("\n\n----------------------------------");
        sqlSession = sqlSessionFactory.openSession();
        blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog1 = blogMapper.selectBlogById(1);
        System.out.println(blog1);
        System.out.println("same instance :" + (blog == blog1));
        sqlSession.commit();
        sqlSession.close();

        System.out.println("\n\n----------------------------------");
        sqlSession = sqlSessionFactory.openSession();
        blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.save(new Blog("alice"));
        sqlSession.commit();
        sqlSession.close();


        System.out.println("\n\n----------------------------------");
        sqlSession = sqlSessionFactory.openSession();
        blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog2 = blogMapper.selectBlogById(1);
        System.out.println(blog2);
        System.out.println("same instance blog == blog2 :" + (blog == blog2));
        sqlSession.commit();
        sqlSession.close();

        System.out.println("\n\n----------------------------------");
        sqlSession = sqlSessionFactory.openSession();
        blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog3 = blogMapper.selectBlogById(2);
        System.out.println(blog3);
        sqlSession.commit();
        sqlSession.close();
    }
}
