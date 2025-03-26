package www.supercoding.com.web.dto.Sign;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SigninResponse {
    private String respontContent;

    public SigninResponse(String respontContent) {
        this.respontContent = respontContent;
    }

    public String getRespontContent() {
        return respontContent;
    }

    public void setRespontContent(String respontContent) {
        this.respontContent = respontContent;
    }
}
