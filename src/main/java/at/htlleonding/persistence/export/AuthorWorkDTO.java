package at.htlleonding.persistence.export;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

@JsonRootName("Work")
public class AuthorWorkDTO {
    @JsonProperty("Title")
    @JacksonXmlProperty(isAttribute = true)
    String title;

    @JsonProperty("Genre")
    @JacksonXmlProperty(localName = "genre", isAttribute = true)
    String genre;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Publication")
    List<PublicationOfWorkDTO> publications = new ArrayList<PublicationOfWorkDTO>();

    public AuthorWorkDTO() {
    }

    public AuthorWorkDTO(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<PublicationOfWorkDTO> getPublications() {
        return publications;
    }

    public void setPublications(List<PublicationOfWorkDTO> publications) {
        this.publications = publications;
    }
}
