import React, { useState } from "react";
import {
  Box,
  Grid,
  Typography,
  Card,
  CardContent,
  Button,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Chip,
  Avatar,
  TextField,
  MenuItem,
  Tabs,
  Tab,
  List,
  ListItem,
  ListItemText,
  ListItemAvatar,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Switch,
  FormControlLabel,
  Alert,
  IconButton,
  Tooltip,
} from "@mui/material";
import {
  People,
  TrendingUp,
  AttachMoney,
  Storage,
  Visibility,
  Edit,
  Delete,
  Block,
  CheckCircle,
  Warning,
  Refresh,
  Download,
  Settings,
  Security,
  Analytics,
  Campaign,
  Notifications,
  Search,
} from "@mui/icons-material";

const AdminDashboard = () => {
  const [activeTab, setActiveTab] = useState(0);
  const [selectedUser, setSelectedUser] = useState(null);
  const [showUserDialog, setShowUserDialog] = useState(false);
  const [filterStatus, setFilterStatus] = useState("all");
  const [searchTerm, setSearchTerm] = useState("");

  const systemStats = [
    {
      title: "Tổng người dùng",
      value: "1,247",
      change: "+12.5%",
      icon: <People sx={{ fontSize: 40 }} />,
      color: "#3B82F6",
    },
    {
      title: "Doanh thu tháng",
      value: "₫45.2M",
      change: "+8.3%",
      icon: <AttachMoney sx={{ fontSize: 40 }} />,
      color: "#10B981",
    },
    {
      title: "Số chiến dịch",
      value: "3,892",
      change: "+15.7%",
      icon: <Campaign sx={{ fontSize: 40 }} />,
      color: "#8B5CF6",
    },
    {
      title: "Dung lượng sử dụng",
      value: "847 GB",
      change: "+5.2%",
      icon: <Storage sx={{ fontSize: 40 }} />,
      color: "#F59E0B",
    },
  ];

  const users = [
    {
      id: 1,
      name: "Nguyễn Văn An",
      email: "nguyenvanan@email.com",
      plan: "Pro",
      status: "active",
      joinDate: "2024-01-15",
      lastActive: "2024-11-26 14:30",
      posts: 247,
      revenue: "299,000",
    },
    {
      id: 2,
      name: "Trần Thị Bình",
      email: "tranthibinh@email.com",
      plan: "Basic",
      status: "active",
      joinDate: "2024-02-20",
      lastActive: "2024-11-25 09:15",
      posts: 89,
      revenue: "99,000",
    },
    {
      id: 3,
      name: "Lê Minh Cường",
      email: "leminhcuong@email.com",
      plan: "Enterprise",
      status: "suspended",
      joinDate: "2024-03-10",
      lastActive: "2024-11-20 16:45",
      posts: 456,
      revenue: "999,000",
    },
    {
      id: 4,
      name: "Phạm Thu Duyên",
      email: "phamthuduyen@email.com",
      plan: "Pro",
      status: "inactive",
      joinDate: "2024-04-05",
      lastActive: "2024-11-15 11:20",
      posts: 123,
      revenue: "299,000",
    },
  ];

  const systemLogs = [
    {
      id: 1,
      timestamp: "2024-11-26 15:30:25",
      level: "info",
      action: "User login",
      user: "nguyenvanan@email.com",
      details: "Successful login from IP 192.168.1.100",
    },
    {
      id: 2,
      timestamp: "2024-11-26 15:25:10",
      level: "warning",
      action: "Failed payment",
      user: "tranthibinh@email.com",
      details: "Payment declined for Pro plan upgrade",
    },
    {
      id: 3,
      timestamp: "2024-11-26 15:20:15",
      level: "error",
      action: "API error",
      user: "system",
      details: "Social media API rate limit exceeded",
    },
    {
      id: 4,
      timestamp: "2024-11-26 15:15:30",
      level: "info",
      action: "Campaign created",
      user: "leminhcuong@email.com",
      details: "New campaign 'Black Friday Sale' created",
    },
  ];

  const systemSettings = [
    {
      category: "General",
      settings: [
        {
          key: "maintenance_mode",
          label: "Chế độ bảo trì",
          value: false,
          type: "boolean",
        },
        {
          key: "new_registrations",
          label: "Cho phép đăng ký mới",
          value: true,
          type: "boolean",
        },
        {
          key: "max_posts_per_day",
          label: "Giới hạn bài đăng/ngày",
          value: 100,
          type: "number",
        },
      ],
    },
    {
      category: "Security",
      settings: [
        {
          key: "force_2fa",
          label: "Bắt buộc xác thực 2 bước",
          value: false,
          type: "boolean",
        },
        {
          key: "session_timeout",
          label: "Thời gian hết phiên (phút)",
          value: 60,
          type: "number",
        },
        {
          key: "password_min_length",
          label: "Độ dài mật khẩu tối thiểu",
          value: 8,
          type: "number",
        },
      ],
    },
  ];

  const handleTabChange = (event, newValue) => {
    setActiveTab(newValue);
  };

  const handleUserClick = (user) => {
    setSelectedUser(user);
    setShowUserDialog(true);
  };

  const getStatusColor = (status) => {
    switch (status) {
      case "active":
        return "success";
      case "inactive":
        return "warning";
      case "suspended":
        return "error";
      default:
        return "default";
    }
  };

  const getStatusText = (status) => {
    switch (status) {
      case "active":
        return "Hoạt động";
      case "inactive":
        return "Không hoạt động";
      case "suspended":
        return "Tạm khóa";
      default:
        return status;
    }
  };

  const getLevelColor = (level) => {
    switch (level) {
      case "info":
        return "primary";
      case "warning":
        return "warning";
      case "error":
        return "error";
      default:
        return "default";
    }
  };

  const filteredUsers = users.filter((user) => {
    const matchesSearch =
      user.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
      user.email.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesStatus =
      filterStatus === "all" || user.status === filterStatus;
    return matchesSearch && matchesStatus;
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
            Quản trị hệ thống
          </Typography>
          <Typography variant="body1" color="text.secondary">
            Tổng quan và quản lý hệ thống marketing automation
          </Typography>
        </Box>
        <Box sx={{ display: "flex", gap: 2 }}>
          <Button variant="outlined" startIcon={<Download />}>
            Xuất báo cáo
          </Button>
          <Button variant="outlined" startIcon={<Refresh />}>
            Làm mới
          </Button>
        </Box>
      </Box>

      {/* System Stats */}
      <Grid container spacing={3} sx={{ mb: 4 }}>
        {systemStats.map((stat, index) => (
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
                <TrendingUp sx={{ color: "success.main", fontSize: 20 }} />
                <Typography variant="body2" sx={{ color: "success.main" }}>
                  {stat.change} so với tháng trước
                </Typography>
              </Box>
            </Card>
          </Grid>
        ))}
      </Grid>

      {/* Tabs */}
      <Card sx={{ mb: 3 }}>
        <Tabs value={activeTab} onChange={handleTabChange}>
          <Tab icon={<People />} label="Người dùng" iconPosition="start" />
          <Tab
            icon={<Analytics />}
            label="Nhật ký hệ thống"
            iconPosition="start"
          />
          <Tab icon={<Settings />} label="Cài đặt" iconPosition="start" />
          <Tab icon={<Security />} label="Bảo mật" iconPosition="start" />
        </Tabs>
      </Card>

      {/* Users Tab */}
      {activeTab === 0 && (
        <Card>
          <CardContent>
            <Box
              sx={{
                display: "flex",
                justifyContent: "space-between",
                alignItems: "center",
                mb: 3,
              }}
            >
              <Typography variant="h6">Quản lý người dùng</Typography>
              <Box sx={{ display: "flex", gap: 2 }}>
                <TextField
                  size="small"
                  placeholder="Tìm kiếm..."
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                  InputProps={{
                    startAdornment: (
                      <Search sx={{ mr: 1, color: "text.secondary" }} />
                    ),
                  }}
                />
                <TextField
                  select
                  size="small"
                  value={filterStatus}
                  onChange={(e) => setFilterStatus(e.target.value)}
                  sx={{ minWidth: 120 }}
                >
                  <MenuItem value="all">Tất cả</MenuItem>
                  <MenuItem value="active">Hoạt động</MenuItem>
                  <MenuItem value="inactive">Không hoạt động</MenuItem>
                  <MenuItem value="suspended">Tạm khóa</MenuItem>
                </TextField>
              </Box>
            </Box>

            <TableContainer>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>Người dùng</TableCell>
                    <TableCell>Gói</TableCell>
                    <TableCell>Trạng thái</TableCell>
                    <TableCell>Ngày tham gia</TableCell>
                    <TableCell>Hoạt động cuối</TableCell>
                    <TableCell align="right">Bài đăng</TableCell>
                    <TableCell align="right">Doanh thu</TableCell>
                    <TableCell>Hành động</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {filteredUsers.map((user) => (
                    <TableRow key={user.id} hover>
                      <TableCell>
                        <Box sx={{ display: "flex", alignItems: "center" }}>
                          <Avatar sx={{ mr: 2 }}>
                            {user.name
                              .split(" ")
                              .map((n) => n[0])
                              .join("")}
                          </Avatar>
                          <Box>
                            <Typography
                              variant="body2"
                              sx={{ fontWeight: 500 }}
                            >
                              {user.name}
                            </Typography>
                            <Typography
                              variant="caption"
                              color="text.secondary"
                            >
                              {user.email}
                            </Typography>
                          </Box>
                        </Box>
                      </TableCell>
                      <TableCell>
                        <Chip label={user.plan} size="small" />
                      </TableCell>
                      <TableCell>
                        <Chip
                          label={getStatusText(user.status)}
                          color={getStatusColor(user.status)}
                          size="small"
                        />
                      </TableCell>
                      <TableCell>{user.joinDate}</TableCell>
                      <TableCell>{user.lastActive}</TableCell>
                      <TableCell align="right">{user.posts}</TableCell>
                      <TableCell align="right">₫{user.revenue}</TableCell>
                      <TableCell>
                        <Box sx={{ display: "flex", gap: 1 }}>
                          <Tooltip title="Xem chi tiết">
                            <IconButton
                              size="small"
                              onClick={() => handleUserClick(user)}
                            >
                              <Visibility fontSize="small" />
                            </IconButton>
                          </Tooltip>
                          <Tooltip title="Chỉnh sửa">
                            <IconButton size="small">
                              <Edit fontSize="small" />
                            </IconButton>
                          </Tooltip>
                          <Tooltip title="Khóa">
                            <IconButton size="small" color="error">
                              <Block fontSize="small" />
                            </IconButton>
                          </Tooltip>
                        </Box>
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </CardContent>
        </Card>
      )}

      {/* System Logs Tab */}
      {activeTab === 1 && (
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              Nhật ký hệ thống
            </Typography>
            <List>
              {systemLogs.map((log) => (
                <ListItem key={log.id} divider>
                  <ListItemAvatar>
                    <Avatar
                      sx={{ bgcolor: `${getLevelColor(log.level)}.main` }}
                    >
                      {log.level === "info" && <Notifications />}
                      {log.level === "warning" && <Warning />}
                      {log.level === "error" && <Security />}
                    </Avatar>
                  </ListItemAvatar>
                  <ListItemText
                    primary={
                      <Box
                        sx={{ display: "flex", alignItems: "center", gap: 1 }}
                      >
                        <Typography variant="body1">{log.action}</Typography>
                        <Chip
                          label={log.level.toUpperCase()}
                          color={getLevelColor(log.level)}
                          size="small"
                        />
                      </Box>
                    }
                    secondary={
                      <Box>
                        <Typography variant="body2" color="text.secondary">
                          {log.details}
                        </Typography>
                        <Typography variant="caption" color="text.secondary">
                          {log.timestamp} - {log.user}
                        </Typography>
                      </Box>
                    }
                  />
                </ListItem>
              ))}
            </List>
          </CardContent>
        </Card>
      )}

      {/* Settings Tab */}
      {activeTab === 2 && (
        <Grid container spacing={3}>
          {systemSettings.map((category) => (
            <Grid item xs={12} md={6} key={category.category}>
              <Card>
                <CardContent>
                  <Typography variant="h6" gutterBottom>
                    {category.category}
                  </Typography>
                  {category.settings.map((setting) => (
                    <Box key={setting.key} sx={{ mb: 2 }}>
                      {setting.type === "boolean" ? (
                        <FormControlLabel
                          control={<Switch checked={setting.value} />}
                          label={setting.label}
                        />
                      ) : (
                        <TextField
                          fullWidth
                          label={setting.label}
                          type="number"
                          value={setting.value}
                          size="small"
                        />
                      )}
                    </Box>
                  ))}
                </CardContent>
              </Card>
            </Grid>
          ))}
        </Grid>
      )}

      {/* Security Tab */}
      {activeTab === 3 && (
        <Grid container spacing={3}>
          <Grid item xs={12} md={6}>
            <Card>
              <CardContent>
                <Typography variant="h6" gutterBottom>
                  Thông tin bảo mật
                </Typography>
                <Alert severity="success" sx={{ mb: 2 }}>
                  Hệ thống đang hoạt động bình thường
                </Alert>
                <Box sx={{ mb: 2 }}>
                  <Typography variant="body2" color="text.secondary">
                    Lần cập nhật bảo mật cuối: 2024-11-20
                  </Typography>
                </Box>
                <Button variant="outlined" fullWidth>
                  Kiểm tra bảo mật
                </Button>
              </CardContent>
            </Card>
          </Grid>
          <Grid item xs={12} md={6}>
            <Card>
              <CardContent>
                <Typography variant="h6" gutterBottom>
                  Sao lưu dữ liệu
                </Typography>
                <Typography
                  variant="body2"
                  color="text.secondary"
                  sx={{ mb: 2 }}
                >
                  Sao lưu tự động: Hàng ngày lúc 02:00
                </Typography>
                <Typography
                  variant="body2"
                  color="text.secondary"
                  sx={{ mb: 2 }}
                >
                  Sao lưu cuối: 2024-11-26 02:00
                </Typography>
                <Button variant="outlined" fullWidth>
                  Tạo sao lưu ngay
                </Button>
              </CardContent>
            </Card>
          </Grid>
        </Grid>
      )}

      {/* User Detail Dialog */}
      <Dialog
        open={showUserDialog}
        onClose={() => setShowUserDialog(false)}
        maxWidth="md"
        fullWidth
      >
        <DialogTitle>Chi tiết người dùng</DialogTitle>
        <DialogContent>
          {selectedUser && (
            <Grid container spacing={3}>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="Tên"
                  value={selectedUser.name}
                  disabled
                  sx={{ mb: 2 }}
                />
                <TextField
                  fullWidth
                  label="Email"
                  value={selectedUser.email}
                  disabled
                  sx={{ mb: 2 }}
                />
                <TextField
                  fullWidth
                  label="Gói đăng ký"
                  value={selectedUser.plan}
                  disabled
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="Trạng thái"
                  value={getStatusText(selectedUser.status)}
                  disabled
                  sx={{ mb: 2 }}
                />
                <TextField
                  fullWidth
                  label="Ngày tham gia"
                  value={selectedUser.joinDate}
                  disabled
                  sx={{ mb: 2 }}
                />
                <TextField
                  fullWidth
                  label="Hoạt động cuối"
                  value={selectedUser.lastActive}
                  disabled
                />
              </Grid>
            </Grid>
          )}
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setShowUserDialog(false)}>Đóng</Button>
          <Button variant="contained">Chỉnh sửa</Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default AdminDashboard;
