package maurosimoni.BEU2W2D5.auth.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationSuccessfullPayload {
    private String accessToken;
}
