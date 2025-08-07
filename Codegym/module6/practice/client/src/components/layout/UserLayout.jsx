import React from "react";
import {
  Box,
  AppBar,
  Toolbar,
  Typography,
  Button,
  Avatar,
  Menu,
  MenuItem,
  Container,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { AccountCircle, Dashboard, ExitToApp } from "@mui/icons-material";

const UserLayout = ({ children }) => {
  const navigate = useNavigate();
  const [anchorEl, setAnchorEl] = useState(null);

  const handleProfileClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleProfileClose = () => {
    setAnchorEl(null);
  };

  const handleLogout = () => {
    localStorage.removeItem("isAuthenticated");
    localStorage.removeItem("userRole");
    navigate("/");
  };

  return (
    <Box sx={{ display: "flex", flexDirection: "column", minHeight: "100vh" }}>
      {/* Header */}
      <AppBar
        position="fixed"
        sx={{
          background: "linear-gradient(135deg, #272882 0%, #4a4ba8 100%)",
          boxShadow: "0 4px 20px rgba(39, 40, 130, 0.15)",
        }}
      >
        <Toolbar>
          <Typography
            variant="h6"
            sx={{
              flexGrow: 1,
              fontWeight: "bold",
              cursor: "pointer",
            }}
            onClick={() => navigate("/dashboard")}
          >
            SocialAuto
          </Typography>

          <Button
            color="inherit"
            startIcon={<Dashboard />}
            onClick={() => navigate("/dashboard")}
            sx={{ mr: 2 }}
          >
            Dashboard
          </Button>

          <Button
            onClick={handleProfileClick}
            sx={{
              color: "white",
              display: "flex",
              alignItems: "center",
              gap: 1,
            }}
          >
            <Avatar
              sx={{ width: 32, height: 32, bgcolor: "rgba(255,255,255,0.2)" }}
            >
              <AccountCircle />
            </Avatar>
            <Typography variant="body2">Nguyễn Văn An</Typography>
          </Button>

          <Menu
            anchorEl={anchorEl}
            open={Boolean(anchorEl)}
            onClose={handleProfileClose}
          >
            <MenuItem
              onClick={() => {
                navigate("/profile");
                handleProfileClose();
              }}
            >
              <AccountCircle sx={{ mr: 1 }} />
              Hồ sơ cá nhân
            </MenuItem>
            <MenuItem onClick={handleLogout}>
              <ExitToApp sx={{ mr: 1 }} />
              Đăng xuất
            </MenuItem>
          </Menu>
        </Toolbar>
      </AppBar>

      {/* Main Content */}
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          pt: { xs: 7, sm: 8 },
          pb: 3,
          background: "linear-gradient(135deg, #f8fafc 0%, #e2e8f0 100%)",
          minHeight: "calc(100vh - 64px)",
        }}
      >
        <Container maxWidth="xl" sx={{ px: { xs: 2, sm: 3 } }}>
          {children}
        </Container>
      </Box>

      {/* Footer */}
      <Box
        component="footer"
        sx={{
          py: 3,
          px: 2,
          mt: "auto",
          background: "linear-gradient(135deg, #272882 0%, #4a4ba8 100%)",
          color: "white",
          textAlign: "center",
        }}
      >
        <Container maxWidth="lg">
          <Typography variant="body2">
            © 2024 SocialAuto. Tất cả quyền được bảo lưu.
          </Typography>
        </Container>
      </Box>
    </Box>
  );
};

export default UserLayout;
