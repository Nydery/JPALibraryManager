package at.htlleonding.persistence.export;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

@JsonRootName("WorksOfAuthors")
public class WorksOfAuthors {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Author")
    List<AuthorDTO> authors = new ArrayList<AuthorDTO>();

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }
}
