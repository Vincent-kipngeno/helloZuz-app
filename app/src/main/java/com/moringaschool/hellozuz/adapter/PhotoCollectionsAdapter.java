package com.moringaschool.hellozuz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.hellozuz.models.Photo;

import java.util.List;
import com.moringaschool.hellozuz.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoCollectionsAdapter extends RecyclerView.Adapter<PhotoCollectionsAdapter.PhotoViewHolder> {
    private List<Photo> mPhotos;
    private Context mContext;

    public PhotoCollectionsAdapter(Context context, List<Photo> photos) {
        mContext = context;
        mPhotos = photos;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_item, parent, false);
        PhotoViewHolder viewHolder = new PhotoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.bindRestaurant(mPhotos.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.flickrImageView) ImageView mFlickrImageView;
        private  Context mContext;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }

        public void bindRestaurant(Photo photo) {
            String photoUrl = "https://farm" + photo.getFarm() +  ".staticflickr.com/" + photo.getServer() + "/" + photo.getId() + "_" + photo.getSecret() + ".jpg";
            Picasso.get().load(photoUrl).into(mFlickrImageView);
        }
    }
}
