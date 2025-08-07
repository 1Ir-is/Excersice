import React, { useState } from "react";
import {
  Box,
  Card,
  CardContent,
  Typography,
  TextField,
  Button,
  Alert,
  Grid,
  Chip,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
} from "@mui/material";
import {
  Schedule as ScheduleIcon,
  CalendarMonth as CalendarIcon,
} from "@mui/icons-material";
import { DateTimePicker } from "@mui/x-date-pickers/DateTimePicker";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import dayjs from "dayjs";
import { postAPI } from "../services/api";

const PostScheduler = () => {
  const [formData, setFormData] = useState({
    postId: 1, // Default for testing
    socialChannelId: 1, // Default for testing
    scheduledTime: dayjs().add(1, "hour"),
    finalContent: "",
  });

  const [scheduledPosts, setScheduledPosts] = useState([]);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  // Mock social channels for testing
  const socialChannels = [
    { id: 1, name: "Facebook Page", type: "facebook" },
    { id: 2, name: "Instagram Business", type: "instagram" },
    { id: 3, name: "LinkedIn Company", type: "linkedin" },
    { id: 4, name: "Company Website", type: "website" },
  ];

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]:
        name === "postId" || name === "socialChannelId"
          ? parseInt(value)
          : value,
    }));
  };

  const handleDateTimeChange = (newValue) => {
    setFormData((prev) => ({
      ...prev,
      scheduledTime: newValue,
    }));
  };

  const handleSchedulePost = async () => {
    try {
      setError("");
      setSuccess("");

      const scheduleData = {
        ...formData,
        scheduledTime: formData.scheduledTime.format("YYYY-MM-DDTHH:mm:ss"),
      };

      console.log("Scheduling post with data:", scheduleData);

      const response = await postAPI.schedulePost(scheduleData);
      setScheduledPosts((prev) => [...prev, response.data]);
      setSuccess("Post scheduled successfully!");

      // Reset form
      setFormData((prev) => ({
        ...prev,
        postId: prev.postId + 1,
        finalContent: "",
        scheduledTime: dayjs().add(1, "hour"),
      }));
    } catch (err) {
      console.error("Error scheduling post:", err);
      setError(err.response?.data?.error || "Failed to schedule post");
    }
  };

  const loadScheduledPosts = async () => {
    try {
      const response = await postAPI.getScheduledPosts();
      setScheduledPosts(response.data);
    } catch (err) {
      console.error("Error loading scheduled posts:", err);
    }
  };

  // Load scheduled posts on component mount
  React.useEffect(() => {
    loadScheduledPosts();
  }, []);

  const getChannelName = (channelId) => {
    const channel = socialChannels.find((ch) => ch.id === channelId);
    return channel ? channel.name : `Channel ${channelId}`;
  };

  const getStatusColor = (status) => {
    switch (status) {
      case "PUBLISHED":
        return "success";
      case "SCHEDULED":
        return "warning";
      case "FAILED":
        return "error";
      case "DRAFT":
        return "default";
      default:
        return "default";
    }
  };

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <Box sx={{ p: 3 }}>
        <Typography variant="h4" gutterBottom>
          Post Scheduler (Task 11)
        </Typography>

        {/* Schedule Form */}
        <Card sx={{ mb: 3 }}>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              Schedule Post for Publishing
            </Typography>

            <Grid container spacing={2}>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="Post ID"
                  name="postId"
                  type="number"
                  value={formData.postId}
                  onChange={handleInputChange}
                  variant="outlined"
                />
              </Grid>

              <Grid item xs={12} sm={6}>
                <FormControl fullWidth>
                  <InputLabel>Social Channel</InputLabel>
                  <Select
                    name="socialChannelId"
                    value={formData.socialChannelId}
                    onChange={handleInputChange}
                    label="Social Channel"
                  >
                    {socialChannels.map((channel) => (
                      <MenuItem key={channel.id} value={channel.id}>
                        {channel.name} ({channel.type})
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
              </Grid>

              <Grid item xs={12} sm={6}>
                <DateTimePicker
                  label="Scheduled Date & Time"
                  value={formData.scheduledTime}
                  onChange={handleDateTimeChange}
                  minDateTime={dayjs()}
                  slotProps={{
                    textField: {
                      fullWidth: true,
                      variant: "outlined",
                    },
                  }}
                />
              </Grid>

              <Grid item xs={12}>
                <TextField
                  fullWidth
                  label="Final Content (Optional - override AI content)"
                  name="finalContent"
                  value={formData.finalContent}
                  onChange={handleInputChange}
                  multiline
                  rows={4}
                  variant="outlined"
                  placeholder="Leave empty to use the original AI-generated content..."
                />
              </Grid>
            </Grid>

            <Box sx={{ mt: 2 }}>
              <Button
                variant="contained"
                onClick={handleSchedulePost}
                startIcon={<ScheduleIcon />}
                size="large"
              >
                Schedule Post
              </Button>
            </Box>
          </CardContent>
        </Card>

        {/* Alerts */}
        {error && (
          <Alert severity="error" sx={{ mb: 2 }} onClose={() => setError("")}>
            {error}
          </Alert>
        )}

        {success && (
          <Alert
            severity="success"
            sx={{ mb: 2 }}
            onClose={() => setSuccess("")}
          >
            {success}
          </Alert>
        )}

        {/* Scheduled Posts List */}
        <Card>
          <CardContent>
            <Box
              sx={{
                display: "flex",
                justifyContent: "space-between",
                alignItems: "center",
                mb: 2,
              }}
            >
              <Typography variant="h6">
                Scheduled Posts ({scheduledPosts.length})
              </Typography>
              <Button
                variant="outlined"
                startIcon={<CalendarIcon />}
                onClick={loadScheduledPosts}
                size="small"
              >
                Refresh
              </Button>
            </Box>

            {scheduledPosts.length === 0 ? (
              <Typography color="text.secondary">
                No scheduled posts found. Schedule your first post above!
              </Typography>
            ) : (
              <Grid container spacing={2}>
                {scheduledPosts.map((post) => (
                  <Grid item xs={12} md={6} key={post.id}>
                    <Card variant="outlined">
                      <CardContent>
                        <Box
                          sx={{
                            display: "flex",
                            justifyContent: "space-between",
                            alignItems: "flex-start",
                            mb: 2,
                          }}
                        >
                          <Typography variant="h6" component="div">
                            Post #{post.id}
                          </Typography>
                          <Chip
                            label={post.status}
                            color={getStatusColor(post.status)}
                            size="small"
                          />
                        </Box>

                        <Typography
                          variant="body2"
                          color="text.secondary"
                          sx={{ mb: 1 }}
                        >
                          <strong>Channel:</strong>{" "}
                          {getChannelName(post.socialChannelId)}
                        </Typography>

                        <Typography
                          variant="body2"
                          color="text.secondary"
                          sx={{ mb: 1 }}
                        >
                          <strong>Scheduled:</strong>{" "}
                          {post.scheduledTime
                            ? new Date(post.scheduledTime).toLocaleString()
                            : "Not scheduled"}
                        </Typography>

                        {post.publishedTime && (
                          <Typography
                            variant="body2"
                            color="text.secondary"
                            sx={{ mb: 1 }}
                          >
                            <strong>Published:</strong>{" "}
                            {new Date(post.publishedTime).toLocaleString()}
                          </Typography>
                        )}

                        {post.contentText && (
                          <Typography
                            variant="body2"
                            sx={{ mb: 2, whiteSpace: "pre-wrap" }}
                          >
                            {post.contentText.length > 150
                              ? post.contentText.substring(0, 150) + "..."
                              : post.contentText}
                          </Typography>
                        )}

                        <Typography variant="caption" color="text.secondary">
                          Created: {new Date(post.createdAt).toLocaleString()}
                        </Typography>
                      </CardContent>
                    </Card>
                  </Grid>
                ))}
              </Grid>
            )}
          </CardContent>
        </Card>
      </Box>
    </LocalizationProvider>
  );
};

export default PostScheduler;
