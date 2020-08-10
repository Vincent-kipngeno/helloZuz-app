package com.moringaschool.hellozuz.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.hellozuz.models.Photo;
import com.moringaschool.hellozuz.ui.PhotoDetailsFragment;

import java.util.List;

public class PhotoPagerAdapter extends FragmentPagerAdapter {
    private List<Photo> mPhotos;

    public PhotoPagerAdapter(FragmentManager fm, int behavior, List<Photo> photos) {
        super(fm, behavior);
        mPhotos = photos;
    }

    @Override
    public Fragment getItem(int position) {
        return PhotoDetailsFragment.newInstance(mPhotos.get(position));
    }

    @Override
    public int getCount() {
        return mPhotos.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPhotos.get(position).getTitle();
    }
}
