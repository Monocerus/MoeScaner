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

package com.moelover.moescaner.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.moelover.moescaner.ApplicationController;
import com.moelover.moescaner.R;
import com.moelover.moescaner.download.ImageDownloadManager;
import com.nostra13.universalimageloader.core.ImageLoader;

import uk.co.senab.photoview.PhotoView;


public class ImageDetailFragment extends Fragment {
    private static final String IMAGE_DATA_EXTRA = "extra_image_data";
    private static final String FILE_DATA_EXTRA = "extra_file_data";
    private String mImageUrl;
    private String mFileUrl;
    private PhotoView mImageView;
    private ProgressBar mProgressBar;
    private Button mBtnDownload;

    /**
     * Factory method to generate a new instance of the fragment given an image number.
     *
     * @param imageUrl The image url to load
     * @return A new instance of ImageDetailFragment with imageNum extras
     */
    public static ImageDetailFragment newInstance(String imageUrl, String fileUrl) {
        final ImageDetailFragment f = new ImageDetailFragment();

        final Bundle args = new Bundle();
        args.putString(IMAGE_DATA_EXTRA, imageUrl);
        args.putString(FILE_DATA_EXTRA, fileUrl);
        f.setArguments(args);

        return f;
    }

    /**
     * Empty constructor as per the Fragment documentation
     */
    public ImageDetailFragment() {}

    /**
     * Populate image using a url from extras, use the convenience factory method
     * {@link ImageDetailFragment#newInstance(String,String)} to create this fragment.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments() != null ? getArguments().getString(IMAGE_DATA_EXTRA) : null;
        mFileUrl = getArguments() != null ? getArguments().getString(FILE_DATA_EXTRA) : null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate and locate the main ImageView
        final View v = inflater.inflate(R.layout.image_detail_fragment, container, false);
        mImageView = (PhotoView) v.findViewById(R.id.imageView);
        //mProgressBar = (ProgressBar) v.findViewById(R.id.progressbar);
        mBtnDownload = (Button) v.findViewById(R.id.btn_download);
        ImageLoader.getInstance().displayImage(mImageUrl,mImageView, ApplicationController.getInstance().getOptions2());
        mBtnDownload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageDownloadManager.getInstance().download(mFileUrl);
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
