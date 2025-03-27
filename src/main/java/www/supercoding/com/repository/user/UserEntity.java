package www.supercoding.com.repository.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter // 모든 필드의 getter 메서드 자동 생성
@Setter // 모든 필드의 setter 메서드 자동 생성
@NoArgsConstructor // 매개변수 없는 생성자 자동 생성
@ToString // toString() 자동 생성
@Entity // 이 클래스는 JPA 엔티티이며 DB 테이블과 매핑됨
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 자동 생성
@Table(name = "users") // 이 엔티티는 'users' 테이블과 매핑됨
@Builder
public class UserEntity {
    @Id @Column(name = "userId") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }
}
