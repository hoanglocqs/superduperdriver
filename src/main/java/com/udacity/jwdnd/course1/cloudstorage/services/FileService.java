package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileService {
    private FileMapper filesMapper;
    public FileService(FileMapper filesMapper) {
        this.filesMapper = filesMapper;
    }

    public List<FileModel> findAll(Integer userId) {
        List<FileModel> files = filesMapper.getAllByUserId(userId);
        return files;
    }

    @Transactional
    public int save(FileModel fileModel) throws Exception {
        return filesMapper.insert(fileModel);
    }

    @Transactional
    public int deleteFile(Integer fileId) {
        return filesMapper.deleteById(fileId);
    }

    public FileModel getFileById(Integer fileId){
        return filesMapper.getFileById(fileId);
    }

    public boolean isExsisFile(String fileName, Integer userId) {
        int cnt = filesMapper.getFileByFileName(fileName,userId);
        if (cnt != 0){
            return false;
        }
        return true;
    }
}
