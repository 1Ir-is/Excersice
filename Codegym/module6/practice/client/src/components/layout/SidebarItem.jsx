import React from "react";
import {
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  alpha,
} from "@mui/material";

const SidebarItem = ({ text, icon, path, active, onClick }) => {
  return (
    <ListItem disablePadding sx={{ mb: 0.5 }}>
      <ListItemButton
        onClick={onClick}
        sx={{
          borderRadius: 2,
          mx: 1,
          backgroundColor: active ? "rgba(255,255,255,0.2)" : "transparent",
          color: active ? "white" : "rgba(255,255,255,0.8)",
          "&:hover": {
            backgroundColor: active
              ? "rgba(255,255,255,0.25)"
              : "rgba(255,255,255,0.1)",
          },
          "& .MuiListItemIcon-root": {
            color: active ? "white" : "rgba(255,255,255,0.7)",
          },
          transition: "all 0.3s ease",
        }}
      >
        <ListItemIcon sx={{ minWidth: 40 }}>{icon}</ListItemIcon>
        <ListItemText
          primary={text}
          primaryTypographyProps={{
            fontWeight: active ? 600 : 400,
            fontSize: "0.9rem",
            color: active ? "white" : "rgba(255,255,255,0.8)",
          }}
        />
      </ListItemButton>
    </ListItem>
  );
};

export default SidebarItem;
