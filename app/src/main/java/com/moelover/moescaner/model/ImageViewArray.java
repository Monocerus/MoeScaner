package com.moelover.moescaner.model;

import java.util.ArrayList;

/**
 * Created by tianle on 2016/12/14.
 */

public class ImageViewArray {

   private ArrayList<ImageModelYande> images;

    public ImageViewArray() {
        images = new ArrayList<>();
    }


    public ArrayList<ImageModelYande> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageModelYande> images) {
        this.images = images;
    }

    public void addImages(ArrayList<ImageModelYande> images) {
        this.images.addAll(images);
    }

}
