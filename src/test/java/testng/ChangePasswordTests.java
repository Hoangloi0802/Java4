package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangePasswordTests {

    private String currentPassword = "123231";

    @Test(description = "Đổi mật khẩu thành công")
    public void testChangePasswordSuccess() {
        String oldPassword = "123231";
        String newPassword = "hoangloi8";
        String confirmPassword = "hoangloi8";

        String result = changePassword(oldPassword, newPassword, confirmPassword);
        Assert.assertEquals(result, "Đổi mật khẩu thành công");
    }

    @Test(description = "Đổi mật khẩu với mật khẩu cũ sai")
    public void testChangePasswordWrongOldPassword() {
        String oldPassword = "123456"; 
        String newPassword = "hoangloi8";
        String confirmPassword = "hoangloi8";

        String result = changePassword(oldPassword, newPassword, confirmPassword);
        Assert.assertEquals(result, "Sai mật khẩu cũ");
    }

    @Test(description = "Đổi mật khẩu với xác nhận không khớp")
    public void testChangePasswordMismatchConfirmation() {
        String oldPassword = "123231";
        String newPassword = "hoangloi8";
        String confirmPassword = "hoangloi1";

        String result = changePassword(oldPassword, newPassword, confirmPassword);
        Assert.assertEquals(result, "Mật khẩu xác nhận không trùng khớp");
    }

    @Test(description = "Đổi mật khẩu khi bỏ trống mật khẩu mới")
    public void testChangePasswordEmptyNewPassword() {
        String oldPassword = "123231";
        String newPassword = "";
        String confirmPassword = "hoangloi8";

        String result = changePassword(oldPassword, newPassword, confirmPassword);
        Assert.assertEquals(result, "Vui lòng nhập mật khẩu mới");
    }

    @Test(description = "Đổi mật khẩu với mật khẩu mới trùng mật khẩu cũ")
    public void testChangePasswordSameAsOld() {
        String oldPassword = "123231";
        String newPassword = "123231"; 
        String confirmPassword = "123231";

        String result = changePassword(oldPassword, newPassword, confirmPassword);
        Assert.assertEquals(result, "Mật khẩu mới không được trùng mật khẩu cũ");
    }

  
    private String changePassword(String oldPassword, String newPassword, String confirmPassword) {
        if (!oldPassword.equals(currentPassword)) {
            return "Sai mật khẩu cũ";
        }
        if (newPassword.isEmpty()) {
            return "Vui lòng nhập mật khẩu mới";
        }
        if (!newPassword.equals(confirmPassword)) {
            return "Mật khẩu xác nhận không trùng khớp";
        }
        if (newPassword.equals(currentPassword)) {
            return "Mật khẩu mới không được trùng mật khẩu cũ";
        }

       
        currentPassword = newPassword;
        return "Đổi mật khẩu thành công";
    }
}
