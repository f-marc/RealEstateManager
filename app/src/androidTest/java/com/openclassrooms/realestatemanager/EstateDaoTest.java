package com.openclassrooms.realestatemanager;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.openclassrooms.realestatemanager.data.model.Estate;
import com.openclassrooms.realestatemanager.data.database.RealEstateDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class EstateDaoTest {

    // FOR DATA
    private RealEstateDatabase database;

    // DATA SET FOR TEST
    private static String USER_IMAGE = "https://www.google.fr";
    private static Estate ESTATE_DEMO = new Estate(USER_IMAGE, "Maison", 1000, "Paris");


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                RealEstateDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @Test
    public void insertAndGetUser() throws InterruptedException {
        // BEFORE : Adding a new user
        this.database.estateDao().createEstate(ESTATE_DEMO);
        // TEST
        Estate estate = LiveDataTestUtil.getValue(this.database.estateDao().getEstates());
        Log.i("testcrash", "" + ESTATE_DEMO.getId());
        Log.i("testcrash", estate.getImage());
        Log.i("testcrash2", ESTATE_DEMO.getImage());
        assertTrue(estate.getImage().equals(ESTATE_DEMO.getImage()) && estate.getImage().equals(USER_IMAGE));

    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }
}
