package poly.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Video")
public class Video {
    @Id
    private String id;
    private String title;
    private String poster; // URL của poster (có thể tự động lấy từ YouTube hoặc upload)
    private String description;
    private boolean active;
    private int views;
    private String url; // Thêm trường để lưu URL của video

    @OneToMany(mappedBy = "video")
    private List<Favorite> favorite;
}


