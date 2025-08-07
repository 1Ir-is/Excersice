// src/components/Navigation.jsx
import React from "react";
import { AppBar, Toolbar, Typography, Tabs, Tab, Box } from "@mui/material";

const Navigation = ({ currentTab, onTabChange }) => {
  const handleTabChange = (event, newValue) => {
    onTabChange(newValue);
  };

  return (
    <AppBar position="static" sx={{ mb: 3 }}>
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Marketing AI Platform - User 1Ir-is
        </Typography>

        <Box sx={{ ml: "auto" }}>
          <Tabs
            value={currentTab}
            onChange={handleTabChange}
            textColor="inherit"
            indicatorColor="secondary"
          >
            <Tab label="Tạo Chủ Đề" value="topics" sx={{ color: "white" }} />
            <Tab label="Tạo Nội Dung" value="content" sx={{ color: "white" }} />
            <Tab
              label="Lên Lịch Đăng"
              value="schedule"
              sx={{ color: "white" }}
            />
          </Tabs>
        </Box>
      </Toolbar>
    </AppBar>
  );
};

export default Navigation;
