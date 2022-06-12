package at.htlleonding.persistence.export;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonRootName("WorksOfAuthor")
public class WorksOfAuthorDTO {

    @JsonProperty("Author")
    AuthorDTO author;

    @JacksonXmlElementWrapper(localName = "Works")
    @JsonProperty("Work")
    List<AuthorWorkDTO> work = new ArrayList<AuthorWorkDTO>();

    public WorksOfAuthorDTO() {
    }

    public WorksOfAuthorDTO(String lastName, String firstName, LocalDate dateBirth, LocalDate dateDeath) {
        author = new AuthorDTO(lastName, firstName, dateBirth, dateDeath);
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public List<AuthorWorkDTO> getWork() {
        return work;
    }

    public void setWork(List<AuthorWorkDTO> work) {
        this.work = work;
    }
}
