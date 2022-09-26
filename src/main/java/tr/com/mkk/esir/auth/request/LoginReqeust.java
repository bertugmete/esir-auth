package tr.com.mkk.esir.auth.request;

import lombok.Data;

@Data
public class LoginReqeust {
    private String username;
    private String password;
}
