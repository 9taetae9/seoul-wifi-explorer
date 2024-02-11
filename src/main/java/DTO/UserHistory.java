package DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserHistory {
    private int id;
    private double latitude;
    private double longitude;
    private String queryDate;
}
