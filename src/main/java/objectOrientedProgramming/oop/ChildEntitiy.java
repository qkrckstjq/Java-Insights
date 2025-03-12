package objectOrientedProgramming.oop;

import java.time.LocalDateTime;

public class ChildEntitiy extends BaseEntity{
    private LocalDateTime modifiedAt;

    public void setModifiedAt() {
        this.modifiedAt = LocalDateTime.now();
    }

    public void getModifiedAt() {
        System.out.println(modifiedAt);
    }
}
