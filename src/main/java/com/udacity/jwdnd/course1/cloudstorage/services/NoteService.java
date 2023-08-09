package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private NoteMapper notesMapper;
    public NoteService(NoteMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public List<NoteModel> findAll(Integer userId) {
        List<NoteModel> notes = notesMapper.getAllByUser(userId);
        return notes;
    }
    @Transactional
    public int save(NoteModel noteModel) {
        if (!ObjectUtils.isEmpty(noteModel.getNoteId())) {
            NoteModel note = notesMapper.getNoteById(noteModel.getNoteId(),noteModel.getUserId());
            note.setNoteTitle(noteModel.getNoteTitle());
            note.setNoteDescription(noteModel.getNoteDescription());
            return notesMapper.update(note);
        }
        noteModel.setUserId(noteModel.getUserId());
        return notesMapper.insert(noteModel);
    }
    @Transactional
    public int deleteNote(Integer noteId) {
        return notesMapper.deleteById(noteId);
    }
}
