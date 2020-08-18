package com.moringaschool.hellozuz.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.moringaschool.hellozuz.R;
import com.moringaschool.hellozuz.adapter.PhotoPagerAdapter;
import com.moringaschool.hellozuz.models.Photo;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoDetailsActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private PhotoPagerAdapter adapterViewPager;
    List<Photo> mPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);
        ButterKnife.bind(this);

        mPhotos = Parcels.unwrap(getIntent().getParcelableExtra("photos"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new PhotoPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mPhotos);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}