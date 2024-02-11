package DTO;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookmarkGroup {
    private int id;
    private String bookmarkName;
    private int order;
    private String registrationDate;
    private String updateDate;
}
