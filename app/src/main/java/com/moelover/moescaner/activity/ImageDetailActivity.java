/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moelover.moescaner.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.moelover.moescaner.ApplicationController;
import com.moelover.moescaner.HackyViewPager;
import com.moelover.moescaner.R;
import com.moelover.moescaner.fragment.ImageDetailFragment;
import com.moelover.moescaner.model.ImageViewArray;


public class ImageDetailActivity extends AppCompatActivity{
    public static final String EXTRA_IMAGE_POSITION = "extra_image";

    private ImagePagerAdapter mAdapter;
    private HackyViewPager mPager;
    private ImageViewArray imageViewArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_pager);
        imageViewArray = ApplicationController.getInstance().getImageViewArray();
        mAdapter = new ImagePagerAdapter(getFragmentManager(), imageViewArray.getImages().size());
        mPager = (HackyViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        mPager.setPageMargin((int) getResources().getDimension(R.dimen.horizontal_page_margin));
        mPager.setOffscreenPageLimit(1);

        // Set up activity to go full screen
        //getWindow().addFlags(LayoutParams.FLAG_FULLSCREEN);

        // Set the current item based on the extra passed in to this activity
        final int extraCurrentItem = getIntent().getIntExtra(EXTRA_IMAGE_POSITION, -1);
        if (extraCurrentItem != -1) {
            mPager.setCurrentItem(extraCurrentItem);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private class ImagePagerAdapter extends FragmentStatePagerAdapter {
        private final int mSize;

        ImagePagerAdapter(FragmentManager fm, int size) {
            super(fm);
            mSize = size;
        }

        @Override
        public int getCount() {
            return mSize;
        }

        @Override
        public Fragment getItem(int position) {
            return ImageDetailFragment.newInstance(imageViewArray.getImages().get(position).getPreview_url(),
                    imageViewArray.getImages().get(position).getFile_url());
        }
    }


}
