package org.example;

public class ThumbnailView extends ImageView {
    public ThumbnailView(ImageModel imageModel) {
        super(imageModel, new Perspective());
        // Thumbnail-specific rendering logic
    }
}
