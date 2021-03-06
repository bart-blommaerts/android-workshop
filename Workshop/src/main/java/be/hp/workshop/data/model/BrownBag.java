package be.hp.workshop.data.model;

/**
 * Created by bart on 11/20/13.
 */
public class BrownBag {
    private String id;
    private String title;
    private String content;
    private Integer imageId;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public BrownBag(String id, String title, String content, Integer imageId, String imageUrl) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
