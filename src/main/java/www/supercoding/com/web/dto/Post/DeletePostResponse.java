package www.supercoding.com.web.dto.Post;

public class DeletePostResponse {
    private String message;

    public DeletePostResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
