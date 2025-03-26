package www.supercoding.com.web.dto.Post;

public class DeletePostRequest {
    private int postId;

    public DeletePostRequest(int postId) {
        this.postId = postId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
