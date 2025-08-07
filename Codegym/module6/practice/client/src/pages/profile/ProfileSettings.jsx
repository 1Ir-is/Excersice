import React, { useState } from "react";
import {
  Box,
  Grid,
  Typography,
  Card,
  CardContent,
  TextField,
  Button,
  Avatar,
  Switch,
  FormControlLabel,
  Divider,
  List,
  ListItem,
  ListItemText,
  ListItemSecondaryAction,
  IconButton,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Chip,
  Alert,
  MenuItem,
  Tabs,
  Tab,
  Paper,
  FormGroup,
} from "@mui/material";
import {
  Edit,
  PhotoCamera,
  Notifications,
  Security,
  Language,
  Palette,
  Delete,
  Save,
  Cancel,
  Visibility,
  VisibilityOff,
  Person,
  Business,
  Key,
  Mail,
  Phone,
} from "@mui/icons-material";

const ProfileSettings = () => {
  const [activeTab, setActiveTab] = useState(0);
  const [editMode, setEditMode] = useState(false);
  const [showDeleteDialog, setShowDeleteDialog] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const [successMessage, setSuccessMessage] = useState("");

  const [profile, setProfile] = useState({
    firstName: "Nguyễn",
    lastName: "Văn An",
    email: "nguyenvanan@email.com",
    phone: "+84 123 456 789",
    company: "Marketing Pro",
    position: "Marketing Manager",
    bio: "Chuyên gia marketing với 5 năm kinh nghiệm trong lĩnh vực marketing số.",
    avatar: "",
    timezone: "Asia/Ho_Chi_Minh",
    language: "vi",
  });

  const [notifications, setNotifications] = useState({
    emailNotifications: true,
    pushNotifications: true,
    campaignUpdates: true,
    weeklyReports: true,
    securityAlerts: true,
    newFeatures: false,
  });

  const [security, setSecurity] = useState({
    twoFactorAuth: false,
    loginAlerts: true,
    sessionTimeout: 30,
  });

  const [preferences, setPreferences] = useState({
    darkMode: false,
    autoSave: true,
    defaultView: "dashboard",
    itemsPerPage: 20,
  });

  const timezones = [
    { value: "Asia/Ho_Chi_Minh", label: "Việt Nam (GMT+7)" },
    { value: "Asia/Bangkok", label: "Thái Lan (GMT+7)" },
    { value: "Asia/Singapore", label: "Singapore (GMT+8)" },
    { value: "Asia/Tokyo", label: "Nhật Bản (GMT+9)" },
    { value: "Europe/London", label: "London (GMT+0)" },
    { value: "America/New_York", label: "New York (GMT-5)" },
  ];

  const languages = [
    { value: "vi", label: "Tiếng Việt" },
    { value: "en", label: "English" },
    { value: "ja", label: "日本語" },
    { value: "ko", label: "한국어" },
  ];

  const handleTabChange = (event, newValue) => {
    setActiveTab(newValue);
  };

  const handleProfileChange = (field) => (event) => {
    setProfile({ ...profile, [field]: event.target.value });
  };

  const handleNotificationChange = (field) => (event) => {
    setNotifications({ ...notifications, [field]: event.target.checked });
  };

  const handleSecurityChange = (field) => (event) => {
    setSecurity({ ...security, [field]: event.target.checked });
  };

  const handlePreferenceChange = (field) => (event) => {
    setPreferences({ ...preferences, [field]: event.target.checked });
  };

  const handleSave = () => {
    // Simulate API call
    setTimeout(() => {
      setSuccessMessage("Thông tin đã được cập nhật thành công!");
      setEditMode(false);
      setTimeout(() => setSuccessMessage(""), 3000);
    }, 500);
  };

  const handleDeleteAccount = () => {
    setShowDeleteDialog(false);
    // Handle account deletion
    console.log("Account deletion requested");
  };

  const connectedAccounts = [
    {
      platform: "Facebook",
      connected: true,
      email: "user@facebook.com",
      icon: "🔵",
    },
    {
      platform: "Instagram",
      connected: true,
      email: "user@instagram.com",
      icon: "🟣",
    },
    {
      platform: "LinkedIn",
      connected: false,
      email: "",
      icon: "🔷",
    },
    {
      platform: "Twitter",
      connected: false,
      email: "",
      icon: "🐦",
    },
  ];

  return (
    <Box>
      {/* Header */}
      <Box
        sx={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          mb: 4,
        }}
      >
        <Box>
          <Typography variant="h4" gutterBottom>
            Cài đặt tài khoản
          </Typography>
          <Typography variant="body1" color="text.secondary">
            Quản lý thông tin cá nhân và tùy chọn tài khoản
          </Typography>
        </Box>
        {editMode && (
          <Box sx={{ display: "flex", gap: 2 }}>
            <Button
              variant="outlined"
              startIcon={<Cancel />}
              onClick={() => setEditMode(false)}
            >
              Hủy
            </Button>
            <Button
              variant="contained"
              startIcon={<Save />}
              onClick={handleSave}
            >
              Lưu thay đổi
            </Button>
          </Box>
        )}
      </Box>

      {/* Success Message */}
      {successMessage && (
        <Alert severity="success" sx={{ mb: 3 }}>
          {successMessage}
        </Alert>
      )}

      {/* Tabs */}
      <Paper sx={{ mb: 3 }}>
        <Tabs value={activeTab} onChange={handleTabChange}>
          <Tab
            icon={<Person />}
            label="Thông tin cá nhân"
            iconPosition="start"
          />
          <Tab
            icon={<Notifications />}
            label="Thông báo"
            iconPosition="start"
          />
          <Tab icon={<Security />} label="Bảo mật" iconPosition="start" />
          <Tab icon={<Palette />} label="Tùy chọn" iconPosition="start" />
        </Tabs>
      </Paper>

      {/* Profile Tab */}
      {activeTab === 0 && (
        <Grid container spacing={3}>
          <Grid item xs={12} md={4}>
            <Card sx={{ p: 3, textAlign: "center" }}>
              <Avatar
                sx={{
                  width: 120,
                  height: 120,
                  mx: "auto",
                  mb: 2,
                  fontSize: "3rem",
                  bgcolor: "primary.main",
                }}
              >
                {profile.firstName[0]}
                {profile.lastName[0]}
              </Avatar>
              <Typography variant="h6" gutterBottom>
                {profile.firstName} {profile.lastName}
              </Typography>
              <Typography variant="body2" color="text.secondary" gutterBottom>
                {profile.position} tại {profile.company}
              </Typography>
              <Button
                variant="outlined"
                startIcon={<PhotoCamera />}
                sx={{ mt: 2 }}
              >
                Thay đổi ảnh
              </Button>
            </Card>
          </Grid>

          <Grid item xs={12} md={8}>
            <Card sx={{ p: 3 }}>
              <Box
                sx={{
                  display: "flex",
                  justifyContent: "space-between",
                  alignItems: "center",
                  mb: 3,
                }}
              >
                <Typography variant="h6">Thông tin cá nhân</Typography>
                {!editMode && (
                  <Button
                    variant="outlined"
                    startIcon={<Edit />}
                    onClick={() => setEditMode(true)}
                  >
                    Chỉnh sửa
                  </Button>
                )}
              </Box>

              <Grid container spacing={3}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Họ"
                    value={profile.firstName}
                    onChange={handleProfileChange("firstName")}
                    disabled={!editMode}
                    InputProps={{
                      startAdornment: (
                        <Person sx={{ mr: 1, color: "text.secondary" }} />
                      ),
                    }}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Tên"
                    value={profile.lastName}
                    onChange={handleProfileChange("lastName")}
                    disabled={!editMode}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Email"
                    type="email"
                    value={profile.email}
                    onChange={handleProfileChange("email")}
                    disabled={!editMode}
                    InputProps={{
                      startAdornment: (
                        <Mail sx={{ mr: 1, color: "text.secondary" }} />
                      ),
                    }}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Số điện thoại"
                    value={profile.phone}
                    onChange={handleProfileChange("phone")}
                    disabled={!editMode}
                    InputProps={{
                      startAdornment: (
                        <Phone sx={{ mr: 1, color: "text.secondary" }} />
                      ),
                    }}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Công ty"
                    value={profile.company}
                    onChange={handleProfileChange("company")}
                    disabled={!editMode}
                    InputProps={{
                      startAdornment: (
                        <Business sx={{ mr: 1, color: "text.secondary" }} />
                      ),
                    }}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Vị trí"
                    value={profile.position}
                    onChange={handleProfileChange("position")}
                    disabled={!editMode}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    select
                    fullWidth
                    label="Múi giờ"
                    value={profile.timezone}
                    onChange={handleProfileChange("timezone")}
                    disabled={!editMode}
                  >
                    {timezones.map((option) => (
                      <MenuItem key={option.value} value={option.value}>
                        {option.label}
                      </MenuItem>
                    ))}
                  </TextField>
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    select
                    fullWidth
                    label="Ngôn ngữ"
                    value={profile.language}
                    onChange={handleProfileChange("language")}
                    disabled={!editMode}
                  >
                    {languages.map((option) => (
                      <MenuItem key={option.value} value={option.value}>
                        {option.label}
                      </MenuItem>
                    ))}
                  </TextField>
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    fullWidth
                    multiline
                    rows={3}
                    label="Giới thiệu"
                    value={profile.bio}
                    onChange={handleProfileChange("bio")}
                    disabled={!editMode}
                    placeholder="Viết vài dòng giới thiệu về bản thân..."
                  />
                </Grid>
              </Grid>
            </Card>
          </Grid>

          {/* Connected Accounts */}
          <Grid item xs={12}>
            <Card sx={{ p: 3 }}>
              <Typography variant="h6" gutterBottom>
                Tài khoản liên kết
              </Typography>
              <List>
                {connectedAccounts.map((account, index) => (
                  <ListItem key={account.platform}>
                    <Box sx={{ display: "flex", alignItems: "center", mr: 2 }}>
                      <Typography sx={{ fontSize: "1.5rem", mr: 1 }}>
                        {account.icon}
                      </Typography>
                      <Box>
                        <Typography variant="subtitle1">
                          {account.platform}
                        </Typography>
                        {account.connected && (
                          <Typography variant="body2" color="text.secondary">
                            {account.email}
                          </Typography>
                        )}
                      </Box>
                    </Box>
                    <Box
                      sx={{
                        ml: "auto",
                        display: "flex",
                        alignItems: "center",
                        gap: 1,
                      }}
                    >
                      <Chip
                        label={
                          account.connected ? "Đã kết nối" : "Chưa kết nối"
                        }
                        color={account.connected ? "success" : "default"}
                        size="small"
                      />
                      <Button
                        variant={account.connected ? "outlined" : "contained"}
                        size="small"
                      >
                        {account.connected ? "Ngắt kết nối" : "Kết nối"}
                      </Button>
                    </Box>
                  </ListItem>
                ))}
              </List>
            </Card>
          </Grid>
        </Grid>
      )}

      {/* Notifications Tab */}
      {activeTab === 1 && (
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              Cài đặt thông báo
            </Typography>
            <FormGroup>
              <FormControlLabel
                control={
                  <Switch
                    checked={notifications.emailNotifications}
                    onChange={handleNotificationChange("emailNotifications")}
                  />
                }
                label="Thông báo qua email"
              />
              <FormControlLabel
                control={
                  <Switch
                    checked={notifications.pushNotifications}
                    onChange={handleNotificationChange("pushNotifications")}
                  />
                }
                label="Thông báo đẩy"
              />
              <FormControlLabel
                control={
                  <Switch
                    checked={notifications.campaignUpdates}
                    onChange={handleNotificationChange("campaignUpdates")}
                  />
                }
                label="Cập nhật chiến dịch"
              />
              <FormControlLabel
                control={
                  <Switch
                    checked={notifications.weeklyReports}
                    onChange={handleNotificationChange("weeklyReports")}
                  />
                }
                label="Báo cáo hàng tuần"
              />
              <FormControlLabel
                control={
                  <Switch
                    checked={notifications.securityAlerts}
                    onChange={handleNotificationChange("securityAlerts")}
                  />
                }
                label="Cảnh báo bảo mật"
              />
              <FormControlLabel
                control={
                  <Switch
                    checked={notifications.newFeatures}
                    onChange={handleNotificationChange("newFeatures")}
                  />
                }
                label="Tính năng mới"
              />
            </FormGroup>
          </CardContent>
        </Card>
      )}

      {/* Security Tab */}
      {activeTab === 2 && (
        <Grid container spacing={3}>
          <Grid item xs={12} md={6}>
            <Card sx={{ p: 3 }}>
              <Typography variant="h6" gutterBottom>
                Bảo mật tài khoản
              </Typography>
              <FormGroup sx={{ mb: 3 }}>
                <FormControlLabel
                  control={
                    <Switch
                      checked={security.twoFactorAuth}
                      onChange={handleSecurityChange("twoFactorAuth")}
                    />
                  }
                  label="Xác thực 2 bước"
                />
                <FormControlLabel
                  control={
                    <Switch
                      checked={security.loginAlerts}
                      onChange={handleSecurityChange("loginAlerts")}
                    />
                  }
                  label="Cảnh báo đăng nhập"
                />
              </FormGroup>

              <Button
                variant="outlined"
                startIcon={<Key />}
                fullWidth
                sx={{ mb: 2 }}
              >
                Thay đổi mật khẩu
              </Button>

              <Button
                variant="outlined"
                color="error"
                startIcon={<Delete />}
                fullWidth
                onClick={() => setShowDeleteDialog(true)}
              >
                Xóa tài khoản
              </Button>
            </Card>
          </Grid>

          <Grid item xs={12} md={6}>
            <Card sx={{ p: 3 }}>
              <Typography variant="h6" gutterBottom>
                Phiên đăng nhập
              </Typography>
              <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                Phiên hiện tại sẽ tự động đăng xuất sau khi không hoạt động
              </Typography>
              <TextField
                fullWidth
                label="Thời gian chờ (phút)"
                type="number"
                value={security.sessionTimeout}
                onChange={(e) =>
                  setSecurity({
                    ...security,
                    sessionTimeout: parseInt(e.target.value),
                  })
                }
                inputProps={{ min: 5, max: 120 }}
              />
            </Card>
          </Grid>
        </Grid>
      )}

      {/* Preferences Tab */}
      {activeTab === 3 && (
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              Tùy chọn giao diện
            </Typography>
            <FormGroup>
              <FormControlLabel
                control={
                  <Switch
                    checked={preferences.darkMode}
                    onChange={handlePreferenceChange("darkMode")}
                  />
                }
                label="Chế độ tối"
              />
              <FormControlLabel
                control={
                  <Switch
                    checked={preferences.autoSave}
                    onChange={handlePreferenceChange("autoSave")}
                  />
                }
                label="Tự động lưu"
              />
            </FormGroup>

            <Box sx={{ mt: 3 }}>
              <TextField
                select
                fullWidth
                label="Trang mặc định"
                value={preferences.defaultView}
                onChange={(e) =>
                  setPreferences({
                    ...preferences,
                    defaultView: e.target.value,
                  })
                }
                sx={{ mb: 2 }}
              >
                <MenuItem value="dashboard">Dashboard</MenuItem>
                <MenuItem value="campaigns">Chiến dịch</MenuItem>
                <MenuItem value="analytics">Thống kê</MenuItem>
                <MenuItem value="content">Nội dung</MenuItem>
              </TextField>

              <TextField
                select
                fullWidth
                label="Số mục trên trang"
                value={preferences.itemsPerPage}
                onChange={(e) =>
                  setPreferences({
                    ...preferences,
                    itemsPerPage: parseInt(e.target.value),
                  })
                }
              >
                <MenuItem value={10}>10</MenuItem>
                <MenuItem value={20}>20</MenuItem>
                <MenuItem value={50}>50</MenuItem>
                <MenuItem value={100}>100</MenuItem>
              </TextField>
            </Box>
          </CardContent>
        </Card>
      )}

      {/* Delete Account Dialog */}
      <Dialog
        open={showDeleteDialog}
        onClose={() => setShowDeleteDialog(false)}
      >
        <DialogTitle>Xác nhận xóa tài khoản</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Bạn có chắc chắn muốn xóa tài khoản? Hành động này không thể hoàn
            tác và tất cả dữ liệu của bạn sẽ bị xóa vĩnh viễn.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setShowDeleteDialog(false)}>Hủy</Button>
          <Button onClick={handleDeleteAccount} color="error" autoFocus>
            Xóa tài khoản
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default ProfileSettings;
