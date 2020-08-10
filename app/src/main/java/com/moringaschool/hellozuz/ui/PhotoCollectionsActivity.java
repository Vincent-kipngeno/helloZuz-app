package com.moringaschool.hellozuz.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class PhotoCollectionsActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = PhotoCollectionsActivity.class.getSimpleName();
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.photoSearchText) EditText mPhotoSearchText;
    @BindView(R.id.searchButton) Button mSearchButton;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private PhotoCollectionsAdapter photoCollectionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_collections);
        ButterKnife.bind(this);

        setSearchMessage();
        mSearchButton.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        if (view == mSearchButton){
            showProgressBar();
            String term = mPhotoSearchText.getText().toString();

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
}