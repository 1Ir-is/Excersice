import React, { useState } from "react";
import {
  Box,
  Container,
  Paper,
  Typography,
  TextField,
  Button,
  Link,
  Alert,
  Stepper,
  Step,
  StepLabel,
} from "@mui/material";
import { Email, Lock } from "@mui/icons-material";
import { useNavigate } from "react-router-dom";

const ForgotPasswordPage = () => {
  const navigate = useNavigate();
  const [activeStep, setActiveStep] = useState(0);
  const [formData, setFormData] = useState({
    email: "",
    code: "",
    newPassword: "",
    confirmPassword: "",
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const steps = ["Nhập email", "Xác thực", "Đặt lại mật khẩu"];

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");
    setSuccess("");

    try {
      // Simulate API call
      await new Promise((resolve) => setTimeout(resolve, 1500));

      if (activeStep === 0) {
        // Send reset code
        setSuccess("Mã xác thực đã được gửi đến email của bạn");
        setActiveStep(1);
      } else if (activeStep === 1) {
        // Verify code
        if (formData.code !== "123456") {
          setError("Mã xác thực không đúng");
          return;
        }
        setActiveStep(2);
      } else {
        // Reset password
        if (formData.newPassword !== formData.confirmPassword) {
          setError("Mật khẩu xác nhận không khớp");
          return;
        }
        if (formData.newPassword.length < 6) {
          setError("Mật khẩu phải có ít nhất 6 ký tự");
          return;
        }
        navigate("/login", {
          state: {
            message:
              "Đặt lại mật khẩu thành công! Vui lòng đăng nhập với mật khẩu mới.",
          },
        });
      }
    } catch (err) {
      setError("Có lỗi xảy ra. Vui lòng thử lại.");
    } finally {
      setLoading(false);
    }
  };

  const renderStepContent = (step) => {
    switch (step) {
      case 0:
        return (
          <Box sx={{ textAlign: "center" }}>
            <Email sx={{ fontSize: 64, color: "primary.main", mb: 3 }} />
            <Typography variant="h5" gutterBottom>
              Quên mật khẩu?
            </Typography>
            <Typography variant="body1" color="text.secondary" sx={{ mb: 4 }}>
              Nhập địa chỉ email của bạn và chúng tôi sẽ gửi mã xác thực để đặt
              lại mật khẩu.
            </Typography>
            <TextField
              fullWidth
              label="Địa chỉ email"
              name="email"
              type="email"
              value={formData.email}
              onChange={handleChange}
              margin="normal"
              required
              autoFocus
            />
          </Box>
        );

      case 1:
        return (
          <Box sx={{ textAlign: "center" }}>
            <Email sx={{ fontSize: 64, color: "primary.main", mb: 3 }} />
            <Typography variant="h5" gutterBottom>
              Kiểm tra email
            </Typography>
            <Typography variant="body1" color="text.secondary" sx={{ mb: 4 }}>
              Chúng tôi đã gửi mã xác thực 6 số đến{" "}
              <strong>{formData.email}</strong>
            </Typography>
            <TextField
              fullWidth
              label="Mã xác thực"
              name="code"
              value={formData.code}
              onChange={handleChange}
              margin="normal"
              required
              autoFocus
              placeholder="Nhập mã 6 số"
              inputProps={{ maxLength: 6 }}
            />
            <Typography variant="body2" color="text.secondary" sx={{ mt: 2 }}>
              Không nhận được mã?{" "}
              <Link href="#" sx={{ fontWeight: "bold" }}>
                Gửi lại
              </Link>
            </Typography>
          </Box>
        );

      case 2:
        return (
          <Box sx={{ textAlign: "center" }}>
            <Lock sx={{ fontSize: 64, color: "primary.main", mb: 3 }} />
            <Typography variant="h5" gutterBottom>
              Đặt mật khẩu mới
            </Typography>
            <Typography variant="body1" color="text.secondary" sx={{ mb: 4 }}>
              Tạo mật khẩu mới cho tài khoản của bạn
            </Typography>
            <TextField
              fullWidth
              label="Mật khẩu mới"
              name="newPassword"
              type="password"
              value={formData.newPassword}
              onChange={handleChange}
              margin="normal"
              required
              autoFocus
            />
            <TextField
              fullWidth
              label="Xác nhận mật khẩu mới"
              name="confirmPassword"
              type="password"
              value={formData.confirmPassword}
              onChange={handleChange}
              margin="normal"
              required
            />
          </Box>
        );

      default:
        return null;
    }
  };

  const getButtonText = () => {
    if (loading) return "Đang xử lý...";
    switch (activeStep) {
      case 0:
        return "Gửi mã xác thực";
      case 1:
        return "Xác thực";
      case 2:
        return "Đặt lại mật khẩu";
      default:
        return "Tiếp tục";
    }
  };

  return (
    <Box
      sx={{
        minHeight: "100vh",
        background: "linear-gradient(135deg, #667eea 0%, #764ba2 100%)",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        py: 3,
      }}
    >
      <Container maxWidth="sm">
        <Paper
          elevation={24}
          sx={{
            p: 4,
            borderRadius: 3,
            backdropFilter: "blur(10px)",
            border: "1px solid rgba(255,255,255,0.1)",
          }}
        >
          {/* Stepper */}
          <Stepper activeStep={activeStep} sx={{ mb: 4 }}>
            {steps.map((label) => (
              <Step key={label}>
                <StepLabel>{label}</StepLabel>
              </Step>
            ))}
          </Stepper>

          {/* Error Alert */}
          {error && (
            <Alert severity="error" sx={{ mb: 3 }}>
              {error}
            </Alert>
          )}

          {/* Success Alert */}
          {success && (
            <Alert severity="success" sx={{ mb: 3 }}>
              {success}
            </Alert>
          )}

          {/* Form */}
          <form onSubmit={handleSubmit}>
            {renderStepContent(activeStep)}

            <Button
              type="submit"
              fullWidth
              variant="contained"
              size="large"
              disabled={loading}
              sx={{
                mt: 4,
                py: 1.5,
                background: "linear-gradient(135deg, #667eea 0%, #764ba2 100%)",
                "&:hover": {
                  background:
                    "linear-gradient(135deg, #5a6fd8 0%, #6a4190 100%)",
                },
              }}
            >
              {getButtonText()}
            </Button>
          </form>

          {/* Footer Links */}
          <Box sx={{ textAlign: "center", mt: 3 }}>
            <Typography variant="body2" color="text.secondary">
              Nhớ lại mật khẩu?{" "}
              <Link
                component="button"
                type="button"
                variant="body2"
                onClick={() => navigate("/login")}
                sx={{ fontWeight: "bold" }}
              >
                Đăng nhập ngay
              </Link>
            </Typography>
          </Box>

          <Box sx={{ textAlign: "center", mt: 2 }}>
            <Link
              component="button"
              type="button"
              variant="body2"
              onClick={() => navigate("/")}
              sx={{ color: "text.secondary" }}
            >
              ← Quay lại trang chủ
            </Link>
          </Box>
        </Paper>
      </Container>
    </Box>
  );
};

export default ForgotPasswordPage;
