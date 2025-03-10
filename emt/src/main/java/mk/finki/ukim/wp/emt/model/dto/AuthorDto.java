package mk.finki.ukim.wp.emt.model.dto;

import lombok.Data;



@Data
public class AuthorDto {

    private String name;

    private String surname;

    private Long country;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Long getCountry() {
        return country;
    }
}
