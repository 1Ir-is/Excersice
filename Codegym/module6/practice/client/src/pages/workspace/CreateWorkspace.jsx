import React, { useState } from "react";
import {
  Box,
  Typography,
  Card,
  CardContent,
  Stepper,
  Step,
  StepLabel,
  Button,
  TextField,
  Grid,
  FormControl,
  FormLabel,
  RadioGroup,
  FormControlLabel,
  Radio,
  Chip,
  Avatar,
  List,
  ListItem,
  ListItemAvatar,
  ListItemText,
  Divider,
  Alert,
  LinearProgress,
} from "@mui/material";
import {
  Business,
  Person,
  Settings,
  CheckCircle,
  Facebook,
  Instagram,
  LinkedIn,
  Twitter,
  YouTube,
  Add,
  ArrowBack,
  ArrowForward,
} from "@mui/icons-material";
import { useNavigate } from "react-router-dom";

const CreateWorkspace = () => {
  const navigate = useNavigate();
  const [activeStep, setActiveStep] = useState(0);
  const [workspaceData, setWorkspaceData] = useState({
    name: "",
    description: "",
    type: "personal",
    industry: "",
    teamSize: "1-5",
    platforms: [],
    goals: [],
  });

  const steps = [
    "Thông tin cơ bản",
    "Chọn nền tảng",
    "Cài đặt mục tiêu",
    "Hoàn tất",
  ];

  const industries = [
    "E-commerce",
    "Giáo dục",
    "Y tế",
    "Công nghệ",
    "Du lịch",
    "Thực phẩm",
    "Thời trang",
    "Bất động sản",
    "Tài chính",
    "Khác",
  ];

  const platforms = [
    { id: "facebook", name: "Facebook", icon: <Facebook />, color: "#1877F2" },
    {
      id: "instagram",
      name: "Instagram",
      icon: <Instagram />,
      color: "#E4405F",
    },
    { id: "linkedin", name: "LinkedIn", icon: <LinkedIn />, color: "#0A66C2" },
    { id: "twitter", name: "Twitter", icon: <Twitter />, color: "#1DA1F2" },
    { id: "youtube", name: "YouTube", icon: <YouTube />, color: "#FF0000" },
    { id: "tiktok", name: "TikTok", icon: "🎵", color: "#000000" },
  ];

  const goals = [
    "Tăng độ nhận diện thương hiệu",
    "Tạo leads và bán hàng",
    "Xây dựng cộng đồng",
    "Chia sẻ nội dung giáo dục",
    "Hỗ trợ khách hàng",
    "Tuyển dụng nhân tài",
  ];

  const handleNext = () => {
    setActiveStep((prevStep) => prevStep + 1);
  };

  const handleBack = () => {
    setActiveStep((prevStep) => prevStep - 1);
  };

  const handleInputChange = (field) => (event) => {
    setWorkspaceData({
      ...workspaceData,
      [field]: event.target.value,
    });
  };

  const handlePlatformToggle = (platformId) => {
    const currentPlatforms = workspaceData.platforms;
    const updatedPlatforms = currentPlatforms.includes(platformId)
      ? currentPlatforms.filter((id) => id !== platformId)
      : [...currentPlatforms, platformId];

    setWorkspaceData({
      ...workspaceData,
      platforms: updatedPlatforms,
    });
  };

  const handleGoalToggle = (goal) => {
    const currentGoals = workspaceData.goals;
    const updatedGoals = currentGoals.includes(goal)
      ? currentGoals.filter((g) => g !== goal)
      : [...currentGoals, goal];

    setWorkspaceData({
      ...workspaceData,
      goals: updatedGoals,
    });
  };

  const handleFinish = () => {
    // Simulate workspace creation
    setTimeout(() => {
      navigate("/dashboard");
    }, 2000);
  };

  const renderStepContent = (step) => {
    switch (step) {
      case 0:
        return (
          <Box>
            <Typography variant="h6" gutterBottom>
              Thiết lập workspace của bạn
            </Typography>
            <Typography variant="body2" color="text.secondary" sx={{ mb: 3 }}>
              Cung cấp thông tin cơ bản về workspace marketing của bạn
            </Typography>

            <Grid container spacing={3}>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  label="Tên workspace"
                  placeholder="VD: Công ty ABC Marketing"
                  value={workspaceData.name}
                  onChange={handleInputChange("name")}
                  required
                />
              </Grid>

              <Grid item xs={12}>
                <TextField
                  fullWidth
                  multiline
                  rows={3}
                  label="Mô tả"
                  placeholder="Mô tả ngắn về doanh nghiệp hoặc dự án của bạn"
                  value={workspaceData.description}
                  onChange={handleInputChange("description")}
                />
              </Grid>

              <Grid item xs={12} sm={6}>
                <FormControl component="fieldset">
                  <FormLabel component="legend">Loại workspace</FormLabel>
                  <RadioGroup
                    value={workspaceData.type}
                    onChange={handleInputChange("type")}
                  >
                    <FormControlLabel
                      value="personal"
                      control={<Radio />}
                      label="Cá nhân"
                    />
                    <FormControlLabel
                      value="business"
                      control={<Radio />}
                      label="Doanh nghiệp"
                    />
                    <FormControlLabel
                      value="agency"
                      control={<Radio />}
                      label="Agency"
                    />
                  </RadioGroup>
                </FormControl>
              </Grid>

              <Grid item xs={12} sm={6}>
                <TextField
                  select
                  fullWidth
                  label="Lĩnh vực"
                  value={workspaceData.industry}
                  onChange={handleInputChange("industry")}
                  SelectProps={{ native: true }}
                >
                  <option value="">Chọn lĩnh vực</option>
                  {industries.map((industry) => (
                    <option key={industry} value={industry}>
                      {industry}
                    </option>
                  ))}
                </TextField>
              </Grid>

              <Grid item xs={12}>
                <FormControl component="fieldset">
                  <FormLabel component="legend">Quy mô team</FormLabel>
                  <RadioGroup
                    row
                    value={workspaceData.teamSize}
                    onChange={handleInputChange("teamSize")}
                  >
                    <FormControlLabel
                      value="1-5"
                      control={<Radio />}
                      label="1-5 người"
                    />
                    <FormControlLabel
                      value="6-20"
                      control={<Radio />}
                      label="6-20 người"
                    />
                    <FormControlLabel
                      value="21-50"
                      control={<Radio />}
                      label="21-50 người"
                    />
                    <FormControlLabel
                      value="50+"
                      control={<Radio />}
                      label="50+ người"
                    />
                  </RadioGroup>
                </FormControl>
              </Grid>
            </Grid>
          </Box>
        );

      case 1:
        return (
          <Box>
            <Typography variant="h6" gutterBottom>
              Chọn nền tảng mạng xã hội
            </Typography>
            <Typography variant="body2" color="text.secondary" sx={{ mb: 3 }}>
              Chọn các nền tảng bạn muốn quản lý (có thể thêm sau)
            </Typography>

            <Grid container spacing={2}>
              {platforms.map((platform) => (
                <Grid item xs={12} sm={6} md={4} key={platform.id}>
                  <Card
                    onClick={() => handlePlatformToggle(platform.id)}
                    sx={{
                      cursor: "pointer",
                      border: workspaceData.platforms.includes(platform.id)
                        ? "2px solid #272882"
                        : "1px solid #e0e0e0",
                      "&:hover": {
                        boxShadow: "0 4px 12px rgba(39, 40, 130, 0.15)",
                        transform: "translateY(-2px)",
                      },
                      transition: "all 0.3s ease",
                    }}
                  >
                    <CardContent sx={{ textAlign: "center", p: 3 }}>
                      <Avatar
                        sx={{
                          bgcolor: platform.color,
                          width: 56,
                          height: 56,
                          mx: "auto",
                          mb: 2,
                        }}
                      >
                        {platform.icon}
                      </Avatar>
                      <Typography variant="h6">{platform.name}</Typography>
                      {workspaceData.platforms.includes(platform.id) && (
                        <CheckCircle
                          sx={{
                            color: "#272882",
                            mt: 1,
                            fontSize: 24,
                          }}
                        />
                      )}
                    </CardContent>
                  </Card>
                </Grid>
              ))}
            </Grid>

            {workspaceData.platforms.length > 0 && (
              <Alert severity="info" sx={{ mt: 3 }}>
                Đã chọn {workspaceData.platforms.length} nền tảng. Bạn có thể
                kết nối tài khoản sau khi tạo workspace.
              </Alert>
            )}
          </Box>
        );

      case 2:
        return (
          <Box>
            <Typography variant="h6" gutterBottom>
              Mục tiêu marketing
            </Typography>
            <Typography variant="body2" color="text.secondary" sx={{ mb: 3 }}>
              Chọn các mục tiêu chính để chúng tôi có thể tùy chỉnh trải nghiệm
            </Typography>

            <Box sx={{ display: "flex", flexWrap: "wrap", gap: 2 }}>
              {goals.map((goal) => (
                <Chip
                  key={goal}
                  label={goal}
                  onClick={() => handleGoalToggle(goal)}
                  color={
                    workspaceData.goals.includes(goal) ? "primary" : "default"
                  }
                  variant={
                    workspaceData.goals.includes(goal) ? "filled" : "outlined"
                  }
                  sx={{
                    fontSize: "0.9rem",
                    py: 2,
                    px: 1,
                    "&:hover": {
                      backgroundColor: workspaceData.goals.includes(goal)
                        ? "#1e1f5a"
                        : "rgba(39, 40, 130, 0.1)",
                    },
                  }}
                />
              ))}
            </Box>

            {workspaceData.goals.length > 0 && (
              <Alert severity="success" sx={{ mt: 3 }}>
                Tuyệt vời! Đã chọn {workspaceData.goals.length} mục tiêu.
              </Alert>
            )}
          </Box>
        );

      case 3:
        return (
          <Box textAlign="center">
            <Typography variant="h6" gutterBottom>
              Đang tạo workspace...
            </Typography>
            <Typography variant="body2" color="text.secondary" sx={{ mb: 3 }}>
              Chúng tôi đang thiết lập workspace cho bạn
            </Typography>

            <LinearProgress sx={{ mb: 3, height: 8, borderRadius: 4 }} />

            <Card sx={{ p: 3, mb: 3, textAlign: "left" }}>
              <Typography variant="subtitle1" gutterBottom>
                Tóm tắt workspace:
              </Typography>
              <List>
                <ListItem>
                  <ListItemAvatar>
                    <Avatar sx={{ bgcolor: "primary.main" }}>
                      <Business />
                    </Avatar>
                  </ListItemAvatar>
                  <ListItemText
                    primary={workspaceData.name}
                    secondary={`${workspaceData.type} • ${workspaceData.industry}`}
                  />
                </ListItem>

                <ListItem>
                  <ListItemAvatar>
                    <Avatar sx={{ bgcolor: "secondary.main" }}>
                      <Person />
                    </Avatar>
                  </ListItemAvatar>
                  <ListItemText
                    primary={`Quy mô: ${workspaceData.teamSize} người`}
                    secondary={`${workspaceData.platforms.length} nền tảng được chọn`}
                  />
                </ListItem>

                <ListItem>
                  <ListItemAvatar>
                    <Avatar sx={{ bgcolor: "success.main" }}>
                      <Settings />
                    </Avatar>
                  </ListItemAvatar>
                  <ListItemText
                    primary={`${workspaceData.goals.length} mục tiêu marketing`}
                    secondary="Sẵn sàng để bắt đầu"
                  />
                </ListItem>
              </List>
            </Card>
          </Box>
        );

      default:
        return null;
    }
  };

  return (
    <Box sx={{ maxWidth: 800, mx: "auto", py: 4 }}>
      <Typography variant="h4" gutterBottom textAlign="center">
        Tạo Workspace Mới
      </Typography>
      <Typography
        variant="body1"
        color="text.secondary"
        textAlign="center"
        sx={{ mb: 4 }}
      >
        Thiết lập workspace để bắt đầu quản lý social media marketing
      </Typography>

      <Card sx={{ mb: 4 }}>
        <CardContent sx={{ p: 4 }}>
          <Stepper activeStep={activeStep} sx={{ mb: 4 }}>
            {steps.map((label) => (
              <Step key={label}>
                <StepLabel>{label}</StepLabel>
              </Step>
            ))}
          </Stepper>

          {renderStepContent(activeStep)}

          <Divider sx={{ my: 3 }} />

          <Box sx={{ display: "flex", justifyContent: "space-between" }}>
            <Button
              onClick={handleBack}
              disabled={activeStep === 0}
              startIcon={<ArrowBack />}
            >
              Quay lại
            </Button>

            {activeStep === steps.length - 1 ? (
              <Button
                variant="contained"
                onClick={handleFinish}
                startIcon={<Add />}
              >
                Tạo Workspace
              </Button>
            ) : (
              <Button
                variant="contained"
                onClick={handleNext}
                endIcon={<ArrowForward />}
                disabled={
                  (activeStep === 0 && !workspaceData.name) ||
                  (activeStep === 1 && workspaceData.platforms.length === 0)
                }
              >
                Tiếp tục
              </Button>
            )}
          </Box>
        </CardContent>
      </Card>
    </Box>
  );
};

export default CreateWorkspace;
