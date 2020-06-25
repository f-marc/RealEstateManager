package com.openclassrooms.realestatemanager.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.openclassrooms.realestatemanager.data.database.EstateDao;
import com.openclassrooms.realestatemanager.data.database.EstateDatabase;
import com.openclassrooms.realestatemanager.data.model.Estate;

import java.util.List;

public class EstateRepository {

    private EstateDao estateDao;
    private LiveData<Estate> estate;
    private LiveData<List<Estate>> allEstates;

    public EstateRepository(Application application) {
        EstateDatabase database = EstateDatabase.getInstance(application);
        estateDao = database.estateDao();
        allEstates = estateDao.getAllEstates();
    }

    public void insert(Estate estate) {
        new InsertEstateAsyncTask(estateDao).execute(estate);
    }

    public void update(Estate estate) {
        new UpdateEstateAsyncTask(estateDao).execute(estate);
    }

    public void delete(Estate estate) {
        new DeleteEstateAsyncTask(estateDao).execute(estate);
    }

    public void deleteAllEstates() {
        new DeleteAllEstatesAsyncTask(estateDao).execute();
    }

    public LiveData<Estate> getEstate(long id) { return this.estateDao.getEstate(id); }

    public LiveData<List<Estate>> getAllEstates() {
        return allEstates;
    }

    private static class InsertEstateAsyncTask extends AsyncTask<Estate, Void, Void> {
        private EstateDao estateDao;

        private InsertEstateAsyncTask(EstateDao estateDao) {
            this.estateDao = estateDao;
        }

        @Override
        protected Void doInBackground(Estate... estates) {
            estateDao.insert(estates[0]);
            return null;
        }
    }

    private static class UpdateEstateAsyncTask extends AsyncTask<Estate, Void, Void> {
        private EstateDao estateDao;

        private UpdateEstateAsyncTask(EstateDao estateDao) {
            this.estateDao = estateDao;
        }

        @Override
        protected Void doInBackground(Estate... estates) {
            estateDao.update(estates[0]);
            return null;
        }
    }
    private static class DeleteEstateAsyncTask extends AsyncTask<Estate, Void, Void> {
        private EstateDao estateDao;

        private DeleteEstateAsyncTask(EstateDao estateDao) {
            this.estateDao = estateDao;
        }

        @Override
        protected Void doInBackground(Estate... estates) {
            estateDao.delete(estates[0]);
            return null;
        }
    }

    private static class DeleteAllEstatesAsyncTask extends AsyncTask<Void, Void, Void> {
        private EstateDao estateDao;

        private DeleteAllEstatesAsyncTask(EstateDao estateDao) {
            this.estateDao = estateDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            estateDao.deleteAllEstates();
            return null;
        }
    }
}
