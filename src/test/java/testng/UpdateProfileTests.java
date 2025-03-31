package testng;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateProfileTests {

    private boolean isLoggedIn;

    @BeforeMethod
    public void setup() {
        isLoggedIn = true; 
    }

    @Test(description = "Cập nhật với dữ liệu không hợp lệ")
    public void testUpdateProfileWithInvalidData() {
        String name = "Thái Hoàng Lợi";
        String email = "hoangloi@gmail"; 

        String result = updateProfile(name, email);
        Assert.assertEquals(result, "Email/Số điện thoại không hợp lệ");
    }

    @Test(description = "Cập nhật thông tin hợp lệ")
    public void testUpdateProfileSuccess() {
        String name = "Thái Hoàng Lợi";
        String email = "hoangloi@gmail.com"; 
        String result = updateProfile(name, email);
        Assert.assertEquals(result, "Cập nhật thành công");
    }

    @Test(description = "Cập nhật thông tin khi bỏ trống trường bắt buộc")
    public void testUpdateProfileWithMissingFields() {
        String name = "Thái Hoàng Lợi";
        String email = ""; 

        String result = updateProfile(name, email);
        Assert.assertEquals(result, "Vui lòng nhập đầy đủ thông tin");
    }

    @Test(description = "Cập nhật thông tin khi chưa đăng nhập")
    public void testUpdateProfileWithoutLogin() {
        isLoggedIn = false; 

        String name = "Thái Hoàng Lợi";
        String email = "hoangloi@gmail.com";

        String result = updateProfile(name, email);
        Assert.assertEquals(result, "Chuyển hướng về trang đăng nhập");
    }

    private String updateProfile(String name, String email) {
        if (!isLoggedIn) {
            return "Chuyển hướng về trang đăng nhập";
        }
        if (name.isEmpty() || email.isEmpty()) {
            return "Vui lòng nhập đầy đủ thông tin";
        }
        if (!email.contains("@") || !email.contains(".")) {
            return "Email/Số điện thoại không hợp lệ";
        }

        return "Cập nhật thành công";
    }
}

