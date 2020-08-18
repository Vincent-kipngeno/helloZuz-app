package com.moringaschool.hellozuz.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.moringaschool.hellozuz.Constants;
import com.moringaschool.hellozuz.R;
import com.moringaschool.hellozuz.adapter.PhotoCollectionsAdapter;
import com.moringaschool.hellozuz.models.FlickrPhotosSearchApiResponse;
import com.moringaschool.hellozuz.models.Photo;
import com.moringaschool.hellozuz.network.FlickrApi;
import com.moringaschool.hellozuz.network.FlickrClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.moringaschool.hellozuz.Constants.FLICKR_API_KEY;

public class PhotoCollectionsActivity extends AppCompatActivity {
    public static final String TAG = PhotoCollectionsActivity.class.getSimpleName();
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.main_page_toolbar) Toolbar mToolbar;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private PhotoCollectionsAdapter photoCollectionsAdapter;
    private SharedPreferences mSharedPreferences;
    private String mSearchPhoto;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_collections);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("HelloZuz");

        setSearchMessage();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSearchPhoto = mSharedPreferences.getString(Constants.PREFERENCE_SEARCH_KEY, null);
        if (mSearchPhoto != null) {
            hideErrorMessage();
            getPhotos(mSearchPhoto);
        }

    }

    private void setSearchMessage() {
        mErrorTextView.setText("Enter the photo text you of the photos you would like to have a look at.");
        mErrorTextView.setVisibility(View.VISIBLE);
    }
    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    private void showPhotos() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mErrorTextView.setVisibility(View.GONE);
    }

    private void hideErrorMessage() {
        mErrorTextView.setVisibility(View.GONE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                addToSharedPreferences(s);
                getPhotos(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                getPhotos(s);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }




    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCE_SEARCH_KEY, location).apply();
    }

    private void getPhotos(String term) {

        showProgressBar();
        FlickrApi client = FlickrClient.getClient();

        Call<FlickrPhotosSearchApiResponse> call = client.getPhotos("flickr.photos.search", FLICKR_API_KEY, term, "json","1");

        call.enqueue(new Callback<FlickrPhotosSearchApiResponse>() {
            @Override
            public void onResponse(Call<FlickrPhotosSearchApiResponse> call, Response<FlickrPhotosSearchApiResponse> response) {
                hideProgressBar();

                if (response.isSuccessful()){
                    List<Photo> photos = response.body().getPhotos().getPhoto();
                    Log.d(TAG, String.format("%d", photos.size()));
                    photoCollectionsAdapter = new PhotoCollectionsAdapter(PhotoCollectionsActivity.this, photos);
                    mRecyclerView.setAdapter(photoCollectionsAdapter);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(PhotoCollectionsActivity.this, 3);
                    mRecyclerView.setLayoutManager(layoutManager);

                    showPhotos();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<FlickrPhotosSearchApiResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
                hideProgressBar();
                showFailureMessage();
            }
        });
    }
}