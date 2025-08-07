import React, { useState } from "react";
import {
  Box,
  Drawer,
  AppBar,
  Toolbar,
  List,
  Typography,
  IconButton,
  Avatar,
  Menu,
  MenuItem,
  Badge,
  useMediaQuery,
  useTheme,
  Paper,
} from "@mui/material";
import {
  Menu as MenuIcon,
  Notifications as NotificationsIcon,
  Logout,
  Settings,
  Dashboard,
  Campaign,
  Share,
  Create,
  Schedule,
  Analytics,
  Payment,
  AdminPanelSettings,
} from "@mui/icons-material";
import { useNavigate, useLocation } from "react-router-dom";
import SidebarItem from "./SidebarItem";

const drawerWidth = 280;

const Layout = ({ children, user, setUser }) => {
  const navigate = useNavigate();
  const location = useLocation();
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down("md"));

  const [mobileOpen, setMobileOpen] = useState(false);
  const [anchorEl, setAnchorEl] = useState(null);

  const menuItems = [
    {
      text: "Dashboard",
      icon: <Dashboard />,
      path: "/dashboard",
      roles: ["user", "admin"],
    },
    {
      text: "Quản lý chiến dịch",
      icon: <Campaign />,
      path: "/campaigns",
      roles: ["user", "admin"],
    },
    {
      text: "Cấu hình mạng xã hội",
      icon: <Share />,
      path: "/social-media",
      roles: ["user", "admin"],
    },
    {
      text: "Tạo nội dung",
      icon: <Create />,
      path: "/content",
      roles: ["user", "admin"],
    },
    {
      text: "Lập lịch đăng bài",
      icon: <Schedule />,
      path: "/scheduler",
      roles: ["user", "admin"],
    },
    {
      text: "Thống kê & Báo cáo",
      icon: <Analytics />,
      path: "/analytics",
      roles: ["user", "admin"],
    },
    {
      text: "Thanh toán",
      icon: <Payment />,
      path: "/billing",
      roles: ["user", "admin"],
    },
    {
      text: "Quản trị hệ thống",
      icon: <AdminPanelSettings />,
      path: "/admin",
      roles: ["admin"],
    },
  ];

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };

  const handleProfileMenuOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleProfileMenuClose = () => {
    setAnchorEl(null);
  };

  const handleLogout = () => {
    localStorage.removeItem("authToken");
    localStorage.removeItem("userData");
    setUser(null);
    navigate("/");
  };

  const filteredMenuItems = menuItems.filter((item) =>
    item.roles.includes(user?.role)
  );

  const drawer = (
    <Box
      sx={{
        background: "linear-gradient(180deg, #272882 0%, #4a4ba8 100%)",
        height: "100%",
        color: "white",
      }}
    >
      {/* Logo */}
      <Box
        sx={{
          p: 3,
          display: "flex",
          alignItems: "center",
          borderBottom: "1px solid rgba(255,255,255,0.1)",
        }}
      >
        <Box
          sx={{
            width: 40,
            height: 40,
            borderRadius: "50%",
            background: "rgba(255,255,255,0.2)",
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            mr: 2,
          }}
        >
          <Typography
            variant="h6"
            sx={{
              color: "white",
              fontWeight: "bold",
              fontSize: "1.2rem",
            }}
          >
            S
          </Typography>
        </Box>
        <Typography variant="h6" sx={{ fontWeight: "bold", color: "white" }}>
          SocialAuto
        </Typography>
      </Box>

      {/* User Info */}
      <Box sx={{ p: 3, borderBottom: "1px solid rgba(255,255,255,0.1)" }}>
        <Box sx={{ display: "flex", alignItems: "center", mb: 2 }}>
          <Avatar
            src={user?.avatar}
            sx={{
              mr: 2,
              bgcolor: "rgba(255,255,255,0.2)",
              color: "white",
            }}
          >
            {user?.name?.charAt(0)}
          </Avatar>
          <Box>
            <Typography
              variant="subtitle1"
              sx={{ fontWeight: 600, color: "white" }}
            >
              {user?.name}
            </Typography>
            <Typography variant="body2" sx={{ color: "rgba(255,255,255,0.7)" }}>
              {user?.email}
            </Typography>
          </Box>
        </Box>
        <Typography variant="caption" sx={{ color: "rgba(255,255,255,0.7)" }}>
          Gói {user?.plan || "Pro"} • {user?.daysLeft || "28"} ngày còn lại
        </Typography>
      </Box>

      {/* Navigation */}
      <List sx={{ px: 2, py: 1 }}>
        {filteredMenuItems.map((item) => (
          <SidebarItem
            key={item.path}
            text={item.text}
            icon={item.icon}
            path={item.path}
            active={location.pathname === item.path}
            onClick={() => {
              navigate(item.path);
              if (isMobile) setMobileOpen(false);
            }}
          />
        ))}
      </List>
    </Box>
  );

  return (
    <Box sx={{ display: "flex" }}>
      {/* App Bar */}
      <AppBar
        position="fixed"
        sx={{
          width: { md: `calc(100% - ${drawerWidth}px)` },
          ml: { md: `${drawerWidth}px` },
          background: "rgba(255, 255, 255, 0.95)",
          backdropFilter: "blur(20px)",
          color: "#272882",
          boxShadow: "0 4px 20px rgba(39, 40, 130, 0.08)",
          borderBottom: "1px solid rgba(39, 40, 130, 0.1)",
        }}
      >
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={handleDrawerToggle}
            sx={{ mr: 2, display: { md: "none" } }}
          >
            <MenuIcon />
          </IconButton>

          <Typography variant="h6" noWrap component="div" sx={{ flexGrow: 1 }}>
            {filteredMenuItems.find((item) => item.path === location.pathname)
              ?.text || "Dashboard"}
          </Typography>

          <Box sx={{ display: "flex", alignItems: "center", gap: 1 }}>
            <IconButton color="inherit">
              <Badge badgeContent={3} color="error">
                <NotificationsIcon />
              </Badge>
            </IconButton>

            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="primary-search-account-menu"
              aria-haspopup="true"
              onClick={handleProfileMenuOpen}
              color="inherit"
            >
              <Avatar src={user?.avatar} sx={{ width: 32, height: 32 }}>
                {user?.name?.charAt(0)}
              </Avatar>
            </IconButton>
          </Box>
        </Toolbar>
      </AppBar>

      {/* Profile Menu */}
      <Menu
        anchorEl={anchorEl}
        open={Boolean(anchorEl)}
        onClose={handleProfileMenuClose}
        anchorOrigin={{
          vertical: "bottom",
          horizontal: "right",
        }}
        transformOrigin={{
          vertical: "top",
          horizontal: "right",
        }}
      >
        <MenuItem
          onClick={() => {
            navigate("/settings");
            handleProfileMenuClose();
          }}
        >
          <Settings sx={{ mr: 2 }} />
          Cài đặt tài khoản
        </MenuItem>
        <MenuItem onClick={handleLogout}>
          <Logout sx={{ mr: 2 }} />
          Đăng xuất
        </MenuItem>
      </Menu>

      {/* Sidebar */}
      <Box
        component="nav"
        sx={{ width: { md: drawerWidth }, flexShrink: { md: 0 } }}
      >
        <Drawer
          variant="temporary"
          open={mobileOpen}
          onClose={handleDrawerToggle}
          ModalProps={{
            keepMounted: true,
          }}
          sx={{
            display: { xs: "block", md: "none" },
            "& .MuiDrawer-paper": {
              boxSizing: "border-box",
              width: drawerWidth,
            },
          }}
        >
          {drawer}
        </Drawer>
        <Drawer
          variant="permanent"
          sx={{
            display: { xs: "none", md: "block" },
            "& .MuiDrawer-paper": {
              boxSizing: "border-box",
              width: drawerWidth,
              borderRight: "1px solid",
              borderColor: "divider",
            },
          }}
          open
        >
          {drawer}
        </Drawer>
      </Box>

      {/* Main Content */}
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          width: { md: `calc(100% - ${drawerWidth}px)` },
          minHeight: "100vh",
          bgcolor: "background.default",
        }}
      >
        <Toolbar />
        <Box sx={{ p: 3 }}>{children}</Box>
      </Box>
    </Box>
  );
};

export default Layout;
