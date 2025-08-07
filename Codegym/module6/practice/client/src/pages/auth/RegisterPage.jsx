import React, { useState } from "react";
import {
  Box,
  Container,
  Paper,
  Typography,
  TextField,
  Button,
  Link,
  Divider,
  Alert,
  IconButton,
  InputAdornment,
  Checkbox,
  FormControlLabel,
  Stepper,
  Step,
  StepLabel,
} from "@mui/material";
import {
  Visibility,
  VisibilityOff,
  Google as GoogleIcon,
  Facebook as FacebookIcon,
  CheckCircle,
} from "@mui/icons-material";
import { useNavigate } from "react-router-dom";

const RegisterPage = () => {
  const navigate = useNavigate();
  const [activeStep, setActiveStep] = useState(0);
  const [formData, setFormData] = useState({
    // Step 1
    name: "",
    email: "",
    password: "",
    confirmPassword: "",
    // Step 2
    company: "",
    position: "",
    phone: "",
    // Step 3
    plan: "basic",
    agreeTerms: false,
    agreeMarketing: false,
  });
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const steps = ["Thông tin tài khoản", "Thông tin doanh nghiệp", "Hoàn tất"];

  const handleChange = (e) => {
    const { name, value, checked } = e.target;
    setFormData({
      ...formData,
      [name]: name.includes("agree") ? checked : value,
    });
  };

  const validateStep = (step) => {
    switch (step) {
      case 0:
        if (!formData.name || !formData.email || !formData.password) {
          setError("Vui lòng điền đầy đủ thông tin");
          return false;
        }
        if (formData.password !== formData.confirmPassword) {
          setError("Mật khẩu xác nhận không khớp");
          return false;
        }
        if (formData.password.length < 6) {
          setError("Mật khẩu phải có ít nhất 6 ký tự");
          return false;
        }
        break;
      case 1:
        if (!formData.company || !formData.position) {
          setError("Vui lòng điền thông tin doanh nghiệp");
          return false;
        }
        break;
      case 2:
        if (!formData.agreeTerms) {
          setError("Vui lòng đồng ý với điều khoản sử dụng");
          return false;
        }
        break;
      default:
        return true;
    }
    setError("");
    return true;
  };

  const handleNext = () => {
    if (validateStep(activeStep)) {
      setActiveStep(activeStep + 1);
    }
  };

  const handleBack = () => {
    setActiveStep(activeStep - 1);
    setError("");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateStep(activeStep)) return;

    setLoading(true);
    setError("");

    try {
      // Simulate API call
      await new Promise((resolve) => setTimeout(resolve, 2000));

      // Mock successful registration
      navigate("/login", {
        state: { message: "Đăng ký thành công! Vui lòng đăng nhập." },
      });
    } catch (err) {
      setError("Có lỗi xảy ra. Vui lòng thử lại.");
    } finally {
      setLoading(false);
    }
  };

  const handleSocialLogin = (provider) => {
    console.log(`Register with ${provider}`);
  };

  const renderStepContent = (step) => {
    switch (step) {
      case 0:
        return (
          <Box>
            <TextField
              fullWidth
              label="Họ và tên"
              name="name"
              value={formData.name}
              onChange={handleChange}
              margin="normal"
              required
              autoFocus
            />

            <TextField
              fullWidth
              label="Email"
              name="email"
              type="email"
              value={formData.email}
              onChange={handleChange}
              margin="normal"
              required
            />

            <TextField
              fullWidth
              label="Mật khẩu"
              name="password"
              type={showPassword ? "text" : "password"}
              value={formData.password}
              onChange={handleChange}
              margin="normal"
              required
              InputProps={{
                endAdornment: (
                  <InputAdornment position="end">
                    <IconButton
                      onClick={() => setShowPassword(!showPassword)}
                      edge="end"
                    >
                      {showPassword ? <VisibilityOff /> : <Visibility />}
                    </IconButton>
                  </InputAdornment>
                ),
              }}
            />

            <TextField
              fullWidth
              label="Xác nhận mật khẩu"
              name="confirmPassword"
              type={showConfirmPassword ? "text" : "password"}
              value={formData.confirmPassword}
              onChange={handleChange}
              margin="normal"
              required
              InputProps={{
                endAdornment: (
                  <InputAdornment position="end">
                    <IconButton
                      onClick={() =>
                        setShowConfirmPassword(!showConfirmPassword)
                      }
                      edge="end"
                    >
                      {showConfirmPassword ? <VisibilityOff /> : <Visibility />}
                    </IconButton>
                  </InputAdornment>
                ),
              }}
            />
          </Box>
        );

      case 1:
        return (
          <Box>
            <TextField
              fullWidth
              label="Tên công ty"
              name="company"
              value={formData.company}
              onChange={handleChange}
              margin="normal"
              required
            />

            <TextField
              fullWidth
              label="Chức vụ"
              name="position"
              value={formData.position}
              onChange={handleChange}
              margin="normal"
              required
            />

            <TextField
              fullWidth
              label="Số điện thoại"
              name="phone"
              value={formData.phone}
              onChange={handleChange}
              margin="normal"
            />
          </Box>
        );

      case 2:
        return (
          <Box>
            <Box sx={{ textAlign: "center", mb: 3 }}>
              <CheckCircle
                sx={{ fontSize: 64, color: "success.main", mb: 2 }}
              />
              <Typography variant="h5" gutterBottom>
                Gần xong rồi!
              </Typography>
              <Typography variant="body1" color="text.secondary">
                Chỉ còn vài bước nữa để hoàn tất đăng ký
              </Typography>
            </Box>

            <FormControlLabel
              control={
                <Checkbox
                  name="agreeTerms"
                  checked={formData.agreeTerms}
                  onChange={handleChange}
                  color="primary"
                />
              }
              label={
                <Typography variant="body2">
                  Tôi đồng ý với{" "}
                  <Link href="#" color="primary">
                    Điều khoản sử dụng
                  </Link>{" "}
                  và{" "}
                  <Link href="#" color="primary">
                    Chính sách bảo mật
                  </Link>
                </Typography>
              }
              sx={{ mb: 2 }}
            />

            <FormControlLabel
              control={
                <Checkbox
                  name="agreeMarketing"
                  checked={formData.agreeMarketing}
                  onChange={handleChange}
                  color="primary"
                />
              }
              label={
                <Typography variant="body2">
                  Tôi muốn nhận thông tin về sản phẩm và ưu đãi mới
                </Typography>
              }
            />
          </Box>
        );

      default:
        return null;
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
      <Container maxWidth="md">
        <Paper
          elevation={24}
          sx={{
            p: 4,
            borderRadius: 3,
            backdropFilter: "blur(10px)",
            border: "1px solid rgba(255,255,255,0.1)",
          }}
        >
          {/* Header */}
          <Box sx={{ textAlign: "center", mb: 4 }}>
            <Typography variant="h4" gutterBottom sx={{ fontWeight: "bold" }}>
              Đăng ký tài khoản
            </Typography>
            <Typography variant="body1" color="text.secondary">
              Tạo tài khoản miễn phí và bắt đầu tự động hóa social media
            </Typography>
          </Box>

          {/* Stepper */}
          <Stepper activeStep={activeStep} sx={{ mb: 4 }}>
            {steps.map((label) => (
              <Step key={label}>
                <StepLabel>{label}</StepLabel>
              </Step>
            ))}
          </Stepper>

          {/* Social Login - Only show on first step */}
          {activeStep === 0 && (
            <>
              <Box sx={{ mb: 3 }}>
                <Button
                  fullWidth
                  variant="outlined"
                  size="large"
                  startIcon={<GoogleIcon />}
                  sx={{ mb: 2, py: 1.5 }}
                  onClick={() => handleSocialLogin("google")}
                >
                  Đăng ký với Google
                </Button>
                <Button
                  fullWidth
                  variant="outlined"
                  size="large"
                  startIcon={<FacebookIcon />}
                  sx={{ py: 1.5 }}
                  onClick={() => handleSocialLogin("facebook")}
                >
                  Đăng ký với Facebook
                </Button>
              </Box>

              <Divider sx={{ my: 3 }}>
                <Typography variant="body2" color="text.secondary">
                  Hoặc
                </Typography>
              </Divider>
            </>
          )}

          {/* Error Alert */}
          {error && (
            <Alert severity="error" sx={{ mb: 3 }}>
              {error}
            </Alert>
          )}

          {/* Form Content */}
          <form onSubmit={handleSubmit}>
            {renderStepContent(activeStep)}

            {/* Navigation Buttons */}
            <Box
              sx={{ display: "flex", justifyContent: "space-between", mt: 4 }}
            >
              <Button
                disabled={activeStep === 0}
                onClick={handleBack}
                variant="outlined"
              >
                Quay lại
              </Button>

              {activeStep === steps.length - 1 ? (
                <Button
                  type="submit"
                  variant="contained"
                  disabled={loading}
                  sx={{
                    background:
                      "linear-gradient(135deg, #667eea 0%, #764ba2 100%)",
                    "&:hover": {
                      background:
                        "linear-gradient(135deg, #5a6fd8 0%, #6a4190 100%)",
                    },
                  }}
                >
                  {loading ? "Đang đăng ký..." : "Hoàn tất đăng ký"}
                </Button>
              ) : (
                <Button
                  variant="contained"
                  onClick={handleNext}
                  sx={{
                    background:
                      "linear-gradient(135deg, #667eea 0%, #764ba2 100%)",
                    "&:hover": {
                      background:
                        "linear-gradient(135deg, #5a6fd8 0%, #6a4190 100%)",
                    },
                  }}
                >
                  Tiếp tục
                </Button>
              )}
            </Box>
          </form>

          {/* Footer Links */}
          <Box sx={{ textAlign: "center", mt: 3 }}>
            <Typography variant="body2" color="text.secondary">
              Đã có tài khoản?{" "}
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

export default RegisterPage;
