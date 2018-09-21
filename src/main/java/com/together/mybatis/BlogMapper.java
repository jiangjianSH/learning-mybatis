package com.together.mybatis;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiangjian
 */
public interface BlogMapper {
    Blog selectBlogById(int id);

    Blog selectBlogByIdAndAuthorName(@Param("id") int id,
                                     @Param("authorName") String authorName);

    Blog selectBlogByIdAndAuthor(@Param("id") int id, @Param("author") Author author);

    Blog SelectBlogByAuthor(Author author);

    void save(Blog blog);

    void update(Blog blog);

    List<Blog> selectByIds(@Param("ids") List<Integer> ids);

    List<Blog> selectByUsername(String username);

}
