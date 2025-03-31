package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotPasswordTests {

    @Test(description = "Quên mật khẩu thành công")
    public void testForgotPasswordSuccess() {
        String email = "hoangloi0802@gmail.com"; 

        String result = forgotPassword(email);
        Assert.assertEquals(result, "Mật khẩu đã được gửi về email của bạn");
    }

    @Test(description = "Quên mật khẩu với email không tồn tại")
    public void testForgotPasswordEmailNotExist() {
        String email = "hoangloi@gmail.com"; 

        String result = forgotPassword(email);
        Assert.assertEquals(result, "Email không tồn tại trong hệ thống");
    }

    @Test(description = "Quên mật khẩu với email sai định dạng")
    public void testForgotPasswordInvalidEmailFormat() {
        String email = "hoangloi@gmail"; 

        String result = forgotPassword(email);
        Assert.assertEquals(result, "Email không hợp lệ");
    }

    private String forgotPassword(String email) {
        String validEmail = "hoangloi0802@gmail.com";

        if (!email.contains("@") || !email.contains(".")) {
            return "Email không hợp lệ";
        }
        if (!email.equals(validEmail)) {
            return "Email không tồn tại trong hệ thống";
        }

        return "Mật khẩu đã được gửi về email của bạn";
    }
}
