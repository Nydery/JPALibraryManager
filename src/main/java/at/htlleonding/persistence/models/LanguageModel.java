package at.htlleonding.persistence.models;

import java.util.HashSet;
import java.util.Set;

public class LanguageModel extends IdentityModel{
    private String keyword;
    private final Set<MediaExemplarModel> mediaExemplars = new HashSet<>();

    //------------------------------------

    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Set<MediaExemplarModel> getMediaExemplars() {
        return mediaExemplars;
    }
}
