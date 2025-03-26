package www.supercoding.com.web.dto.Comment;

public class UpdateCommentRequest {
    private int commentId;
    private int postId;
    private String content;
    private String email;

    public UpdateCommentRequest(int postId, String content, String email) {
        this.postId = postId;
        this.content = content;
        this.email = email;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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
}
