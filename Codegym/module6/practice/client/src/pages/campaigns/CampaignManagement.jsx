import React, { useState } from "react";
import {
  Box,
  Grid,
  Typography,
  Card,
  Button,
  Chip,
  IconButton,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  MenuItem,
  Tab,
  Tabs,
  LinearProgress,
} from "@mui/material";
import {
  Add,
  Edit,
  PlayArrow,
  Pause,
  MoreVert,
  Campaign,
  TrendingUp,
  Visibility,
} from "@mui/icons-material";

const CampaignManagement = () => {
  const [activeTab, setActiveTab] = useState(0);
  const [openDialog, setOpenDialog] = useState(false);
  const [selectedCampaign, setSelectedCampaign] = useState(null);
  const [formData, setFormData] = useState({
    name: "",
    description: "",
    type: "",
    targetAudience: "",
    budget: "",
    startDate: "",
    endDate: "",
  });

  const campaigns = [
    {
      id: 1,
      name: "Khuyến mãi Black Friday 2024",
      description: "Chiến dịch khuyến mãi lớn nhất trong năm",
      type: "Khuyến mãi",
      status: "active",
      progress: 75,
      budget: "10,000,000đ",
      spent: "7,500,000đ",
      startDate: "2024-11-15",
      endDate: "2024-11-30",
      posts: 45,
      engagement: "2.1K",
      reach: "25.5K",
      platforms: ["Facebook", "Instagram", "LinkedIn"],
    },
    {
      id: 2,
      name: "Giới thiệu sản phẩm mới",
      description: "Ra mắt dòng sản phẩm công nghệ mới",
      type: "Sản phẩm",
      status: "draft",
      progress: 25,
      budget: "5,000,000đ",
      spent: "0đ",
      startDate: "2024-12-01",
      endDate: "2024-12-31",
      posts: 0,
      engagement: "0",
      reach: "0",
      platforms: ["Facebook", "Instagram"],
    },
    {
      id: 3,
      name: "Tăng nhận thức thương hiệu",
      description: "Xây dựng và củng cố hình ảnh thương hiệu",
      type: "Thương hiệu",
      status: "completed",
      progress: 100,
      budget: "15,000,000đ",
      spent: "14,200,000đ",
      startDate: "2024-09-01",
      endDate: "2024-10-31",
      posts: 67,
      engagement: "5.8K",
      reach: "48.2K",
      platforms: ["Facebook", "Instagram", "LinkedIn", "Twitter"],
    },
  ];

  const campaignTypes = [
    "Khuyến mãi",
    "Sản phẩm",
    "Thương hiệu",
    "Sự kiện",
    "Giáo dục",
    "Tuyển dụng",
  ];

  const getStatusColor = (status) => {
    switch (status) {
      case "active":
        return "success";
      case "draft":
        return "warning";
      case "completed":
        return "info";
      case "paused":
        return "default";
      default:
        return "default";
    }
  };

  const getStatusText = (status) => {
    switch (status) {
      case "active":
        return "Đang chạy";
      case "draft":
        return "Bản nháp";
      case "completed":
        return "Hoàn thành";
      case "paused":
        return "Tạm dừng";
      default:
        return status;
    }
  };

  const handleTabChange = (event, newValue) => {
    setActiveTab(newValue);
  };

  const handleOpenDialog = (campaign = null) => {
    setSelectedCampaign(campaign);
    if (campaign) {
      setFormData({
        name: campaign.name,
        description: campaign.description,
        type: campaign.type,
        targetAudience: campaign.targetAudience || "",
        budget: campaign.budget,
        startDate: campaign.startDate,
        endDate: campaign.endDate,
      });
    } else {
      setFormData({
        name: "",
        description: "",
        type: "",
        targetAudience: "",
        budget: "",
        startDate: "",
        endDate: "",
      });
    }
    setOpenDialog(true);
  };

  const handleCloseDialog = () => {
    setOpenDialog(false);
    setSelectedCampaign(null);
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = () => {
    // Handle create/update campaign
    console.log("Campaign data:", formData);
    handleCloseDialog();
  };

  const filteredCampaigns = campaigns.filter((campaign) => {
    switch (activeTab) {
      case 0:
        return true; // Tất cả
      case 1:
        return campaign.status === "active";
      case 2:
        return campaign.status === "draft";
      case 3:
        return campaign.status === "completed";
      default:
        return true;
    }
  });

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
            Quản lý chiến dịch
          </Typography>
          <Typography variant="body1" color="text.secondary">
            Tạo và quản lý các chiến dịch marketing của bạn
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
          Tạo chiến dịch mới
        </Button>
      </Box>

      {/* Stats Overview */}
      <Grid container spacing={3} sx={{ mb: 4 }}>
        <Grid item xs={12} sm={6} md={3}>
          <Card sx={{ p: 3, textAlign: "center" }}>
            <Campaign sx={{ fontSize: 40, color: "primary.main", mb: 1 }} />
            <Typography variant="h4" sx={{ fontWeight: "bold" }}>
              {campaigns.length}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              Tổng chiến dịch
            </Typography>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Card sx={{ p: 3, textAlign: "center" }}>
            <PlayArrow sx={{ fontSize: 40, color: "success.main", mb: 1 }} />
            <Typography variant="h4" sx={{ fontWeight: "bold" }}>
              {campaigns.filter((c) => c.status === "active").length}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              Đang chạy
            </Typography>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Card sx={{ p: 3, textAlign: "center" }}>
            <TrendingUp sx={{ fontSize: 40, color: "warning.main", mb: 1 }} />
            <Typography variant="h4" sx={{ fontWeight: "bold" }}>
              {campaigns
                .reduce(
                  (sum, c) =>
                    sum +
                    parseInt(c.engagement.replace("K", "000").replace(",", "")),
                  0
                )
                .toLocaleString()}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              Tổng tương tác
            </Typography>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Card sx={{ p: 3, textAlign: "center" }}>
            <Visibility sx={{ fontSize: 40, color: "info.main", mb: 1 }} />
            <Typography variant="h4" sx={{ fontWeight: "bold" }}>
              {campaigns
                .reduce(
                  (sum, c) =>
                    sum +
                    parseFloat(c.reach.replace("K", "000").replace(",", "")),
                  0
                )
                .toLocaleString()}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              Tổng lượt tiếp cận
            </Typography>
          </Card>
        </Grid>
      </Grid>

      {/* Tabs */}
      <Card sx={{ mb: 3 }}>
        <Tabs value={activeTab} onChange={handleTabChange}>
          <Tab label="Tất cả" />
          <Tab label="Đang chạy" />
          <Tab label="Bản nháp" />
          <Tab label="Hoàn thành" />
        </Tabs>
      </Card>

      {/* Campaign List */}
      <Grid container spacing={3}>
        {filteredCampaigns.map((campaign) => (
          <Grid item xs={12} md={6} lg={4} key={campaign.id}>
            <Card
              sx={{
                p: 3,
                height: "100%",
                border: "1px solid",
                borderColor: "grey.200",
                "&:hover": {
                  boxShadow: "0 8px 25px rgba(0,0,0,0.1)",
                  transform: "translateY(-2px)",
                  transition: "all 0.3s ease",
                },
              }}
            >
              {/* Header */}
              <Box
                sx={{
                  display: "flex",
                  justifyContent: "space-between",
                  alignItems: "flex-start",
                  mb: 2,
                }}
              >
                <Box sx={{ flex: 1 }}>
                  <Typography variant="h6" sx={{ fontWeight: "bold", mb: 1 }}>
                    {campaign.name}
                  </Typography>
                  <Typography
                    variant="body2"
                    color="text.secondary"
                    sx={{ mb: 2 }}
                  >
                    {campaign.description}
                  </Typography>
                  <Box sx={{ display: "flex", gap: 1, mb: 2 }}>
                    <Chip
                      label={campaign.type}
                      size="small"
                      variant="outlined"
                    />
                    <Chip
                      label={getStatusText(campaign.status)}
                      size="small"
                      color={getStatusColor(campaign.status)}
                    />
                  </Box>
                </Box>
                <IconButton size="small">
                  <MoreVert />
                </IconButton>
              </Box>

              {/* Progress */}
              <Box sx={{ mb: 2 }}>
                <Box
                  sx={{
                    display: "flex",
                    justifyContent: "space-between",
                    mb: 1,
                  }}
                >
                  <Typography variant="body2">Tiến độ</Typography>
                  <Typography variant="body2">{campaign.progress}%</Typography>
                </Box>
                <LinearProgress
                  variant="determinate"
                  value={campaign.progress}
                />
              </Box>

              {/* Stats */}
              <Grid container spacing={2} sx={{ mb: 2 }}>
                <Grid item xs={6}>
                  <Typography variant="caption" color="text.secondary">
                    Ngân sách
                  </Typography>
                  <Typography variant="body2" sx={{ fontWeight: 500 }}>
                    {campaign.budget}
                  </Typography>
                </Grid>
                <Grid item xs={6}>
                  <Typography variant="caption" color="text.secondary">
                    Đã chi
                  </Typography>
                  <Typography variant="body2" sx={{ fontWeight: 500 }}>
                    {campaign.spent}
                  </Typography>
                </Grid>
                <Grid item xs={6}>
                  <Typography variant="caption" color="text.secondary">
                    Bài đăng
                  </Typography>
                  <Typography variant="body2" sx={{ fontWeight: 500 }}>
                    {campaign.posts}
                  </Typography>
                </Grid>
                <Grid item xs={6}>
                  <Typography variant="caption" color="text.secondary">
                    Tương tác
                  </Typography>
                  <Typography variant="body2" sx={{ fontWeight: 500 }}>
                    {campaign.engagement}
                  </Typography>
                </Grid>
              </Grid>

              {/* Platforms */}
              <Box sx={{ mb: 3 }}>
                <Typography
                  variant="caption"
                  color="text.secondary"
                  sx={{ display: "block", mb: 1 }}
                >
                  Nền tảng
                </Typography>
                <Box sx={{ display: "flex", gap: 0.5, flexWrap: "wrap" }}>
                  {campaign.platforms.map((platform) => (
                    <Chip key={platform} label={platform} size="small" />
                  ))}
                </Box>
              </Box>

              {/* Actions */}
              <Box sx={{ display: "flex", gap: 1 }}>
                <Button
                  size="small"
                  variant="outlined"
                  startIcon={<Edit />}
                  onClick={() => handleOpenDialog(campaign)}
                >
                  Chỉnh sửa
                </Button>
                {campaign.status === "active" ? (
                  <Button size="small" variant="outlined" startIcon={<Pause />}>
                    Tạm dừng
                  </Button>
                ) : campaign.status === "draft" ? (
                  <Button
                    size="small"
                    variant="contained"
                    startIcon={<PlayArrow />}
                  >
                    Bắt đầu
                  </Button>
                ) : null}
              </Box>
            </Card>
          </Grid>
        ))}
      </Grid>

      {/* Create/Edit Dialog */}
      <Dialog
        open={openDialog}
        onClose={handleCloseDialog}
        maxWidth="md"
        fullWidth
      >
        <DialogTitle>
          {selectedCampaign ? "Chỉnh sửa chiến dịch" : "Tạo chiến dịch mới"}
        </DialogTitle>
        <DialogContent>
          <Grid container spacing={2} sx={{ mt: 1 }}>
            <Grid item xs={12}>
              <TextField
                fullWidth
                label="Tên chiến dịch"
                name="name"
                value={formData.name}
                onChange={handleChange}
                required
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                label="Mô tả"
                name="description"
                value={formData.description}
                onChange={handleChange}
                multiline
                rows={3}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                fullWidth
                select
                label="Loại chiến dịch"
                name="type"
                value={formData.type}
                onChange={handleChange}
                required
              >
                {campaignTypes.map((type) => (
                  <MenuItem key={type} value={type}>
                    {type}
                  </MenuItem>
                ))}
              </TextField>
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                fullWidth
                label="Ngân sách"
                name="budget"
                value={formData.budget}
                onChange={handleChange}
                placeholder="VD: 10,000,000đ"
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                fullWidth
                label="Ngày bắt đầu"
                name="startDate"
                type="date"
                value={formData.startDate}
                onChange={handleChange}
                InputLabelProps={{ shrink: true }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                fullWidth
                label="Ngày kết thúc"
                name="endDate"
                type="date"
                value={formData.endDate}
                onChange={handleChange}
                InputLabelProps={{ shrink: true }}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                label="Đối tượng mục tiêu"
                name="targetAudience"
                value={formData.targetAudience}
                onChange={handleChange}
                placeholder="VD: Nam, 25-35 tuổi, quan tâm công nghệ"
                multiline
                rows={2}
              />
            </Grid>
          </Grid>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDialog}>Hủy</Button>
          <Button
            onClick={handleSubmit}
            variant="contained"
            sx={{
              background: "linear-gradient(135deg, #667eea 0%, #764ba2 100%)",
              "&:hover": {
                background: "linear-gradient(135deg, #5a6fd8 0%, #6a4190 100%)",
              },
            }}
          >
            {selectedCampaign ? "Cập nhật" : "Tạo chiến dịch"}
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default CampaignManagement;
