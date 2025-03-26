package www.supercoding.com.web.dto.Comment;

public class DeleteCommentRequest {
    private int commentId;

    public DeleteCommentRequest(int commentId) {
        this.commentId = commentId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}
