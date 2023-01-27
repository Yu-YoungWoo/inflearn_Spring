package ex1hellojpa.jpabasic.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Period {
    // 기간 Period
    private LocalDateTime startDate;
    private LocalDateTime endDate;


}
