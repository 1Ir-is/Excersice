import React, { useState } from "react";
import {
  Box,
  Grid,
  Typography,
  Card,
  CardContent,
  Button,
  Switch,
  FormControlLabel,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Avatar,
  Chip,
  Alert,
  List,
  ListItem,
  ListItemText,
  ListItemAvatar,
  ListItemSecondaryAction,
  IconButton,
  Divider,
} from "@mui/material";
import {
  Add,
  Delete,
  CheckCircle,
  Warning,
  Facebook,
  Instagram,
  LinkedIn,
  Twitter,
  YouTube,
  Refresh,
} from "@mui/icons-material";

const SocialMediaConfig = () => {
  const [openDialog, setOpenDialog] = useState(false);
  const [selectedPlatform, setSelectedPlatform] = useState("");
  const [accounts, setAccounts] = useState([
    {
      id: 1,
      platform: "Facebook",
      name: "Công ty ABC - Fanpage",
      username: "@congtyabc",
      avatar: "/api/placeholder/40/40",
      status: "connected",
      followers: "15.2K",
      lastSync: "2 phút trước",
      autoPost: true,
      icon: <Facebook sx={{ color: "#1877F2" }} />,
    },
    {
      id: 2,
      platform: "Instagram",
      name: "congtyabc_official",
      username: "@congtyabc_official",
      avatar: "/api/placeholder/40/40",
      status: "connected",
      followers: "8.5K",
      lastSync: "5 phút trước",
      autoPost: true,
      icon: <Instagram sx={{ color: "#E4405F" }} />,
    },
    {
      id: 3,
      platform: "LinkedIn",
      name: "Công ty ABC",
      username: "cong-ty-abc",
      avatar: "/api/placeholder/40/40",
      status: "error",
      followers: "3.1K",
      lastSync: "2 giờ trước",
      autoPost: false,
      icon: <LinkedIn sx={{ color: "#0A66C2" }} />,
    },
  ]);

  const availablePlatforms = [
    {
      name: "Facebook",
      icon: <Facebook sx={{ color: "#1877F2", fontSize: 32 }} />,
      description: "Kết nối với Facebook Pages và Groups",
      features: ["Đăng bài tự động", "Lập lịch", "Phân tích"],
    },
    {
      name: "Instagram",
      icon: <Instagram sx={{ color: "#E4405F", fontSize: 32 }} />,
      description: "Đăng ảnh và stories lên Instagram",
      features: ["Đăng ảnh", "Stories", "Reels"],
    },
    {
      name: "LinkedIn",
      icon: <LinkedIn sx={{ color: "#0A66C2", fontSize: 32 }} />,
      description: "Chia sẻ nội dung chuyên nghiệp",
      features: ["Đăng bài", "Bài viết", "Mạng lưới"],
    },
    {
      name: "Twitter",
      icon: <Twitter sx={{ color: "#1DA1F2", fontSize: 32 }} />,
      description: "Đăng tweets và threads",
      features: ["Tweets", "Threads", "Retweets"],
    },
    {
      name: "YouTube",
      icon: <YouTube sx={{ color: "#FF0000", fontSize: 32 }} />,
      description: "Quản lý video và channel",
      features: ["Upload video", "Shorts", "Playlist"],
    },
  ];

  const getStatusColor = (status) => {
    switch (status) {
      case "connected":
        return "success";
      case "error":
        return "error";
      case "warning":
        return "warning";
      default:
        return "default";
    }
  };

  const getStatusText = (status) => {
    switch (status) {
      case "connected":
        return "Đã kết nối";
      case "error":
        return "Lỗi kết nối";
      case "warning":
        return "Cần xác thực";
      default:
        return status;
    }
  };

  const getStatusIcon = (status) => {
    switch (status) {
      case "connected":
        return <CheckCircle sx={{ color: "success.main" }} />;
      case "error":
        return <Warning sx={{ color: "error.main" }} />;
      case "warning":
        return <Warning sx={{ color: "warning.main" }} />;
      default:
        return null;
    }
  };

  const handleConnectPlatform = (platform) => {
    setSelectedPlatform(platform);
    setOpenDialog(true);
  };

  const handleCloseDialog = () => {
    setOpenDialog(false);
    setSelectedPlatform("");
  };

  const handleConnect = () => {
    // Simulate connection process
    console.log(`Connecting to ${selectedPlatform}`);
    handleCloseDialog();
  };

  const handleToggleAutoPost = (accountId) => {
    setAccounts(
      accounts.map((account) =>
        account.id === accountId
          ? { ...account, autoPost: !account.autoPost }
          : account
      )
    );
  };

  const handleRefreshAccount = (accountId) => {
    // Simulate refresh
    console.log(`Refreshing account ${accountId}`);
  };

  const handleDeleteAccount = (accountId) => {
    setAccounts(accounts.filter((account) => account.id !== accountId));
  };

  return (
    <Box>
      {/* Header */}
      <Box sx={{ mb: 4 }}>
        <Typography variant="h4" gutterBottom>
          Cấu hình mạng xã hội
        </Typography>
        <Typography variant="body1" color="text.secondary">
          Kết nối và quản lý các tài khoản mạng xã hội của bạn
        </Typography>
      </Box>

      {/* Connected Accounts */}
      <Card sx={{ mb: 4 }}>
        <CardContent>
          <Box
            sx={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
              mb: 3,
            }}
          >
            <Typography variant="h6">Tài khoản đã kết nối</Typography>
            <Typography variant="body2" color="text.secondary">
              {accounts.length} tài khoản
            </Typography>
          </Box>

          {accounts.length === 0 ? (
            <Alert severity="info">
              Chưa có tài khoản nào được kết nối. Hãy thêm tài khoản đầu tiên!
            </Alert>
          ) : (
            <List>
              {accounts.map((account, index) => (
                <Box key={account.id}>
                  <ListItem>
                    <ListItemAvatar>
                      <Avatar src={account.avatar}>{account.icon}</Avatar>
                    </ListItemAvatar>
                    <ListItemText
                      primary={
                        <Box
                          sx={{ display: "flex", alignItems: "center", gap: 1 }}
                        >
                          <Typography
                            variant="subtitle1"
                            sx={{ fontWeight: 500 }}
                          >
                            {account.name}
                          </Typography>
                          <Chip
                            label={getStatusText(account.status)}
                            size="small"
                            color={getStatusColor(account.status)}
                            icon={getStatusIcon(account.status)}
                          />
                        </Box>
                      }
                      secondary={
                        <Box sx={{ mt: 1 }}>
                          <Typography variant="body2" color="text.secondary">
                            {account.username} • {account.followers} người theo
                            dõi
                          </Typography>
                          <Typography variant="caption" color="text.secondary">
                            Đồng bộ lần cuối: {account.lastSync}
                          </Typography>
                        </Box>
                      }
                    />
                    <ListItemSecondaryAction>
                      <Box
                        sx={{ display: "flex", alignItems: "center", gap: 1 }}
                      >
                        <FormControlLabel
                          control={
                            <Switch
                              checked={account.autoPost}
                              onChange={() => handleToggleAutoPost(account.id)}
                              size="small"
                            />
                          }
                          label="Tự động đăng"
                          sx={{ mr: 2 }}
                        />
                        <IconButton
                          onClick={() => handleRefreshAccount(account.id)}
                        >
                          <Refresh />
                        </IconButton>
                        <IconButton
                          onClick={() => handleDeleteAccount(account.id)}
                          color="error"
                        >
                          <Delete />
                        </IconButton>
                      </Box>
                    </ListItemSecondaryAction>
                  </ListItem>
                  {index < accounts.length - 1 && <Divider />}
                </Box>
              ))}
            </List>
          )}
        </CardContent>
      </Card>

      {/* Available Platforms */}
      <Card>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            Thêm tài khoản mới
          </Typography>
          <Typography variant="body2" color="text.secondary" sx={{ mb: 3 }}>
            Chọn nền tảng mạng xã hội để kết nối
          </Typography>

          <Grid container spacing={3}>
            {availablePlatforms.map((platform) => (
              <Grid item xs={12} sm={6} md={4} key={platform.name}>
                <Card
                  sx={{
                    p: 3,
                    height: "100%",
                    border: "1px solid",
                    borderColor: "grey.200",
                    cursor: "pointer",
                    "&:hover": {
                      boxShadow: "0 4px 12px rgba(0,0,0,0.1)",
                      transform: "translateY(-2px)",
                      transition: "all 0.3s ease",
                    },
                  }}
                  onClick={() => handleConnectPlatform(platform.name)}
                >
                  <Box sx={{ textAlign: "center", mb: 2 }}>{platform.icon}</Box>
                  <Typography variant="h6" align="center" gutterBottom>
                    {platform.name}
                  </Typography>
                  <Typography
                    variant="body2"
                    color="text.secondary"
                    align="center"
                    sx={{ mb: 2 }}
                  >
                    {platform.description}
                  </Typography>
                  <Box sx={{ mb: 3 }}>
                    {platform.features.map((feature) => (
                      <Chip
                        key={feature}
                        label={feature}
                        size="small"
                        variant="outlined"
                        sx={{ m: 0.5 }}
                      />
                    ))}
                  </Box>
                  <Button
                    fullWidth
                    variant="outlined"
                    startIcon={<Add />}
                    onClick={() => handleConnectPlatform(platform.name)}
                  >
                    Kết nối
                  </Button>
                </Card>
              </Grid>
            ))}
          </Grid>
        </CardContent>
      </Card>

      {/* Connect Dialog */}
      <Dialog
        open={openDialog}
        onClose={handleCloseDialog}
        maxWidth="sm"
        fullWidth
      >
        <DialogTitle>Kết nối với {selectedPlatform}</DialogTitle>
        <DialogContent>
          <Alert severity="info" sx={{ mb: 3 }}>
            Bạn sẽ được chuyển hướng đến {selectedPlatform} để xác thực tài
            khoản. Vui lòng đăng nhập và cấp quyền cho ứng dụng.
          </Alert>

          <Typography variant="body1" gutterBottom>
            Quyền cần thiết:
          </Typography>
          <Box component="ul" sx={{ pl: 2 }}>
            <li>Đọc thông tin hồ sơ cơ bản</li>
            <li>Đăng bài viết thay mặt bạn</li>
            <li>Quản lý và lập lịch nội dung</li>
            <li>Xem thống kê tương tác</li>
          </Box>

          <Alert severity="warning" sx={{ mt: 2 }}>
            Chúng tôi sẽ không bao giờ truy cập thông tin cá nhân hoặc thực hiện
            hành động mà không có sự đồng ý của bạn.
          </Alert>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDialog}>Hủy</Button>
          <Button
            onClick={handleConnect}
            variant="contained"
            sx={{
              background: "linear-gradient(135deg, #667eea 0%, #764ba2 100%)",
              "&:hover": {
                background: "linear-gradient(135deg, #5a6fd8 0%, #6a4190 100%)",
              },
            }}
          >
            Tiếp tục kết nối
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default SocialMediaConfig;
