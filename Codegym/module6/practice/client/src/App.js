import React, { useState, useEffect } from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import { CssBaseline } from "@mui/material";

// Pages
import LandingPage from "./pages/LandingPage";
import LoginPage from "./pages/auth/LoginPage";
import RegisterPage from "./pages/auth/RegisterPage";
import ForgotPasswordPage from "./pages/auth/ForgotPasswordPage";
import Dashboard from "./pages/dashboard/Dashboard";
import CampaignManagement from "./pages/campaigns/CampaignManagement";
import SocialMediaConfig from "./pages/social/SocialMediaConfig";
import ContentGenerator from "./pages/content/ContentGenerator";
import PostScheduler from "./pages/scheduler/PostScheduler";
import Analytics from "./pages/analytics/Analytics";
import ProfileSettings from "./pages/profile/ProfileSettings";
import BillingPage from "./pages/billing/BillingPage";
import AdminDashboard from "./pages/admin/AdminDashboard";
import CreateWorkspace from "./pages/workspace/CreateWorkspace";

// Components
import Layout from "./components/layout/Layout";
import UserLayout from "./components/layout/UserLayout";
import PrivateRoute from "./components/auth/PrivateRoute";
import AdminRoute from "./components/auth/AdminRoute";

const theme = createTheme({
  palette: {
    primary: {
      main: "#272882", // Deep blue
      dark: "#1e1f5a",
      light: "#4a4ba8",
    },
    secondary: {
      main: "#5a67d8", // Lighter blue
      dark: "#4c51bf",
      light: "#7c86e5",
    },
    success: {
      main: "#10B981",
    },
    warning: {
      main: "#F59E0B",
    },
    error: {
      main: "#EF4444",
    },
    background: {
      default: "#F8FAFC",
      paper: "#FFFFFF",
    },
    text: {
      primary: "#1F2937",
      secondary: "#6B7280",
    },
  },
  typography: {
    fontFamily: '"Inter", "Roboto", "Helvetica", "Arial", sans-serif',
    h1: {
      fontSize: "2.5rem",
      fontWeight: 700,
      lineHeight: 1.2,
    },
    h2: {
      fontSize: "2rem",
      fontWeight: 600,
      lineHeight: 1.3,
    },
    h3: {
      fontSize: "1.5rem",
      fontWeight: 600,
      lineHeight: 1.4,
    },
    h4: {
      fontSize: "1.25rem",
      fontWeight: 600,
      lineHeight: 1.4,
    },
    h5: {
      fontSize: "1.125rem",
      fontWeight: 600,
      lineHeight: 1.4,
    },
    h6: {
      fontSize: "1rem",
      fontWeight: 600,
      lineHeight: 1.4,
    },
  },
  shape: {
    borderRadius: 8,
  },
  components: {
    MuiButton: {
      styleOverrides: {
        root: {
          textTransform: "none",
          fontWeight: 600,
          borderRadius: 8,
          padding: "8px 16px",
        },
        contained: {
          boxShadow:
            "0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06)",
          "&:hover": {
            boxShadow:
              "0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06)",
          },
        },
      },
    },
    MuiCard: {
      styleOverrides: {
        root: {
          boxShadow:
            "0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06)",
          borderRadius: 12,
        },
      },
    },
    MuiPaper: {
      styleOverrides: {
        root: {
          backgroundImage: "none",
        },
      },
    },
  },
});

function App() {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Check if user is logged in
    const checkAuthStatus = () => {
      const token = localStorage.getItem("authToken");
      const userData = localStorage.getItem("userData");

      if (token && userData) {
        setUser(JSON.parse(userData));
      }
      setLoading(false);
    };

    checkAuthStatus();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Router>
        <Routes>
          {/* Public Routes */}
          <Route path="/" element={<LandingPage />} />
          <Route path="/login" element={<LoginPage setUser={setUser} />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/forgot-password" element={<ForgotPasswordPage />} />

          {/* Workspace Creation */}
          <Route
            path="/create-workspace"
            element={
              <PrivateRoute user={user}>
                <UserLayout>
                  <CreateWorkspace />
                </UserLayout>
              </PrivateRoute>
            }
          />

          {/* User Routes */}
          <Route
            path="/dashboard"
            element={
              <PrivateRoute user={user}>
                {user?.role === "admin" ? (
                  <Layout user={user} setUser={setUser}>
                    <Dashboard />
                  </Layout>
                ) : (
                  <UserLayout>
                    <Dashboard />
                  </UserLayout>
                )}
              </PrivateRoute>
            }
          />
          <Route
            path="/campaigns"
            element={
              <PrivateRoute user={user}>
                {user?.role === "admin" ? (
                  <Layout user={user} setUser={setUser}>
                    <CampaignManagement />
                  </Layout>
                ) : (
                  <UserLayout>
                    <CampaignManagement />
                  </UserLayout>
                )}
              </PrivateRoute>
            }
          />
          <Route
            path="/social-media"
            element={
              <PrivateRoute user={user}>
                {user?.role === "admin" ? (
                  <Layout user={user} setUser={setUser}>
                    <SocialMediaConfig />
                  </Layout>
                ) : (
                  <UserLayout>
                    <SocialMediaConfig />
                  </UserLayout>
                )}
              </PrivateRoute>
            }
          />
          <Route
            path="/content"
            element={
              <PrivateRoute user={user}>
                {user?.role === "admin" ? (
                  <Layout user={user} setUser={setUser}>
                    <ContentGenerator />
                  </Layout>
                ) : (
                  <UserLayout>
                    <ContentGenerator />
                  </UserLayout>
                )}
              </PrivateRoute>
            }
          />
          <Route
            path="/scheduler"
            element={
              <PrivateRoute user={user}>
                <Layout user={user} setUser={setUser}>
                  <PostScheduler />
                </Layout>
              </PrivateRoute>
            }
          />
          <Route
            path="/analytics"
            element={
              <PrivateRoute user={user}>
                <Layout user={user} setUser={setUser}>
                  <Analytics />
                </Layout>
              </PrivateRoute>
            }
          />
          <Route
            path="/settings"
            element={
              <PrivateRoute user={user}>
                <Layout user={user} setUser={setUser}>
                  <ProfileSettings />
                </Layout>
              </PrivateRoute>
            }
          />
          <Route
            path="/billing"
            element={
              <PrivateRoute user={user}>
                <Layout user={user} setUser={setUser}>
                  <BillingPage />
                </Layout>
              </PrivateRoute>
            }
          />

          {/* Admin Routes */}
          <Route
            path="/admin"
            element={
              <AdminRoute user={user}>
                <Layout user={user} setUser={setUser}>
                  <AdminDashboard />
                </Layout>
              </AdminRoute>
            }
          />

          {/* Redirect */}
          <Route
            path="*"
            element={<Navigate to={user ? "/dashboard" : "/"} replace />}
          />
        </Routes>
      </Router>
    </ThemeProvider>
  );
}

export default App;
