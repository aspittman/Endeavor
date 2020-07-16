package com.affinityapps.endeavor.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FormRepository {
    private FormDao formDao;
    private LiveData<List<Form>> allForms;

    public FormRepository(Application application) {
        FormDatabase formDatabase = FormDatabase.getInstance(application);
        formDao = formDatabase.formDao();
        allForms = formDao.getAllForms();
    }


    public void insert(Form form) {
        new InsertFormAsyncTask(formDao).execute(form);
    }

    public void update(Form form) {
        new UpdateFormAsyncTask(formDao).execute(form);
    }

    public void delete(Form form) {
        new DeleteFormAsyncTask(formDao).execute(form);
    }

    public void deleteAllForms() {
        new DeleteAllFormsAsyncTask(formDao).execute();
    }

    public LiveData<List<Form>> getAllForms() {
        return allForms;
    }


    public static class InsertFormAsyncTask extends AsyncTask<Form, Void, Void> {
        private FormDao formDao;

        private InsertFormAsyncTask(FormDao formDao) {
            this.formDao = formDao;
        }

        @Override
        protected Void doInBackground(Form... forms) {
            formDao.insert(forms[0]);
            return null;
        }
    }

    public static class UpdateFormAsyncTask extends AsyncTask<Form, Void, Void> {
        private FormDao formDao;

        private UpdateFormAsyncTask(FormDao formDao) {
            this.formDao = formDao;
        }

        @Override
        protected Void doInBackground(Form... forms) {
            formDao.update(forms[0]);
            return null;
        }
    }

    public static class DeleteFormAsyncTask extends AsyncTask<Form, Void, Void> {
        private FormDao formDao;

        private DeleteFormAsyncTask(FormDao formDao) {
            this.formDao = formDao;
        }

        @Override
        protected Void doInBackground(Form... forms) {
            formDao.delete(forms[0]);
            return null;
        }
    }

    public static class DeleteAllFormsAsyncTask extends AsyncTask<Void, Void, Void> {
        private FormDao formDao;

        private DeleteAllFormsAsyncTask(FormDao formDao) {
            this.formDao = formDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            formDao.deleteAllForms();
            return null;
        }
    }
}
