import React, { useState } from "react";
import {
  Box,
  Grid,
  Typography,
  Card,
  CardContent,
  Button,
  MenuItem,
  TextField,
  Chip,
  Avatar,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Tabs,
  Tab,
  List,
  ListItem,
  ListItemText,
  ListItemAvatar,
} from "@mui/material";
import {
  TrendingUp,
  TrendingDown,
  Visibility,
  ThumbUp,
  Share,
  People,
  Timeline,
  BarChart,
  PieChart,
  Facebook,
  Instagram,
  LinkedIn,
  Twitter,
  Download,
} from "@mui/icons-material";

const Analytics = () => {
  const [activeTab, setActiveTab] = useState(0);
  const [timeRange, setTimeRange] = useState("7days");
  const [selectedPlatform, setSelectedPlatform] = useState("all");

  const timeRanges = [
    { value: "7days", label: "7 ngày qua" },
    { value: "30days", label: "30 ngày qua" },
    { value: "3months", label: "3 tháng qua" },
    { value: "1year", label: "1 năm qua" },
  ];

  const platforms = [
    { value: "all", label: "Tất cả nền tảng" },
    { value: "facebook", label: "Facebook" },
    { value: "instagram", label: "Instagram" },
    { value: "linkedin", label: "LinkedIn" },
    { value: "twitter", label: "Twitter" },
  ];

  const overviewStats = [
    {
      title: "Tổng lượt tiếp cận",
      value: "125.4K",
      change: "+12.5%",
      trend: "up",
      icon: <Visibility sx={{ fontSize: 40 }} />,
      color: "#3B82F6",
    },
    {
      title: "Tương tác",
      value: "8.2K",
      change: "+8.3%",
      trend: "up",
      icon: <ThumbUp sx={{ fontSize: 40 }} />,
      color: "#10B981",
    },
    {
      title: "Lượt chia sẻ",
      value: "1.5K",
      change: "-2.1%",
      trend: "down",
      icon: <Share sx={{ fontSize: 40 }} />,
      color: "#8B5CF6",
    },
    {
      title: "Người theo dõi mới",
      value: "324",
      change: "+15.7%",
      trend: "up",
      icon: <People sx={{ fontSize: 40 }} />,
      color: "#F59E0B",
    },
  ];

  const topPosts = [
    {
      id: 1,
      content: "🎉 Khuyến mãi Black Friday - Giảm giá lên đến 70%!",
      platform: "Facebook",
      reach: 15420,
      engagement: 1247,
      shares: 89,
      comments: 156,
      date: "2024-11-20",
      icon: <Facebook sx={{ color: "#1877F2" }} />,
    },
    {
      id: 2,
      content: "📊 5 Tips Marketing Hiệu Quả cho Doanh Nghiệp Nhỏ",
      platform: "LinkedIn",
      reach: 8920,
      engagement: 892,
      shares: 134,
      comments: 67,
      date: "2024-11-19",
      icon: <LinkedIn sx={{ color: "#0A66C2" }} />,
    },
    {
      id: 3,
      content: "✨ Behind the scenes - Quy trình sản xuất sản phẩm",
      platform: "Instagram",
      reach: 6340,
      engagement: 756,
      shares: 45,
      comments: 89,
      date: "2024-11-18",
      icon: <Instagram sx={{ color: "#E4405F" }} />,
    },
  ];

  const platformStats = [
    {
      platform: "Facebook",
      icon: <Facebook sx={{ color: "#1877F2" }} />,
      followers: "15.2K",
      reach: "45.6K",
      engagement: "3.2K",
      growthRate: "+12%",
    },
    {
      platform: "Instagram",
      icon: <Instagram sx={{ color: "#E4405F" }} />,
      followers: "8.5K",
      reach: "28.3K",
      engagement: "2.1K",
      growthRate: "+18%",
    },
    {
      platform: "LinkedIn",
      icon: <LinkedIn sx={{ color: "#0A66C2" }} />,
      followers: "3.1K",
      reach: "12.8K",
      engagement: "987",
      growthRate: "+8%",
    },
    {
      platform: "Twitter",
      icon: <Twitter sx={{ color: "#1DA1F2" }} />,
      followers: "2.4K",
      reach: "8.9K",
      engagement: "567",
      growthRate: "+5%",
    },
  ];

  const engagementData = [
    { date: "20/11", likes: 450, comments: 89, shares: 34 },
    { date: "21/11", likes: 520, comments: 102, shares: 45 },
    { date: "22/11", likes: 380, comments: 76, shares: 28 },
    { date: "23/11", likes: 670, comments: 134, shares: 67 },
    { date: "24/11", likes: 590, comments: 118, shares: 52 },
    { date: "25/11", likes: 720, comments: 156, shares: 89 },
    { date: "26/11", likes: 640, comments: 142, shares: 73 },
  ];

  const handleTabChange = (event, newValue) => {
    setActiveTab(newValue);
  };

  const getTrendIcon = (trend) => {
    return trend === "up" ? (
      <TrendingUp sx={{ color: "success.main", fontSize: 20 }} />
    ) : (
      <TrendingDown sx={{ color: "error.main", fontSize: 20 }} />
    );
  };

  const getTrendColor = (trend) => {
    return trend === "up" ? "success.main" : "error.main";
  };

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
            Thống kê & Báo cáo
          </Typography>
          <Typography variant="body1" color="text.secondary">
            Phân tích hiệu quả hoạt động marketing trên mạng xã hội
          </Typography>
        </Box>
        <Box sx={{ display: "flex", gap: 2 }}>
          <TextField
            select
            size="small"
            value={timeRange}
            onChange={(e) => setTimeRange(e.target.value)}
            sx={{ minWidth: 150 }}
          >
            {timeRanges.map((option) => (
              <MenuItem key={option.value} value={option.value}>
                {option.label}
              </MenuItem>
            ))}
          </TextField>
          <TextField
            select
            size="small"
            value={selectedPlatform}
            onChange={(e) => setSelectedPlatform(e.target.value)}
            sx={{ minWidth: 150 }}
          >
            {platforms.map((option) => (
              <MenuItem key={option.value} value={option.value}>
                {option.label}
              </MenuItem>
            ))}
          </TextField>
          <Button variant="outlined" startIcon={<Download />}>
            Xuất báo cáo
          </Button>
        </Box>
      </Box>

      {/* Overview Stats */}
      <Grid container spacing={3} sx={{ mb: 4 }}>
        {overviewStats.map((stat, index) => (
          <Grid item xs={12} sm={6} md={3} key={index}>
            <Card
              sx={{
                p: 3,
                border: "1px solid",
                borderColor: "grey.200",
                "&:hover": {
                  boxShadow: "0 8px 25px rgba(0,0,0,0.1)",
                  transform: "translateY(-2px)",
                  transition: "all 0.3s ease",
                },
              }}
            >
              <Box sx={{ display: "flex", alignItems: "center", mb: 2 }}>
                <Box
                  sx={{
                    p: 1.5,
                    borderRadius: 2,
                    bgcolor: `${stat.color}15`,
                    color: stat.color,
                    mr: 2,
                  }}
                >
                  {stat.icon}
                </Box>
                <Box>
                  <Typography variant="h4" sx={{ fontWeight: "bold" }}>
                    {stat.value}
                  </Typography>
                  <Typography variant="body2" color="text.secondary">
                    {stat.title}
                  </Typography>
                </Box>
              </Box>
              <Box sx={{ display: "flex", alignItems: "center", gap: 1 }}>
                {getTrendIcon(stat.trend)}
                <Typography
                  variant="body2"
                  sx={{ color: getTrendColor(stat.trend) }}
                >
                  {stat.change} so với kỳ trước
                </Typography>
              </Box>
            </Card>
          </Grid>
        ))}
      </Grid>

      {/* Tabs */}
      <Card sx={{ mb: 3 }}>
        <Tabs value={activeTab} onChange={handleTabChange}>
          <Tab icon={<BarChart />} label="Tổng quan" iconPosition="start" />
          <Tab icon={<PieChart />} label="Theo nền tảng" iconPosition="start" />
          <Tab
            icon={<Timeline />}
            label="Bài đăng hàng đầu"
            iconPosition="start"
          />
          <Tab icon={<TrendingUp />} label="Xu hướng" iconPosition="start" />
        </Tabs>
      </Card>

      {/* Overview Tab */}
      {activeTab === 0 && (
        <Grid container spacing={3}>
          {/* Engagement Chart Placeholder */}
          <Grid item xs={12} lg={8}>
            <Card sx={{ p: 3 }}>
              <Typography variant="h6" gutterBottom>
                Tương tác theo ngày
              </Typography>
              <Box
                sx={{
                  height: 300,
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                  bgcolor: "grey.50",
                  borderRadius: 2,
                }}
              >
                <Typography variant="body1" color="text.secondary">
                  [Biểu đồ tương tác sẽ hiển thị ở đây]
                </Typography>
              </Box>
            </Card>
          </Grid>

          {/* Recent Activity */}
          <Grid item xs={12} lg={4}>
            <Card sx={{ p: 3 }}>
              <Typography variant="h6" gutterBottom>
                Hoạt động gần đây
              </Typography>
              <List>
                <ListItem>
                  <ListItemAvatar>
                    <Avatar sx={{ bgcolor: "success.main" }}>
                      <ThumbUp />
                    </Avatar>
                  </ListItemAvatar>
                  <ListItemText
                    primary="Bài đăng mới nhận 156 lượt thích"
                    secondary="2 giờ trước"
                  />
                </ListItem>
                <ListItem>
                  <ListItemAvatar>
                    <Avatar sx={{ bgcolor: "info.main" }}>
                      <Share />
                    </Avatar>
                  </ListItemAvatar>
                  <ListItemText
                    primary="Bài viết được chia sẻ 89 lần"
                    secondary="4 giờ trước"
                  />
                </ListItem>
                <ListItem>
                  <ListItemAvatar>
                    <Avatar sx={{ bgcolor: "warning.main" }}>
                      <People />
                    </Avatar>
                  </ListItemAvatar>
                  <ListItemText
                    primary="Có 24 người theo dõi mới"
                    secondary="6 giờ trước"
                  />
                </ListItem>
              </List>
            </Card>
          </Grid>
        </Grid>
      )}

      {/* Platform Tab */}
      {activeTab === 1 && (
        <Grid container spacing={3}>
          {platformStats.map((platform, index) => (
            <Grid item xs={12} sm={6} md={3} key={index}>
              <Card sx={{ p: 3, textAlign: "center" }}>
                <Box sx={{ mb: 2 }}>{platform.icon}</Box>
                <Typography variant="h6" gutterBottom>
                  {platform.platform}
                </Typography>
                <Box sx={{ mb: 2 }}>
                  <Typography variant="body2" color="text.secondary">
                    Người theo dõi
                  </Typography>
                  <Typography variant="h6" sx={{ fontWeight: "bold" }}>
                    {platform.followers}
                  </Typography>
                </Box>
                <Grid container spacing={1} sx={{ mb: 2 }}>
                  <Grid item xs={4}>
                    <Typography variant="caption" color="text.secondary">
                      Tiếp cận
                    </Typography>
                    <Typography variant="body2" sx={{ fontWeight: 500 }}>
                      {platform.reach}
                    </Typography>
                  </Grid>
                  <Grid item xs={4}>
                    <Typography variant="caption" color="text.secondary">
                      Tương tác
                    </Typography>
                    <Typography variant="body2" sx={{ fontWeight: 500 }}>
                      {platform.engagement}
                    </Typography>
                  </Grid>
                  <Grid item xs={4}>
                    <Typography variant="caption" color="text.secondary">
                      Tăng trưởng
                    </Typography>
                    <Typography
                      variant="body2"
                      sx={{ fontWeight: 500, color: "success.main" }}
                    >
                      {platform.growthRate}
                    </Typography>
                  </Grid>
                </Grid>
              </Card>
            </Grid>
          ))}
        </Grid>
      )}

      {/* Top Posts Tab */}
      {activeTab === 2 && (
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              Bài đăng có hiệu quả cao nhất
            </Typography>
            <TableContainer>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>Nội dung</TableCell>
                    <TableCell>Nền tảng</TableCell>
                    <TableCell align="right">Tiếp cận</TableCell>
                    <TableCell align="right">Tương tác</TableCell>
                    <TableCell align="right">Chia sẻ</TableCell>
                    <TableCell align="right">Bình luận</TableCell>
                    <TableCell>Ngày đăng</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {topPosts.map((post) => (
                    <TableRow key={post.id}>
                      <TableCell>
                        <Typography
                          variant="body2"
                          sx={{
                            maxWidth: 200,
                            overflow: "hidden",
                            textOverflow: "ellipsis",
                            whiteSpace: "nowrap",
                          }}
                        >
                          {post.content}
                        </Typography>
                      </TableCell>
                      <TableCell>
                        <Chip
                          icon={post.icon}
                          label={post.platform}
                          size="small"
                          variant="outlined"
                        />
                      </TableCell>
                      <TableCell align="right">
                        {post.reach.toLocaleString()}
                      </TableCell>
                      <TableCell align="right">
                        {post.engagement.toLocaleString()}
                      </TableCell>
                      <TableCell align="right">{post.shares}</TableCell>
                      <TableCell align="right">{post.comments}</TableCell>
                      <TableCell>{post.date}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </CardContent>
        </Card>
      )}

      {/* Trends Tab */}
      {activeTab === 3 && (
        <Grid container spacing={3}>
          <Grid item xs={12}>
            <Card sx={{ p: 3 }}>
              <Typography variant="h6" gutterBottom>
                Xu hướng tương tác 7 ngày qua
              </Typography>
              <TableContainer>
                <Table>
                  <TableHead>
                    <TableRow>
                      <TableCell>Ngày</TableCell>
                      <TableCell align="right">Lượt thích</TableCell>
                      <TableCell align="right">Bình luận</TableCell>
                      <TableCell align="right">Chia sẻ</TableCell>
                      <TableCell align="right">Tổng tương tác</TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {engagementData.map((day) => (
                      <TableRow key={day.date}>
                        <TableCell>{day.date}</TableCell>
                        <TableCell align="right">{day.likes}</TableCell>
                        <TableCell align="right">{day.comments}</TableCell>
                        <TableCell align="right">{day.shares}</TableCell>
                        <TableCell align="right">
                          <Typography sx={{ fontWeight: "bold" }}>
                            {day.likes + day.comments + day.shares}
                          </Typography>
                        </TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </TableContainer>
            </Card>
          </Grid>
        </Grid>
      )}
    </Box>
  );
};

export default Analytics;
