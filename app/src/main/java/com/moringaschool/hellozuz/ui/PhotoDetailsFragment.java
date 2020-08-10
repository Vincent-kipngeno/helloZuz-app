package com.moringaschool.hellozuz.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.hellozuz.R;
import com.moringaschool.hellozuz.models.Photo;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoDetailsFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.flickrPhotoImageView) ImageView mFlickrPhoto;
    @BindView(R.id.websiteTextView) TextView mWebsiteTextView;
    @BindView(R.id.savePhotoButton) Button mSavePhotoButton;

    private Photo mPhoto;

    public PhotoDetailsFragment() {

    }

    public static PhotoDetailsFragment newInstance(Photo photo) {
        PhotoDetailsFragment fragment = new PhotoDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("photo", Parcels.wrap(photo));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPhoto = Parcels.unwrap(getArguments().getParcelable("photo"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_photo_details, container, false);
        ButterKnife.bind(this, view);

        String photoUrl = "https://farm" + mPhoto.getFarm() +  ".staticflickr.com/" + mPhoto.getServer() + "/" + mPhoto.getId() + "_" + mPhoto.getSecret() + ".jpg";
        Picasso.get().load(photoUrl).into(mFlickrPhoto);

        mWebsiteTextView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
       if (view == mWebsiteTextView) {
           String flickrPhotoUrl = "https://www.flickr.com/photos/" + mPhoto.getOwner() + "/" + mPhoto.getId();
           Intent webIntent = new Intent(Intent.ACTION_VIEW,
                   Uri.parse(flickrPhotoUrl));
           startActivity(webIntent);
       }
    }
}