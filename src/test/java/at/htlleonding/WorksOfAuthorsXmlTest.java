package at.htlleonding;

import at.htlleonding.persistence.export.AuthorDTO;
import at.htlleonding.persistence.export.AuthorWorkDTO;
import at.htlleonding.persistence.export.WorksOfAuthorDTO;
import at.htlleonding.persistence.export.WorksOfAuthors;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class WorksOfAuthorsXmlTest {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    @Test
    public void createWorksOfAuthorDTO_serializeToXml_deserialize_checkObjects() {
        var target = new WorksOfAuthorDTO("Orwell", "George", LocalDate.parse("25.06.1903", dtf), LocalDate.parse("21.01.1950", dtf));
        target.getWork().add(new AuthorWorkDTO("1984", ""));
        target.getWork().add(new AuthorWorkDTO("Animal Farm", ""));

        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        StringBuilder sb = new StringBuilder();
        try {
            sb.append(xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(target));
            var result = sb.toString();
            assertTrue(result.contains("Orwell"));
            assertTrue(result.contains("1984"));
            System.out.println(result);

            Reader reader = new StringReader(result);
            var result2 = (WorksOfAuthorDTO)xmlMapper.readValue(reader, WorksOfAuthorDTO.class);
            assertEquals("Orwell", result2.getAuthor().getLastName());
            assertEquals("1984", result2.getWork().get(0).getTitle());
        }
        catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void createWorksOfAuthorsDTOWith1Author_serializeToXml_deserialize_checkObjects() {
        var target = new WorksOfAuthors();
        var aut = new AuthorDTO("Orwell", "George", LocalDate.parse("25.06.1903", dtf), LocalDate.parse("21.01.1950", dtf));
        aut.getWorks().add(new AuthorWorkDTO("1984", "reality"));
        aut.getWorks().add(new AuthorWorkDTO("Animal Farm", "fiction"));

        target.getAuthors().add(aut);

        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        StringBuilder sb = new StringBuilder();
        try {
            sb.append(xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(target));
            var result = sb.toString();
            assertTrue(result.contains("Orwell"));
            assertTrue(result.contains("1984"));
            System.out.println(result);

            Reader reader = new StringReader(result);
            var result2 = (WorksOfAuthors)xmlMapper.readValue(reader, WorksOfAuthors.class);
            var subResult = result2.getAuthors().get(0);
            assertEquals("Orwell", subResult.getLastName());
            assertEquals("1984", subResult.getWorks().get(0).getTitle());
        }
        catch (Exception e) {
            fail(e);
        }
    }


}
