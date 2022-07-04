package com.dzo.notesapkusingmvvm;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepo {

    private NoteDoa noteDao;
    private LiveData<List<Note>> notelist;

    public NoteRepo(Application application)
    {
        NoteDatabase noteDatabase=NoteDatabase.getInstance(application);
        noteDao=noteDatabase.noteDao();
        notelist=noteDao.getAllData();

    }
    public void insertData(Note note){new InsertTask(noteDao).execute(note);}
    public void updateData(Note note){new UpdateTask(noteDao).execute(note);}
    public void deleteData(Note note){new DeleteTask(noteDao).execute(note);}
    public LiveData<List<Note>> getAllData()
    {
        return notelist;
    }

    private static class InsertTask extends AsyncTask<Note,Void,Void>{
        private NoteDoa noteDao;

        public InsertTask(NoteDoa noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }private static class DeleteTask extends AsyncTask<Note,Void,Void>{
        private NoteDoa noteDao;

        public DeleteTask(NoteDoa noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }private static class UpdateTask extends AsyncTask<Note,Void,Void>{
        private NoteDoa noteDao;

        public UpdateTask(NoteDoa noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

}
