package mk.finki.ukim.wp.emt.model.dto;


import lombok.Data;
import mk.finki.ukim.wp.emt.model.enumerations.Category;


@Data
public class BookDto {

    private String title;

    private Category category;

    private Long author;

    private int availableCopies;

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }

    public Long getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }
}
