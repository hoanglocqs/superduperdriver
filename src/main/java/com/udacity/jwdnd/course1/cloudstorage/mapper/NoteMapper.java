package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE userid = #{userId} ORDER BY noteid DESC")
    List<NoteModel> getAllByUser(Integer userId);

    @Select("SELECT * FROM NOTES WHERE noteid = #{id} and userid = #{userId}")
    NoteModel getNoteById(Integer id, Integer userId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) values (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(NoteModel notes);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    int update(NoteModel notes);

    @Delete("DELETE FROM NOTES WHERE noteid = #{id}")
    int deleteById(Integer id);
}
