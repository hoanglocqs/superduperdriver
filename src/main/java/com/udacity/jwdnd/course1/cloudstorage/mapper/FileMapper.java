package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE USERID = #{userId} ORDER BY FILEID DESC")
    List<FileModel> getAllByUserId(Integer userId);
    @Insert("INSERT INTO FILES (USERID, FILENAME, CONTENTTYPE, FILESIZE, FILEDATA) VALUES (#{userId}, #{fileName}, #{contentType}, #{fileSize}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(FileModel files);
    @Delete("DELETE FROM FILES WHERE FILEID = #{id}")
    int deleteById(Integer id);

    @Select("select * from FILES where fileId = #{fileId}")
    FileModel getFileById(Integer fileId);

    @Select("SELECT COUNT(*) FROM FILES WHERE userId = #{userId} AND fileName = #{fileName}")
    int getFileByFileName(String fileName,Integer userId);
}
