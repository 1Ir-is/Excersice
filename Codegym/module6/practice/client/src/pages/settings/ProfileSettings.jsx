import React, { useState } from "react";
import {
  Box,
  Paper,
  Typography,
  Avatar,
  Button,
  TextField,
  Grid,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Chip,
  Divider,
  Switch,
  FormControlLabel,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  ListItemSecondaryAction,
  IconButton,
  Card,
  CardContent,
  Alert,
} from "@mui/material";
import {
  Person,
  Email,
  Phone,
  Location,
  Language,
  Notifications,
  Security,
  Privacy,
  Edit,
  Save,
  Cancel,
  CloudUpload,
  Delete,
  Check,
  Warning,
} from "@mui/icons-material";

const ProfileSettings = () => {
  const [editMode, setEditMode] = useState(false);
  const [profile, setProfile] = useState({
    name: "Nguyễn Văn An",
    email: "nguyenvanan@email.com",
    phone: "+84 123 456 789",
    location: "Hồ Chí Minh, Việt Nam",
    bio: "Digital Marketing Specialist với 5 năm kinh nghiệm trong việc phát triển chiến lược content và quản lý mạng xã hội.",
    company: "Digital Marketing Agency",
    website: "https://marketing-pro.vn",
    skills: [
      "Content Marketing",
      "Social Media",
      "SEO",
      "Google Ads",
      "Analytics",
    ],
    preferences: {
      language: "vi",
      timezone: "Asia/Ho_Chi_Minh",
      emailNotifications: true,
      pushNotifications: true,
      marketingEmails: false,
      twoFactorAuth: true,
    },
  });

  const [formData, setFormData] = useState(profile);

  const handleEdit = () => {
    setEditMode(true);
    setFormData(profile);
  };

  const handleSave = () => {
    setProfile(formData);
    setEditMode(false);
  };

  const handleCancel = () => {
    setFormData(profile);
    setEditMode(false);
  };

  const handleInputChange = (field, value) => {
    setFormData((prev) => ({
      ...prev,
      [field]: value,
    }));
  };

  const handlePreferenceChange = (field, value) => {
    setFormData((prev) => ({
      ...prev,
      preferences: {
        ...prev.preferences,
        [field]: value,
      },
    }));
  };

  const addSkill = (skill) => {
    if (skill && !formData.skills.includes(skill)) {
      setFormData((prev) => ({
        ...prev,
        skills: [...prev.skills, skill],
      }));
    }
  };

  const removeSkill = (skillToRemove) => {
    setFormData((prev) => ({
      ...prev,
      skills: prev.skills.filter((skill) => skill !== skillToRemove),
    }));
  };

  return (
    <Box sx={{ maxWidth: 1000, mx: "auto", p: 3 }}>
      <Paper
        elevation={0}
        sx={{
          p: 4,
          borderRadius: 3,
          background:
            "linear-gradient(135deg, rgba(39, 40, 130, 0.05) 0%, rgba(90, 103, 216, 0.05) 100%)",
          border: "1px solid rgba(39, 40, 130, 0.1)",
        }}
      >
        {/* Header */}
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            mb: 4,
          }}
        >
          <Typography variant="h4" fontWeight="bold" color="primary.main">
            Cài Đặt Hồ Sơ
          </Typography>
          {!editMode ? (
            <Button
              variant="contained"
              startIcon={<Edit />}
              onClick={handleEdit}
              sx={{
                background: "linear-gradient(135deg, #272882 0%, #5a67d8 100%)",
                px: 3,
              }}
            >
              Chỉnh Sửa
            </Button>
          ) : (
            <Box sx={{ display: "flex", gap: 1 }}>
              <Button
                variant="outlined"
                startIcon={<Cancel />}
                onClick={handleCancel}
              >
                Hủy
              </Button>
              <Button
                variant="contained"
                startIcon={<Save />}
                onClick={handleSave}
                sx={{
                  background:
                    "linear-gradient(135deg, #272882 0%, #5a67d8 100%)",
                }}
              >
                Lưu
              </Button>
            </Box>
          )}
        </Box>

        <Grid container spacing={4}>
          {/* Avatar & Basic Info */}
          <Grid item xs={12} md={4}>
            <Card
              elevation={0}
              sx={{ border: "1px solid rgba(39, 40, 130, 0.1)" }}
            >
              <CardContent sx={{ textAlign: "center", p: 3 }}>
                <Avatar
                  sx={{
                    width: 120,
                    height: 120,
                    mx: "auto",
                    mb: 2,
                    background:
                      "linear-gradient(135deg, #272882 0%, #5a67d8 100%)",
                    fontSize: "2.5rem",
                  }}
                >
                  {profile.name.charAt(0)}
                </Avatar>

                {editMode && (
                  <Button
                    variant="outlined"
                    startIcon={<CloudUpload />}
                    size="small"
                    sx={{ mb: 2 }}
                  >
                    Đổi Ảnh
                  </Button>
                )}

                <Typography variant="h6" fontWeight="bold" gutterBottom>
                  {profile.name}
                </Typography>
                <Typography variant="body2" color="text.secondary" gutterBottom>
                  {profile.company}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  {profile.location}
                </Typography>
              </CardContent>
            </Card>

            {/* Security Status */}
            <Card
              elevation={0}
              sx={{
                mt: 2,
                border: "1px solid rgba(39, 40, 130, 0.1)",
                background: profile.preferences.twoFactorAuth
                  ? "linear-gradient(135deg, rgba(76, 175, 80, 0.1) 0%, rgba(139, 195, 74, 0.1) 100%)"
                  : "linear-gradient(135deg, rgba(255, 152, 0, 0.1) 0%, rgba(255, 193, 7, 0.1) 100%)",
              }}
            >
              <CardContent>
                <Box sx={{ display: "flex", alignItems: "center", mb: 1 }}>
                  {profile.preferences.twoFactorAuth ? (
                    <Check sx={{ color: "success.main", mr: 1 }} />
                  ) : (
                    <Warning sx={{ color: "warning.main", mr: 1 }} />
                  )}
                  <Typography variant="subtitle2" fontWeight="bold">
                    Bảo Mật Tài Khoản
                  </Typography>
                </Box>
                <Typography variant="body2" color="text.secondary">
                  {profile.preferences.twoFactorAuth
                    ? "Xác thực 2 bước đã được kích hoạt"
                    : "Nên kích hoạt xác thực 2 bước"}
                </Typography>
              </CardContent>
            </Card>
          </Grid>

          {/* Profile Details */}
          <Grid item xs={12} md={8}>
            <Grid container spacing={3}>
              {/* Basic Information */}
              <Grid item xs={12}>
                <Typography
                  variant="h6"
                  fontWeight="bold"
                  gutterBottom
                  sx={{ color: "primary.main" }}
                >
                  Thông Tin Cơ Bản
                </Typography>
                <Divider sx={{ mb: 3 }} />

                <Grid container spacing={2}>
                  <Grid item xs={12} sm={6}>
                    <TextField
                      fullWidth
                      label="Họ và Tên"
                      value={editMode ? formData.name : profile.name}
                      onChange={(e) =>
                        handleInputChange("name", e.target.value)
                      }
                      disabled={!editMode}
                      InputProps={{
                        startAdornment: (
                          <Person sx={{ color: "primary.main", mr: 1 }} />
                        ),
                      }}
                    />
                  </Grid>
                  <Grid item xs={12} sm={6}>
                    <TextField
                      fullWidth
                      label="Email"
                      value={editMode ? formData.email : profile.email}
                      onChange={(e) =>
                        handleInputChange("email", e.target.value)
                      }
                      disabled={!editMode}
                      InputProps={{
                        startAdornment: (
                          <Email sx={{ color: "primary.main", mr: 1 }} />
                        ),
                      }}
                    />
                  </Grid>
                  <Grid item xs={12} sm={6}>
                    <TextField
                      fullWidth
                      label="Số Điện Thoại"
                      value={editMode ? formData.phone : profile.phone}
                      onChange={(e) =>
                        handleInputChange("phone", e.target.value)
                      }
                      disabled={!editMode}
                      InputProps={{
                        startAdornment: (
                          <Phone sx={{ color: "primary.main", mr: 1 }} />
                        ),
                      }}
                    />
                  </Grid>
                  <Grid item xs={12} sm={6}>
                    <TextField
                      fullWidth
                      label="Địa Điểm"
                      value={editMode ? formData.location : profile.location}
                      onChange={(e) =>
                        handleInputChange("location", e.target.value)
                      }
                      disabled={!editMode}
                      InputProps={{
                        startAdornment: (
                          <Location sx={{ color: "primary.main", mr: 1 }} />
                        ),
                      }}
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField
                      fullWidth
                      label="Giới Thiệu"
                      multiline
                      rows={3}
                      value={editMode ? formData.bio : profile.bio}
                      onChange={(e) => handleInputChange("bio", e.target.value)}
                      disabled={!editMode}
                    />
                  </Grid>
                </Grid>
              </Grid>

              {/* Skills */}
              <Grid item xs={12}>
                <Typography
                  variant="h6"
                  fontWeight="bold"
                  gutterBottom
                  sx={{ color: "primary.main" }}
                >
                  Kỹ Năng
                </Typography>
                <Divider sx={{ mb: 3 }} />

                <Box sx={{ display: "flex", flexWrap: "wrap", gap: 1, mb: 2 }}>
                  {(editMode ? formData.skills : profile.skills).map(
                    (skill, index) => (
                      <Chip
                        key={index}
                        label={skill}
                        variant="outlined"
                        color="primary"
                        onDelete={
                          editMode ? () => removeSkill(skill) : undefined
                        }
                        sx={{
                          background:
                            "linear-gradient(135deg, rgba(39, 40, 130, 0.1) 0%, rgba(90, 103, 216, 0.1) 100%)",
                        }}
                      />
                    )
                  )}
                </Box>

                {editMode && (
                  <TextField
                    fullWidth
                    label="Thêm Kỹ Năng"
                    placeholder="Nhập kỹ năng và nhấn Enter"
                    onKeyPress={(e) => {
                      if (e.key === "Enter") {
                        addSkill(e.target.value);
                        e.target.value = "";
                      }
                    }}
                  />
                )}
              </Grid>

              {/* Preferences */}
              <Grid item xs={12}>
                <Typography
                  variant="h6"
                  fontWeight="bold"
                  gutterBottom
                  sx={{ color: "primary.main" }}
                >
                  Tùy Chọn
                </Typography>
                <Divider sx={{ mb: 3 }} />

                <List>
                  <ListItem>
                    <ListItemIcon>
                      <Language color="primary" />
                    </ListItemIcon>
                    <ListItemText
                      primary="Ngôn Ngữ"
                      secondary="Ngôn ngữ hiển thị giao diện"
                    />
                    <ListItemSecondaryAction>
                      <FormControl size="small" sx={{ minWidth: 120 }}>
                        <Select
                          value={
                            editMode
                              ? formData.preferences.language
                              : profile.preferences.language
                          }
                          onChange={(e) =>
                            handlePreferenceChange("language", e.target.value)
                          }
                          disabled={!editMode}
                        >
                          <MenuItem value="vi">Tiếng Việt</MenuItem>
                          <MenuItem value="en">English</MenuItem>
                        </Select>
                      </FormControl>
                    </ListItemSecondaryAction>
                  </ListItem>

                  <ListItem>
                    <ListItemIcon>
                      <Notifications color="primary" />
                    </ListItemIcon>
                    <ListItemText
                      primary="Thông Báo Email"
                      secondary="Nhận thông báo qua email"
                    />
                    <ListItemSecondaryAction>
                      <Switch
                        checked={
                          editMode
                            ? formData.preferences.emailNotifications
                            : profile.preferences.emailNotifications
                        }
                        onChange={(e) =>
                          handlePreferenceChange(
                            "emailNotifications",
                            e.target.checked
                          )
                        }
                        disabled={!editMode}
                        color="primary"
                      />
                    </ListItemSecondaryAction>
                  </ListItem>

                  <ListItem>
                    <ListItemIcon>
                      <Security color="primary" />
                    </ListItemIcon>
                    <ListItemText
                      primary="Xác Thực 2 Bước"
                      secondary="Tăng cường bảo mật tài khoản"
                    />
                    <ListItemSecondaryAction>
                      <Switch
                        checked={
                          editMode
                            ? formData.preferences.twoFactorAuth
                            : profile.preferences.twoFactorAuth
                        }
                        onChange={(e) =>
                          handlePreferenceChange(
                            "twoFactorAuth",
                            e.target.checked
                          )
                        }
                        disabled={!editMode}
                        color="primary"
                      />
                    </ListItemSecondaryAction>
                  </ListItem>
                </List>
              </Grid>
            </Grid>
          </Grid>
        </Grid>
      </Paper>
    </Box>
  );
};

export default ProfileSettings;
