package at.htlleonding.persistence.models;

import java.util.HashSet;
import java.util.Set;

public class TopicModel extends IdentityModel{
    private String keyword;
    private final Set<MediaItemModel> mediaItems = new HashSet<>();

    //------------------------------------

    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Set<MediaItemModel> getMediaItems() {
        return mediaItems;
    }
}
