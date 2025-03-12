package objectOrientedProgramming.oop;

import java.time.LocalDateTime;

public class BaseEntity {
    private LocalDateTime createdAt;

    public BaseEntity() {
        this.createdAt = LocalDateTime.now();
    }

    public void getCreatedAt() {
        System.out.println(createdAt);
    }
}
