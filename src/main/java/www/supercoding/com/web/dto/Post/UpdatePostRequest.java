package www.supercoding.com.web.dto.Post;

public class UpdatePostRequest {

    private int postId;
    private String title;
    private String content;

    public UpdatePostRequest(int postId, String title, String content) {
        this.postId = postId;
        this.title = title;
        this.content = content;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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
}
