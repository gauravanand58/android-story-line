package com.wattpad.storyline.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.wattpad.storyline.database.StoryRoomDatabase;
import com.wattpad.storyline.data.IStoryDao;
import com.wattpad.storyline.data.IStoryRemoteSource;
import com.wattpad.storyline.data.StoryRemoteSource;
import com.wattpad.storyline.model.Story;
import com.wattpad.storyline.model.StoryPage;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class StoryRepository implements IStoryRepository {
    private static StoryRepository sInstance;

    private Context mContext;
    private IStoryRemoteSource mStoryRemoteSource;
    private IStoryDao mStoryDao;
    private MutableLiveData<List<Story>> mStoryData;

    private StoryRepository(Application application) {
        StoryRoomDatabase database = StoryRoomDatabase.getDatabase(application);
        mStoryDao = database.storyDao();
        mContext = application.getApplicationContext();
        mStoryData = new MutableLiveData<>();
        mStoryRemoteSource = new StoryRemoteSource();
    }

    public static StoryRepository getInstance(Application application) {
        if (sInstance == null) {
            sInstance = new StoryRepository(application);
        }
        return sInstance;
    }

    public MutableLiveData<List<Story>> fetchDataFromDB() {
        Log.d("StoryLineLog", "Fetching stories from DB.");
        Maybe<List<Story>> listMaybe = mStoryDao.getStoryLine();
        listMaybe.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Story>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Story> stories) {
                        if (stories.size() == 0) {
                            Log.d("StoryLineLog", "Database empty. Fetching stories from web.");
                            fetchDataFromWeb();
                        } else {
                            Log.d("StoryLineLog", "Fetched " + stories.size() + " stories from DB.");
                            mStoryData.setValue(stories);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("StoryLineLog", "Error while fetching data from DB:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("StoryLineLog", "Data fetching from DB completed");
                    }
                });
        return mStoryData;
    }

    private void fetchDataFromWeb() {
        Maybe<StoryPage> listMaybe = mStoryRemoteSource.getStoryPageFromWeb();
        listMaybe.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<StoryPage>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(StoryPage storyPage) {
                        Log.d("StoryLineLog", storyPage.getStories().size() + " stories fetched from web");
                        mStoryData.setValue(storyPage.getStories());
                        updateStoriesToDB(storyPage.getStories());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("StoryLineLog", "Error while fetching data from web: " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("StoryLineLog", "Data fetching from web completed");
                    }
                });
    }

    private void updateStoriesToDB(List<Story> stories) {
        Completable insertCompletable = Completable.fromAction(() -> mStoryDao.insertAllStories(stories));
        Completable deleteCompletable = Completable.fromAction(() -> mStoryDao.deleteAll());

        deleteCompletable.andThen(Completable.fromAction(() -> Log.d("StoryLineLog", "Deleted old stories from DB")))
                .andThen(insertCompletable)
                .andThen(Completable.fromAction(() -> Log.d("StoryLineLog", "Saved new stories to DB")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(mContext, "Stories saved to view in offline mode.", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("StoryLineLog", "Error while updating data " + e.toString());
                    }
                });
    }
}