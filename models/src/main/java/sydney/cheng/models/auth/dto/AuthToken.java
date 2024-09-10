package sydney.cheng.models.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthToken {
    private String token;
    private boolean valid;
}
