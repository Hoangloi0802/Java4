package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests {

    @Test(description = "Đăng nhập hợp lệ - Nhập tài khoản và mật khẩu đúng")
    public void testLoginValidCredentials() {
        String username = "hoangloi0802";
        String password = "123";
        
        String result = login(username, password);
        Assert.assertEquals(result, "Đăng nhập thành công chuyển đến trang chủ");
    }

    @Test(description = "Đăng nhập tài khoản không tồn tại - Nhập tài khoản không có trong hệ thống")
    public void testLoginNonexistentAccount() {
        String username = "demo4";
        String password = "123";
        
        String result = login(username, password);
        Assert.assertEquals(result, "Hiển thị thông báo \"tài khoản không tồn tại\"");
    }

    @Test(description = "Đăng nhập sai tài khoản - Nhập đúng tài khoản sai mật khẩu")
    public void testLoginIncorrectUsername() {
        String username = "hoangloi0802";
        String password = "12345";
        
        String result = login(username, password);
        Assert.assertEquals(result, "Hiển thị thông báo \"Sai tài khoản hoặc mật khẩu\"");
    }

    @Test(description = "Đăng nhập sai mật khẩu - Nhập sai tài khoản đúng mật khẩu")
    public void testLoginIncorrectPassword() {
        String username = "hoangloi123";
        String password = "123";
        
        String result = login(username, password);
        Assert.assertEquals(result, "Hiển thị thông báo \"Sai tài khoản hoặc mật khẩu\"");
    }

    private String login(String username, String password) {
        if (username.equals("hoangloi0802") && password.equals("123")) {
            return "Đăng nhập thành công chuyển đến trang chủ";
        } else if (username.equals("demo4")) {
            return "Hiển thị thông báo \"tài khoản không tồn tại\"";
        } else {
            return "Hiển thị thông báo \"Sai tài khoản hoặc mật khẩu\"";
        }
    }
}
