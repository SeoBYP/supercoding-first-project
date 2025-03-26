package www.supercoding.com.web.dto.Comment;

public class Comment {
    private int id;
    private String content;
    private int postId;
    private String email;

    public Comment(int id, String content, int postId, String email) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.email = email;
    }

    public Comment(String content, int postId, String email) {
        this.content = content;
        this.postId = postId;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
