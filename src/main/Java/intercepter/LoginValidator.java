package intercepter;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
//登录校验
public class LoginValidator extends Validator {
    @Override
    protected void validate(Controller c) {
        setShortCircuit(true);
        validateCaptcha("captcha","captchMsg","验证码不正确");
    }

    @Override
    protected void handleError(Controller c) {
        c.renderJson();

    }
}
