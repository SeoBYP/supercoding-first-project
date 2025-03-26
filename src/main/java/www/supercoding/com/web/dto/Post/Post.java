package www.supercoding.com.web.dto.Post;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String title;
    private String content;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Post(int id,String title, String content,String email) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.email = email;
        this.created_at = LocalDateTime.now();
        this.updated_at = this.created_at;
    }

    public Post(int id,String title, String content,String email,LocalDateTime created_at) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
