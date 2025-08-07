import React from "react";
import {
  Box,
  Grid,
  Typography,
  Card,
  Button,
  Avatar,
  Chip,
  List,
  ListItem,
  ListItemText,
  ListItemAvatar,
  IconButton,
} from "@mui/material";
import {
  TrendingUp,
  Schedule,
  Campaign,
  People,
  Facebook,
  Instagram,
  LinkedIn,
  PlayArrow,
  MoreVert,
} from "@mui/icons-material";
import { Line, Doughnut } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  ArcElement,
} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  ArcElement
);

const Dashboard = () => {
  // Mock data
  const stats = [
    {
      title: "Tổng chiến dịch",
      value: "12",
      change: "+2 tuần này",
      icon: <Campaign sx={{ fontSize: 40 }} />,
      color: "#3B82F6",
      trend: "up",
    },
    {
      title: "Bài đã đăng",
      value: "147",
      change: "+23 tuần này",
      icon: <Schedule sx={{ fontSize: 40 }} />,
      color: "#10B981",
      trend: "up",
    },
    {
      title: "Tương tác",
      value: "2.1K",
      change: "+12% so với tuần trước",
      icon: <People sx={{ fontSize: 40 }} />,
      color: "#8B5CF6",
      trend: "up",
    },
    {
      title: "Tỷ lệ tương tác",
      value: "4.2%",
      change: "+0.3% so với tuần trước",
      icon: <TrendingUp sx={{ fontSize: 40 }} />,
      color: "#F59E0B",
      trend: "up",
    },
  ];

  const recentPosts = [
    {
      id: 1,
      content: "🎉 Khuyến mãi cuối năm - Giảm giá 50% tất cả sản phẩm!",
      platform: "Facebook",
      status: "published",
      engagement: 23,
      time: "2 giờ trước",
      icon: <Facebook sx={{ color: "#1877F2" }} />,
    },
    {
      id: 2,
      content: "✨ Tips marketing hiệu quả cho doanh nghiệp nhỏ",
      platform: "LinkedIn",
      status: "scheduled",
      engagement: 0,
      time: "Sẽ đăng lúc 14:00",
      icon: <LinkedIn sx={{ color: "#0A66C2" }} />,
    },
    {
      id: 3,
      content: "📸 Behind the scenes - Quy trình làm việc của team",
      platform: "Instagram",
      status: "draft",
      engagement: 0,
      time: "Bản nháp",
      icon: <Instagram sx={{ color: "#E4405F" }} />,
    },
  ];

  const upcomingPosts = [
    {
      id: 1,
      title: "Giới thiệu sản phẩm mới",
      time: "14:00 - Hôm nay",
      platforms: ["Facebook", "Instagram"],
    },
    {
      id: 2,
      title: "Chia sẻ customer story",
      time: "09:00 - Ngày mai",
      platforms: ["LinkedIn"],
    },
    {
      id: 3,
      title: "Tips & Tricks",
      time: "16:00 - Ngày mai",
      platforms: ["Facebook", "Twitter"],
    },
  ];

  // Chart data
  const lineChartData = {
    labels: ["T2", "T3", "T4", "T5", "T6", "T7", "CN"],
    datasets: [
      {
        label: "Tương tác",
        data: [65, 59, 80, 81, 56, 55, 40],
        fill: false,
        borderColor: "#3B82F6",
        tension: 0.4,
      },
      {
        label: "Lượt xem",
        data: [28, 48, 40, 19, 86, 27, 90],
        fill: false,
        borderColor: "#10B981",
        tension: 0.4,
      },
    ],
  };

  const doughnutData = {
    labels: ["Facebook", "Instagram", "LinkedIn", "Twitter"],
    datasets: [
      {
        data: [35, 30, 20, 15],
        backgroundColor: ["#1877F2", "#E4405F", "#0A66C2", "#1DA1F2"],
        borderWidth: 0,
      },
    ],
  };

  const getStatusColor = (status) => {
    switch (status) {
      case "published":
        return "success";
      case "scheduled":
        return "warning";
      case "draft":
        return "default";
      default:
        return "default";
    }
  };

  const getStatusText = (status) => {
    switch (status) {
      case "published":
        return "Đã đăng";
      case "scheduled":
        return "Đã lên lịch";
      case "draft":
        return "Bản nháp";
      default:
        return status;
    }
  };

  return (
    <Box>
      {/* Welcome Section */}
      <Box sx={{ mb: 4 }}>
        <Typography variant="h4" gutterBottom>
          Chào mừng trở lại! 👋
        </Typography>
        <Typography variant="body1" color="text.secondary">
          Đây là tổng quan về hoạt động marketing của bạn tuần này
        </Typography>
      </Box>

      {/* Stats Cards */}
      <Grid container spacing={3} sx={{ mb: 4 }}>
        {stats.map((stat, index) => (
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
              <Typography variant="body2" color="success.main">
                {stat.change}
              </Typography>
            </Card>
          </Grid>
        ))}
      </Grid>

      <Grid container spacing={3}>
        {/* Analytics Chart */}
        <Grid item xs={12} lg={8}>
          <Card sx={{ p: 3, height: 400 }}>
            <Typography variant="h6" gutterBottom>
              Tương tác 7 ngày qua
            </Typography>
            <Box sx={{ height: 300 }}>
              <Line
                data={lineChartData}
                options={{
                  responsive: true,
                  maintainAspectRatio: false,
                  plugins: {
                    legend: {
                      position: "top",
                    },
                  },
                  scales: {
                    y: {
                      beginAtZero: true,
                    },
                  },
                }}
              />
            </Box>
          </Card>
        </Grid>

        {/* Platform Distribution */}
        <Grid item xs={12} lg={4}>
          <Card sx={{ p: 3, height: 400 }}>
            <Typography variant="h6" gutterBottom>
              Phân bố nền tảng
            </Typography>
            <Box
              sx={{ height: 250, display: "flex", justifyContent: "center" }}
            >
              <Doughnut
                data={doughnutData}
                options={{
                  responsive: true,
                  maintainAspectRatio: false,
                  plugins: {
                    legend: {
                      position: "bottom",
                    },
                  },
                }}
              />
            </Box>
          </Card>
        </Grid>

        {/* Recent Posts */}
        <Grid item xs={12} lg={6}>
          <Card sx={{ p: 3 }}>
            <Box
              sx={{ display: "flex", justifyContent: "space-between", mb: 2 }}
            >
              <Typography variant="h6">Bài đăng gần đây</Typography>
              <Button size="small">Xem tất cả</Button>
            </Box>
            <List>
              {recentPosts.map((post) => (
                <ListItem
                  key={post.id}
                  sx={{
                    border: "1px solid",
                    borderColor: "grey.200",
                    borderRadius: 2,
                    mb: 1,
                  }}
                  secondaryAction={
                    <IconButton edge="end">
                      <MoreVert />
                    </IconButton>
                  }
                >
                  <ListItemAvatar>
                    <Avatar sx={{ bgcolor: "background.paper" }}>
                      {post.icon}
                    </Avatar>
                  </ListItemAvatar>
                  <ListItemText
                    primary={
                      <Typography variant="body2" sx={{ fontWeight: 500 }}>
                        {post.content}
                      </Typography>
                    }
                    secondary={
                      <Box
                        sx={{
                          display: "flex",
                          alignItems: "center",
                          gap: 1,
                          mt: 1,
                        }}
                      >
                        <Chip
                          label={getStatusText(post.status)}
                          size="small"
                          color={getStatusColor(post.status)}
                        />
                        <Typography variant="caption" color="text.secondary">
                          {post.time}
                        </Typography>
                        {post.status === "published" && (
                          <Typography variant="caption" color="text.secondary">
                            • {post.engagement} tương tác
                          </Typography>
                        )}
                      </Box>
                    }
                  />
                </ListItem>
              ))}
            </List>
          </Card>
        </Grid>

        {/* Upcoming Posts */}
        <Grid item xs={12} lg={6}>
          <Card sx={{ p: 3 }}>
            <Box
              sx={{ display: "flex", justifyContent: "space-between", mb: 2 }}
            >
              <Typography variant="h6">Bài đăng sắp tới</Typography>
              <Button size="small">Quản lý lịch</Button>
            </Box>
            <List>
              {upcomingPosts.map((post) => (
                <ListItem
                  key={post.id}
                  sx={{
                    border: "1px solid",
                    borderColor: "grey.200",
                    borderRadius: 2,
                    mb: 1,
                  }}
                  secondaryAction={
                    <IconButton edge="end" color="primary">
                      <PlayArrow />
                    </IconButton>
                  }
                >
                  <ListItemText
                    primary={
                      <Typography variant="body2" sx={{ fontWeight: 500 }}>
                        {post.title}
                      </Typography>
                    }
                    secondary={
                      <Box sx={{ mt: 1 }}>
                        <Typography
                          variant="caption"
                          color="text.secondary"
                          sx={{ display: "block" }}
                        >
                          {post.time}
                        </Typography>
                        <Box sx={{ display: "flex", gap: 0.5, mt: 1 }}>
                          {post.platforms.map((platform) => (
                            <Chip
                              key={platform}
                              label={platform}
                              size="small"
                              variant="outlined"
                            />
                          ))}
                        </Box>
                      </Box>
                    }
                  />
                </ListItem>
              ))}
            </List>
          </Card>
        </Grid>

        {/* Quick Actions */}
        <Grid item xs={12}>
          <Card sx={{ p: 3 }}>
            <Typography variant="h6" gutterBottom>
              Hành động nhanh
            </Typography>
            <Grid container spacing={2}>
              <Grid item xs={12} sm={6} md={3}>
                <Button
                  fullWidth
                  variant="outlined"
                  size="large"
                  startIcon={<Campaign />}
                  sx={{ py: 2 }}
                >
                  Tạo chiến dịch mới
                </Button>
              </Grid>
              <Grid item xs={12} sm={6} md={3}>
                <Button
                  fullWidth
                  variant="outlined"
                  size="large"
                  startIcon={<Schedule />}
                  sx={{ py: 2 }}
                >
                  Lên lịch bài đăng
                </Button>
              </Grid>
              <Grid item xs={12} sm={6} md={3}>
                <Button
                  fullWidth
                  variant="outlined"
                  size="large"
                  startIcon={<TrendingUp />}
                  sx={{ py: 2 }}
                >
                  Xem báo cáo
                </Button>
              </Grid>
              <Grid item xs={12} sm={6} md={3}>
                <Button
                  fullWidth
                  variant="outlined"
                  size="large"
                  startIcon={<People />}
                  sx={{ py: 2 }}
                >
                  Cấu hình tài khoản
                </Button>
              </Grid>
            </Grid>
          </Card>
        </Grid>
      </Grid>
    </Box>
  );
};

export default Dashboard;
