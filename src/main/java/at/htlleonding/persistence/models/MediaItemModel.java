package at.htlleonding.persistence.models;

import java.util.HashSet;
import java.util.Set;

public class MediaItemModel extends IdentityModel{
    private String title;
    private GenreModel genre;
    private Set<TopicModel> topics = new HashSet<>();
    private Set<AuthorModel> authors = new HashSet<>();

    //------------------------------------

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public GenreModel getGenre() {
        return genre;
    }
    public void setGenre(GenreModel genre) {
        this.genre = genre;
    }

    public Set<TopicModel> getTopics() {
        return topics;
    }
    public void setTopics(Set<TopicModel> topics) {
        this.topics = topics;
    }

    public Set<AuthorModel> getAuthors() {
        return authors;
    }
    public void setAuthors(Set<AuthorModel> authors) {
        this.authors = authors;
    }
}
