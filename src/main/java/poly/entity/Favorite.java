package poly.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Favorite", uniqueConstraints = @UniqueConstraint(columnNames = {"UserId", "VideoId"}))
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false, referencedColumnName = "Id")
    private Users user; // Liên kết với bảng Users qua cột Id

    @ManyToOne
    @JoinColumn(name = "VideoId", nullable = false, referencedColumnName = "Id")
    private Video video; // Liên kết với bảng Video qua cột Id

    @Temporal(TemporalType.DATE)
    @Column(name = "LikeDate", nullable = false)
    private Date likeDate;

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }
}
