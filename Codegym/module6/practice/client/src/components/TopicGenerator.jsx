// src/components/TopicGenerator.jsx
import React, { useState, useEffect } from "react";
import {
  Box,
  Card,
  CardContent,
  Typography,
  TextField,
  Button,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  Grid,
  Chip,
  CircularProgress,
  Alert,
  IconButton,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
} from "@mui/material";
import { ThumbUp, ThumbDown, Delete, Refresh } from "@mui/icons-material";
import { topicAPI } from "../services/api"; // ← Import service instead of axios

const TopicGenerator = () => {
  const [topics, setTopics] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);

  // Form state
  const [numberOfTopics, setNumberOfTopics] = useState(3);
  const [additionalInstructions, setAdditionalInstructions] = useState("");
  const [campaignId] = useState(1); // Fixed for now

  // Dialog state
  const [deleteDialog, setDeleteDialog] = useState({
    open: false,
    topicId: null,
  });

  // Load topics on component mount
  useEffect(() => {
    loadTopics();
  }, []);

  const loadTopics = async () => {
    setLoading(true);
    setError(null);

    try {
      const response = await topicAPI.getTopicsByCampaign(campaignId);
      setTopics(response.data);
    } catch (err) {
      setError(`Lỗi khi tải chủ đề cho user 1Ir-is: ${err.message}`);
    } finally {
      setLoading(false);
    }
  };

  const generateTopics = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    setSuccess(null);

    try {
      const requestData = {
        campaignId: campaignId,
        numberOfTopics: numberOfTopics,
        additionalInstructions: additionalInstructions,
      };

      const response = await topicAPI.generateTopics(requestData);

      // Add new topics to the beginning of the list
      setTopics((prevTopics) => [...response.data, ...prevTopics]);
      setSuccess(`Đã tạo thành công ${response.data.length} chủ đề mới!`);
      setAdditionalInstructions(""); // Clear form
    } catch (err) {
      setError(`Lỗi khi tạo chủ đề cho user 1Ir-is: ${err.message}`);
    } finally {
      setLoading(false);
    }
  };

  const approveTopic = async (topicId) => {
    try {
      const response = await topicAPI.approveTopic(topicId);
      setTopics((prevTopics) =>
        prevTopics.map((topic) =>
          topic.id === topicId ? response.data : topic
        )
      );
      setSuccess("Chủ đề đã được duyệt!");
    } catch (err) {
      setError(`Lỗi khi duyệt chủ đề: ${err.message}`);
    }
  };

  const rejectTopic = async (topicId) => {
    try {
      const response = await topicAPI.rejectTopic(topicId);
      setTopics((prevTopics) =>
        prevTopics.map((topic) =>
          topic.id === topicId ? response.data : topic
        )
      );
      setSuccess("Chủ đề đã bị từ chối!");
    } catch (err) {
      setError(`Lỗi khi từ chối chủ đề: ${err.message}`);
    }
  };

  const deleteTopic = async (topicId) => {
    try {
      await topicAPI.deleteTopic(topicId);
      setTopics((prevTopics) =>
        prevTopics.filter((topic) => topic.id !== topicId)
      );
      setSuccess("Chủ đề đã được xóa!");
      setDeleteDialog({ open: false, topicId: null });
    } catch (err) {
      setError(`Lỗi khi xóa chủ đề: ${err.message}`);
    }
  };

  const getStatusColor = (status) => {
    switch (status) {
      case "PENDING":
        return "warning";
      case "APPROVED":
        return "success";
      case "REJECTED":
        return "error";
      default:
        return "default";
    }
  };

  const getStatusText = (status) => {
    switch (status) {
      case "PENDING":
        return "Chờ duyệt";
      case "APPROVED":
        return "Đã duyệt";
      case "REJECTED":
        return "Đã từ chối";
      default:
        return status;
    }
  };

  return (
    <Box sx={{ py: 3 }}>
      <Typography variant="h4" component="h1" gutterBottom>
        Tạo Chủ Đề Marketing với AI
      </Typography>

      <Typography variant="subtitle1" color="text.secondary" gutterBottom>
        User: 1Ir-is • Thời gian: 2025-08-06 06:47:39 UTC • MockGPT Vietnamese
      </Typography>

      {/* Generation Form */}
      <Card sx={{ mb: 3 }}>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            Tạo Chủ Đề Mới (Campaign ID: {campaignId})
          </Typography>

          <Box component="form" onSubmit={generateTopics}>
            <Grid container spacing={3}>
              <Grid item xs={12} sm={6}>
                <FormControl fullWidth>
                  <InputLabel>Số lượng chủ đề</InputLabel>
                  <Select
                    value={numberOfTopics}
                    onChange={(e) => setNumberOfTopics(e.target.value)}
                    label="Số lượng chủ đề"
                  >
                    <MenuItem value={1}>1 chủ đề</MenuItem>
                    <MenuItem value={2}>2 chủ đề</MenuItem>
                    <MenuItem value={3}>3 chủ đề</MenuItem>
                    <MenuItem value={4}>4 chủ đề</MenuItem>
                    <MenuItem value={5}>5 chủ đề</MenuItem>
                  </Select>
                </FormControl>
              </Grid>

              <Grid item xs={12}>
                <TextField
                  fullWidth
                  multiline
                  rows={3}
                  label="Hướng dẫn bổ sung"
                  value={additionalInstructions}
                  onChange={(e) => setAdditionalInstructions(e.target.value)}
                  placeholder="Ví dụ: Tập trung vào công nghệ AI cho thị trường Việt Nam, bao gồm blockchain và fintech..."
                />
              </Grid>

              <Grid item xs={12}>
                <Button
                  type="submit"
                  variant="contained"
                  size="large"
                  disabled={loading}
                  startIcon={loading ? <CircularProgress size={20} /> : null}
                  sx={{ mr: 2 }}
                >
                  {loading ? "Đang tạo chủ đề..." : "Tạo Chủ Đề với MockGPT"}
                </Button>

                <Button
                  variant="outlined"
                  onClick={loadTopics}
                  startIcon={<Refresh />}
                  disabled={loading}
                >
                  Tải lại
                </Button>
              </Grid>
            </Grid>
          </Box>
        </CardContent>
      </Card>

      {/* Alerts */}
      {error && (
        <Alert severity="error" sx={{ mb: 2 }} onClose={() => setError(null)}>
          {error}
        </Alert>
      )}

      {success && (
        <Alert
          severity="success"
          sx={{ mb: 2 }}
          onClose={() => setSuccess(null)}
        >
          {success}
        </Alert>
      )}

      {/* Topics List */}
      <Card>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            Danh Sách Chủ Đề ({topics.length})
          </Typography>

          {loading && topics.length === 0 && (
            <Box sx={{ display: "flex", justifyContent: "center", py: 4 }}>
              <CircularProgress />
            </Box>
          )}

          {topics.length === 0 && !loading && (
            <Box sx={{ textAlign: "center", py: 4 }}>
              <Typography color="text.secondary">
                Chưa có chủ đề nào. Hãy tạo chủ đề đầu tiên!
              </Typography>
            </Box>
          )}

          <Grid container spacing={2}>
            {topics.map((topic) => (
              <Grid item xs={12} key={topic.id}>
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
                      <Typography variant="h6" component="h3">
                        {topic.name}
                      </Typography>
                      <Chip
                        label={getStatusText(topic.status)}
                        color={getStatusColor(topic.status)}
                        size="small"
                      />
                    </Box>

                    <Typography
                      variant="body2"
                      color="text.secondary"
                      paragraph
                    >
                      {topic.description}
                    </Typography>

                    <Box
                      sx={{
                        display: "flex",
                        justifyContent: "space-between",
                        alignItems: "center",
                      }}
                    >
                      <Typography variant="caption" color="text.secondary">
                        Tạo: {new Date(topic.createdAt).toLocaleString("vi-VN")}
                        {topic.aiGenerated && " • AI Generated"}
                      </Typography>

                      <Box>
                        {topic.status === "PENDING" && (
                          <>
                            <IconButton
                              color="success"
                              onClick={() => approveTopic(topic.id)}
                              title="Duyệt chủ đề"
                            >
                              <ThumbUp />
                            </IconButton>
                            <IconButton
                              color="error"
                              onClick={() => rejectTopic(topic.id)}
                              title="Từ chối chủ đề"
                            >
                              <ThumbDown />
                            </IconButton>
                          </>
                        )}

                        <IconButton
                          color="error"
                          onClick={() =>
                            setDeleteDialog({ open: true, topicId: topic.id })
                          }
                          title="Xóa chủ đề"
                        >
                          <Delete />
                        </IconButton>
                      </Box>
                    </Box>
                  </CardContent>
                </Card>
              </Grid>
            ))}
          </Grid>
        </CardContent>
      </Card>

      {/* Delete Confirmation Dialog */}
      <Dialog
        open={deleteDialog.open}
        onClose={() => setDeleteDialog({ open: false, topicId: null })}
      >
        <DialogTitle>Xác nhận xóa</DialogTitle>
        <DialogContent>
          <Typography>
            Bạn có chắc chắn muốn xóa chủ đề này? Hành động này không thể hoàn
            tác.
          </Typography>
        </DialogContent>
        <DialogActions>
          <Button
            onClick={() => setDeleteDialog({ open: false, topicId: null })}
          >
            Hủy
          </Button>
          <Button
            onClick={() => deleteTopic(deleteDialog.topicId)}
            color="error"
            variant="contained"
          >
            Xóa
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default TopicGenerator;
