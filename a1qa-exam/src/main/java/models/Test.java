package models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    private String duration;
    private String method;
    private String name;
    private String startTime;
    private String endTime;
    private String status;
}
