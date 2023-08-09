package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE USERID = #{userId}")
    List<CredentialModel> getAllByUserId(Integer userId);

    @Select("SELECT * FROM CREDENTIALS WHERE CREDENTIALID = #{id} AND USERID = #{userId}")
    CredentialModel getById(Integer id, Integer userId);

    @Insert("INSERT INTO CREDENTIALS (URL, USERNAME, KEY, PASSWORD, USERID) VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(CredentialModel credentials);

    @Update("UPDATE CREDENTIALS SET URL = #{url}, USERNAME = #{username}, PASSWORD = #{password} WHERE CREDENTIALID = #{credentialId}")
    int update(CredentialModel credentials);

    @Delete("DELETE FROM CREDENTIALS WHERE CREDENTIALID = #{id}")
    int deleteById(Integer id);

    @Select("SELECT COUNT(*) FROM CREDENTIALS WHERE URL = #{url} and USERNAME = #{username}")
    int existsByUrl(String url, String username);
}
