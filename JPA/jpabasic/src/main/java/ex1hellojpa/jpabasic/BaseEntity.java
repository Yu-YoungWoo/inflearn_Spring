package ex1hellojpa.jpabasic;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 상속관계 매핑 X
// 추상 클래스 권장
@MappedSuperclass // 매핑 정보만 받는 super class
@Getter @Setter
public abstract class BaseEntity {

    // 여기 있는 속성 정보를 공통으로 사용하고 싶다면 MappedSuperclass
    @Column(name = "INSERT_MEMBER")
    private String createdBy;
    private LocalDateTime createdDate;
    @Column(name = "UPDATE_MEMBER")
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

}
