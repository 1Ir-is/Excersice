import React, { useState } from "react";
import {
  Box,
  Grid,
  Typography,
  Card,
  CardContent,
  Button,
  TextField,
  MenuItem,
  Chip,
  Avatar,
  IconButton,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  List,
  ListItem,
  ListItemText,
  ListItemAvatar,
  ListItemSecondaryAction,
  Tabs,
  Tab,
  FormControl,
  InputLabel,
  Select,
  OutlinedInput,
  FormControlLabel,
  Checkbox,
  Alert,
  Divider,
} from "@mui/material";
import {
  Add,
  Schedule,
  Edit,
  Delete,
  PlayArrow,
  AccessTime,
  Facebook,
  Instagram,
  LinkedIn,
  Twitter,
  MoreVert,
  Visibility,
} from "@mui/icons-material";
import { DateTimePicker } from "@mui/x-date-pickers/DateTimePicker";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import dayjs from "dayjs";

const PostScheduler = () => {
  const [activeTab, setActiveTab] = useState(0);
  const [openDialog, setOpenDialog] = useState(false);
  const [selectedPost, setSelectedPost] = useState(null);
  const [formData, setFormData] = useState({
    content: "",
    platforms: [],
    scheduledTime: dayjs().add(1, "hour"),
    recurring: false,
    recurringType: "daily",
    recurringEnd: dayjs().add(1, "month"),
  });

  const scheduledPosts = [
    {
      id: 1,
      content:
        "🎉 Khuyến mãi Black Friday - Giảm giá lên đến 70%! Đừng bỏ lỡ cơ hội vàng này...",
      platforms: ["Facebook", "Instagram"],
      scheduledTime: "2024-11-25 14:00",
      status: "scheduled",
      engagement: 0,
      reach: 0,
    },
    {
      id: 2,
      content:
        "📊 5 Tips Marketing Hiệu Quả cho Doanh Nghiệp Nhỏ:\n1. Hiểu rõ khách hàng mục tiêu...",
      platforms: ["LinkedIn", "Facebook"],
      scheduledTime: "2024-11-24 09:00",
      status: "published",
      engagement: 127,
      reach: 2340,
    },
    {
      id: 3,
      content:
        "🌟 Behind the scenes - Quy trình sản xuất sản phẩm của chúng tôi...",
      platforms: ["Instagram"],
      scheduledTime: "2024-11-23 16:30",
      status: "failed",
      engagement: 0,
      reach: 0,
      error: "Lỗi xác thực tài khoản Instagram",
    },
  ];

  const platformOptions = [
    {
      value: "Facebook",
      label: "Facebook",
      icon: <Facebook sx={{ color: "#1877F2" }} />,
    },
    {
      value: "Instagram",
      label: "Instagram",
      icon: <Instagram sx={{ color: "#E4405F" }} />,
    },
    {
      value: "LinkedIn",
      label: "LinkedIn",
      icon: <LinkedIn sx={{ color: "#0A66C2" }} />,
    },
    {
      value: "Twitter",
      label: "Twitter",
      icon: <Twitter sx={{ color: "#1DA1F2" }} />,
    },
  ];

  const recurringTypes = [
    { value: "daily", label: "Hàng ngày" },
    { value: "weekly", label: "Hàng tuần" },
    { value: "monthly", label: "Hàng tháng" },
  ];

  const getStatusColor = (status) => {
    switch (status) {
      case "scheduled":
        return "warning";
      case "published":
        return "success";
      case "failed":
        return "error";
      case "draft":
        return "default";
      default:
        return "default";
    }
  };

  const getStatusText = (status) => {
    switch (status) {
      case "scheduled":
        return "Đã lên lịch";
      case "published":
        return "Đã đăng";
      case "failed":
        return "Thất bại";
      case "draft":
        return "Bản nháp";
      default:
        return status;
    }
  };

  const handleTabChange = (event, newValue) => {
    setActiveTab(newValue);
  };

  const handleOpenDialog = (post = null) => {
    setSelectedPost(post);
    if (post) {
      setFormData({
        content: post.content,
        platforms: post.platforms,
        scheduledTime: dayjs(post.scheduledTime),
        recurring: false,
        recurringType: "daily",
        recurringEnd: dayjs().add(1, "month"),
      });
    } else {
      setFormData({
        content: "",
        platforms: [],
        scheduledTime: dayjs().add(1, "hour"),
        recurring: false,
        recurringType: "daily",
        recurringEnd: dayjs().add(1, "month"),
      });
    }
    setOpenDialog(true);
  };

  const handleCloseDialog = () => {
    setOpenDialog(false);
    setSelectedPost(null);
  };

  const handleChange = (e) => {
    const { name, value, checked } = e.target;
    setFormData({
      ...formData,
      [name]: name === "recurring" ? checked : value,
    });
  };

  const handlePlatformChange = (event) => {
    const value = event.target.value;
    setFormData({
      ...formData,
      platforms: typeof value === "string" ? value.split(",") : value,
    });
  };

  const handleSubmit = () => {
    // Handle create/update scheduled post
    console.log("Scheduled post data:", formData);
    handleCloseDialog();
  };

  const filteredPosts = scheduledPosts.filter((post) => {
    switch (activeTab) {
      case 0:
        return true; // Tất cả
      case 1:
        return post.status === "scheduled";
      case 2:
        return post.status === "published";
      case 3:
        return post.status === "failed";
      default:
        return true;
    }
  });

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
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
              Lập lịch đăng bài
            </Typography>
            <Typography variant="body1" color="text.secondary">
              Quản lý và lên lịch đăng bài tự động cho các nền tảng mạng xã hội
            </Typography>
          </Box>
          <Button
            variant="contained"
            startIcon={<Add />}
            onClick={() => handleOpenDialog()}
            sx={{
              background: "linear-gradient(135deg, #667eea 0%, #764ba2 100%)",
              "&:hover": {
                background: "linear-gradient(135deg, #5a6fd8 0%, #6a4190 100%)",
              },
            }}
          >
            Lên lịch bài mới
          </Button>
        </Box>

        {/* Stats Overview */}
        <Grid container spacing={3} sx={{ mb: 4 }}>
          <Grid item xs={12} sm={6} md={3}>
            <Card sx={{ p: 3, textAlign: "center" }}>
              <Schedule sx={{ fontSize: 40, color: "primary.main", mb: 1 }} />
              <Typography variant="h4" sx={{ fontWeight: "bold" }}>
                {scheduledPosts.filter((p) => p.status === "scheduled").length}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Đã lên lịch
              </Typography>
            </Card>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Card sx={{ p: 3, textAlign: "center" }}>
              <PlayArrow sx={{ fontSize: 40, color: "success.main", mb: 1 }} />
              <Typography variant="h4" sx={{ fontWeight: "bold" }}>
                {scheduledPosts.filter((p) => p.status === "published").length}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Đã đăng
              </Typography>
            </Card>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Card sx={{ p: 3, textAlign: "center" }}>
              <Visibility sx={{ fontSize: 40, color: "info.main", mb: 1 }} />
              <Typography variant="h4" sx={{ fontWeight: "bold" }}>
                {scheduledPosts
                  .reduce((sum, p) => sum + p.reach, 0)
                  .toLocaleString()}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Tổng lượt tiếp cận
              </Typography>
            </Card>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Card sx={{ p: 3, textAlign: "center" }}>
              <AccessTime sx={{ fontSize: 40, color: "warning.main", mb: 1 }} />
              <Typography variant="h4" sx={{ fontWeight: "bold" }}>
                {scheduledPosts.filter((p) => p.status === "failed").length}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Thất bại
              </Typography>
            </Card>
          </Grid>
        </Grid>

        {/* Tabs */}
        <Card sx={{ mb: 3 }}>
          <Tabs value={activeTab} onChange={handleTabChange}>
            <Tab label="Tất cả" />
            <Tab label="Đã lên lịch" />
            <Tab label="Đã đăng" />
            <Tab label="Thất bại" />
          </Tabs>
        </Card>

        {/* Posts List */}
        <Card>
          <CardContent>
            {filteredPosts.length === 0 ? (
              <Alert severity="info">
                Không có bài đăng nào trong danh mục này.
              </Alert>
            ) : (
              <List>
                {filteredPosts.map((post, index) => (
                  <Box key={post.id}>
                    <ListItem alignItems="flex-start">
                      <ListItemAvatar>
                        <Avatar>
                          <Schedule />
                        </Avatar>
                      </ListItemAvatar>
                      <ListItemText
                        primary={
                          <Box
                            sx={{
                              display: "flex",
                              alignItems: "center",
                              gap: 1,
                              mb: 1,
                            }}
                          >
                            <Typography
                              variant="subtitle1"
                              sx={{ fontWeight: 500 }}
                            >
                              {post.scheduledTime}
                            </Typography>
                            <Chip
                              label={getStatusText(post.status)}
                              size="small"
                              color={getStatusColor(post.status)}
                            />
                          </Box>
                        }
                        secondary={
                          <Box>
                            <Typography
                              variant="body2"
                              color="text.secondary"
                              sx={{
                                display: "-webkit-box",
                                WebkitLineClamp: 2,
                                WebkitBoxOrient: "vertical",
                                overflow: "hidden",
                                mb: 1,
                              }}
                            >
                              {post.content}
                            </Typography>
                            <Box
                              sx={{
                                display: "flex",
                                gap: 0.5,
                                flexWrap: "wrap",
                                mb: 1,
                              }}
                            >
                              {post.platforms.map((platform) => {
                                const platformData = platformOptions.find(
                                  (p) => p.value === platform
                                );
                                return (
                                  <Chip
                                    key={platform}
                                    icon={platformData?.icon}
                                    label={platform}
                                    size="small"
                                    variant="outlined"
                                  />
                                );
                              })}
                            </Box>
                            {post.status === "published" && (
                              <Typography
                                variant="caption"
                                color="text.secondary"
                              >
                                {post.engagement} tương tác •{" "}
                                {post.reach.toLocaleString()} lượt tiếp cận
                              </Typography>
                            )}
                            {post.status === "failed" && post.error && (
                              <Alert severity="error" sx={{ mt: 1 }}>
                                {post.error}
                              </Alert>
                            )}
                          </Box>
                        }
                      />
                      <ListItemSecondaryAction>
                        <Box
                          sx={{ display: "flex", alignItems: "center", gap: 1 }}
                        >
                          <IconButton onClick={() => handleOpenDialog(post)}>
                            <Edit />
                          </IconButton>
                          {post.status === "scheduled" && (
                            <IconButton color="primary">
                              <PlayArrow />
                            </IconButton>
                          )}
                          <IconButton color="error">
                            <Delete />
                          </IconButton>
                          <IconButton>
                            <MoreVert />
                          </IconButton>
                        </Box>
                      </ListItemSecondaryAction>
                    </ListItem>
                    {index < filteredPosts.length - 1 && <Divider />}
                  </Box>
                ))}
              </List>
            )}
          </CardContent>
        </Card>

        {/* Schedule Dialog */}
        <Dialog
          open={openDialog}
          onClose={handleCloseDialog}
          maxWidth="md"
          fullWidth
        >
          <DialogTitle>
            {selectedPost ? "Chỉnh sửa lịch đăng bài" : "Lên lịch bài đăng mới"}
          </DialogTitle>
          <DialogContent>
            <Grid container spacing={2} sx={{ mt: 1 }}>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  label="Nội dung bài đăng"
                  name="content"
                  value={formData.content}
                  onChange={handleChange}
                  multiline
                  rows={4}
                  required
                />
              </Grid>

              <Grid item xs={12}>
                <FormControl fullWidth>
                  <InputLabel>Nền tảng</InputLabel>
                  <Select
                    multiple
                    value={formData.platforms}
                    onChange={handlePlatformChange}
                    input={<OutlinedInput label="Nền tảng" />}
                    renderValue={(selected) => (
                      <Box sx={{ display: "flex", flexWrap: "wrap", gap: 0.5 }}>
                        {selected.map((value) => {
                          const platform = platformOptions.find(
                            (p) => p.value === value
                          );
                          return (
                            <Chip
                              key={value}
                              icon={platform?.icon}
                              label={value}
                              size="small"
                            />
                          );
                        })}
                      </Box>
                    )}
                  >
                    {platformOptions.map((platform) => (
                      <MenuItem key={platform.value} value={platform.value}>
                        <Box
                          sx={{ display: "flex", alignItems: "center", gap: 1 }}
                        >
                          {platform.icon}
                          {platform.label}
                        </Box>
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
              </Grid>

              <Grid item xs={12} sm={6}>
                <DateTimePicker
                  label="Thời gian đăng"
                  value={formData.scheduledTime}
                  onChange={(newValue) =>
                    setFormData({ ...formData, scheduledTime: newValue })
                  }
                  slotProps={{ textField: { fullWidth: true } }}
                />
              </Grid>

              <Grid item xs={12} sm={6}>
                <FormControlLabel
                  control={
                    <Checkbox
                      name="recurring"
                      checked={formData.recurring}
                      onChange={handleChange}
                    />
                  }
                  label="Lặp lại"
                />
              </Grid>

              {formData.recurring && (
                <>
                  <Grid item xs={12} sm={6}>
                    <TextField
                      fullWidth
                      select
                      label="Loại lặp lại"
                      name="recurringType"
                      value={formData.recurringType}
                      onChange={handleChange}
                    >
                      {recurringTypes.map((type) => (
                        <MenuItem key={type.value} value={type.value}>
                          {type.label}
                        </MenuItem>
                      ))}
                    </TextField>
                  </Grid>
                  <Grid item xs={12} sm={6}>
                    <DateTimePicker
                      label="Kết thúc lặp lại"
                      value={formData.recurringEnd}
                      onChange={(newValue) =>
                        setFormData({ ...formData, recurringEnd: newValue })
                      }
                      slotProps={{ textField: { fullWidth: true } }}
                    />
                  </Grid>
                </>
              )}
            </Grid>
          </DialogContent>
          <DialogActions>
            <Button onClick={handleCloseDialog}>Hủy</Button>
            <Button
              onClick={handleSubmit}
              variant="contained"
              disabled={!formData.content || formData.platforms.length === 0}
              sx={{
                background: "linear-gradient(135deg, #667eea 0%, #764ba2 100%)",
                "&:hover": {
                  background:
                    "linear-gradient(135deg, #5a6fd8 0%, #6a4190 100%)",
                },
              }}
            >
              {selectedPost ? "Cập nhật" : "Lên lịch"}
            </Button>
          </DialogActions>
        </Dialog>
      </Box>
    </LocalizationProvider>
  );
};

export default PostScheduler;
