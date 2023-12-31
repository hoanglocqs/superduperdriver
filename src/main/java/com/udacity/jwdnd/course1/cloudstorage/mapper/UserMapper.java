package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE USERNAME = #{username}")
    User getUser(String username);

    @Insert("INSERT INTO USERS (USERNAME, SALT, PASSWORD, FIRSTNAME, LASTNAME) VALUES (#{userName}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    @Select("SELECT COUNT(*) FROM USERS WHERE USERNAME = #{userName}")
    int existsUsername(String username);
}
