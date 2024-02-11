package DTO;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bookmark {
    private int id;
    private String bookmarkName;
    private String wifiName;
    private String registrationDate;
}
