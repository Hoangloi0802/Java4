package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTests {

    @Test(description = "Đăng ký hợp lệ - Nhập đầy đủ thông tin hợp lệ")
    public void testRegisterValidInfo() {
        String username = "hoangloi1234";
        String password = "12323111";
        String fullName = "Thái Hoàng Đế";
        String email = "demooo@gmail.com";

        String result = register(username, password, fullName, email);
        Assert.assertEquals(result, "Tạo tài khoản thành công");
    }

    @Test(description = "Đăng ký với tài khoản(email) đã tồn tại")
    public void testRegisterEmailAlreadyExists() {
        String username = "hoangloi";
        String password = "123123";
        String fullName = "Thái Test";
        String email = "hoangloi2005@gmail.com"; 

        String result = register(username, password, fullName, email);
        Assert.assertEquals(result, "Email đã được sử dụng");
    }

    @Test(description = "Xác nhận email khi đăng ký")
    public void testEmailConfirmation() {
        String username = "hoangloi";
        String password = "123231";
        String fullName = "Thái Hoàng Lợi";
        String email = "hoangloi2005@gmail.com";

     
        String result = register(username, password, fullName, email);
        Assert.assertEquals(result, "Hệ thống gửi email xác nhận tài khoản email đã đăng kí thành công");
    }

    @Test(description = "Đăng ký thiếu thông tin")
    public void testRegisterMissingInfo() {
        String username = ""; 
        String password = "123123";
        String fullName = "Thái Test";
        String email = ""; 

        String result = register(username, password, fullName, email);
        Assert.assertEquals(result, "Vui lòng nhập đầy đủ thông tin");
    }

    private String register(String username, String password, String fullName, String email) {
        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty()) {
            return "Vui lòng nhập đầy đủ thông tin";
        }
        if (email.equals("hoangloi0802@gmail.com")) {
            return "Email đã được sử dụng";
        }
        return "Tạo tài khoản thành công";
    }
}
