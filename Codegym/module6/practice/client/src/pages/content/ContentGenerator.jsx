import React, { useState } from "react";
import {
  Box,
  Grid,
  Typography,
  Card,
  CardContent,
  Button,
  TextField,
  MenuItem,
  Chip,
  Avatar,
  IconButton,
  Paper,
  Tabs,
  Tab,
  CircularProgress,
  Alert,
  Divider,
  List,
  ListItem,
  ListItemText,
  ListItemAvatar,
  ListItemSecondaryAction,
} from "@mui/material";
import {
  AutoAwesome,
  Image as ImageIcon,
  VideoCall,
  Article,
  Refresh,
  Save,
  Share,
  Edit,
  Delete,
  ContentCopy,
  Favorite,
  FavoriteBorder,
} from "@mui/icons-material";

const ContentGenerator = () => {
  const [activeTab, setActiveTab] = useState(0);
  const [loading, setLoading] = useState(false);
  const [formData, setFormData] = useState({
    topic: "",
    type: "post",
    tone: "professional",
    length: "medium",
    keywords: "",
    platform: "facebook",
  });
  const [generatedContent, setGeneratedContent] = useState("");
  const [savedContents, setSavedContents] = useState([
    {
      id: 1,
      title: "Khuyến mãi Black Friday",
      content:
        "🎉 BLACK FRIDAY 2024 - GIẢM GIÁ LÊN ĐẾN 70%! 🛍️\n\nĐây là cơ hội vàng để bạn sở hữu những sản phẩm yêu thích với mức giá không thể tốt hơn...",
      type: "post",
      platform: "facebook",
      createdAt: "2024-11-20",
      isLiked: true,
    },
    {
      id: 2,
      title: "Tips marketing hiệu quả",
      content:
        "5 Tips Marketing Hiệu Quả Cho Doanh Nghiệp Nhỏ:\n\n1. Hiểu rõ khách hàng mục tiêu\n2. Tạo nội dung có giá trị\n3. Sử dụng social media đúng cách...",
      type: "article",
      platform: "linkedin",
      createdAt: "2024-11-19",
      isLiked: false,
    },
  ]);

  const contentTypes = [
    { value: "post", label: "Bài đăng" },
    { value: "article", label: "Bài viết" },
    { value: "caption", label: "Caption" },
    { value: "story", label: "Story" },
  ];

  const tones = [
    { value: "professional", label: "Chuyên nghiệp" },
    { value: "friendly", label: "Thân thiện" },
    { value: "casual", label: "Thoải mái" },
    { value: "formal", label: "Trang trọng" },
    { value: "humorous", label: "Hài hước" },
  ];

  const lengths = [
    { value: "short", label: "Ngắn" },
    { value: "medium", label: "Trung bình" },
    { value: "long", label: "Dài" },
  ];

  const platforms = [
    { value: "facebook", label: "Facebook" },
    { value: "instagram", label: "Instagram" },
    { value: "linkedin", label: "LinkedIn" },
    { value: "twitter", label: "Twitter" },
  ];

  const handleTabChange = (event, newValue) => {
    setActiveTab(newValue);
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleGenerate = async () => {
    setLoading(true);
    // Simulate AI content generation
    await new Promise((resolve) => setTimeout(resolve, 2000));

    const mockContent = {
      post: `🌟 ${
        formData.topic
      } 🌟\n\nChúng tôi rất vui mừng chia sẻ với bạn về ${
        formData.topic
      }. Đây là một cơ hội tuyệt vời để bạn khám phá những điều mới mẻ và thú vị.\n\n✨ Những lợi ích chính:\n• Tiết kiệm thời gian và chi phí\n• Nâng cao hiệu quả công việc\n• Mang lại trải nghiệm tốt nhất\n\n👉 Hãy để lại bình luận để biết thêm thông tin chi tiết!\n\n#${formData.topic.replace(
        /\s+/g,
        ""
      )} #Marketing #Business`,
      article: `# ${formData.topic}: Hướng Dẫn Toàn Diện\n\n## Giới thiệu\n\n${formData.topic} đang trở thành một xu hướng quan trọng trong thời đại số. Bài viết này sẽ cung cấp cho bạn cái nhìn tổng quan và những insights quý giá.\n\n## Tại sao ${formData.topic} quan trọng?\n\nTrong bối cảnh kinh doanh hiện tại, ${formData.topic} mang lại nhiều lợi ích:\n\n- Tăng hiệu quả hoạt động\n- Cải thiện trải nghiệm khách hàng\n- Tối ưu hóa chi phí\n\n## Kết luận\n\n${formData.topic} không chỉ là một xu hướng mà còn là một yếu tố quan trọng quyết định thành công của doanh nghiệp.`,
      caption: `${
        formData.topic
      } ✨\n\nMoment đáng nhớ! 📸\n\n#${formData.topic.replace(
        /\s+/g,
        ""
      )} #Memories #Life`,
      story: `🔥 ${formData.topic}\n\nSwipe up để xem thêm! 👆`,
    };

    setGeneratedContent(mockContent[formData.type] || mockContent.post);
    setLoading(false);
  };

  const handleSave = () => {
    if (generatedContent) {
      const newContent = {
        id: Date.now(),
        title: formData.topic || "Nội dung không có tiêu đề",
        content: generatedContent,
        type: formData.type,
        platform: formData.platform,
        createdAt: new Date().toISOString().split("T")[0],
        isLiked: false,
      };
      setSavedContents([newContent, ...savedContents]);
      setGeneratedContent("");
      setFormData({
        topic: "",
        type: "post",
        tone: "professional",
        length: "medium",
        keywords: "",
        platform: "facebook",
      });
    }
  };

  const handleToggleLike = (id) => {
    setSavedContents(
      savedContents.map((content) =>
        content.id === id ? { ...content, isLiked: !content.isLiked } : content
      )
    );
  };

  const handleDeleteContent = (id) => {
    setSavedContents(savedContents.filter((content) => content.id !== id));
  };

  const handleCopyContent = (content) => {
    navigator.clipboard.writeText(content);
    // Show toast notification
  };

  return (
    <Box>
      {/* Header */}
      <Box sx={{ mb: 4 }}>
        <Typography variant="h4" gutterBottom>
          Tạo nội dung AI
        </Typography>
        <Typography variant="body1" color="text.secondary">
          Sử dụng AI để tạo nội dung chất lượng cao cho social media
        </Typography>
      </Box>

      {/* Tabs */}
      <Paper sx={{ mb: 3 }}>
        <Tabs value={activeTab} onChange={handleTabChange}>
          <Tab
            icon={<AutoAwesome />}
            label="Tạo nội dung"
            iconPosition="start"
          />
          <Tab
            icon={<ImageIcon />}
            label="Tạo hình ảnh"
            iconPosition="start"
            disabled
          />
          <Tab
            icon={<VideoCall />}
            label="Tạo video"
            iconPosition="start"
            disabled
          />
          <Tab
            icon={<Article />}
            label="Nội dung đã lưu"
            iconPosition="start"
          />
        </Tabs>
      </Paper>

      {/* Content Generator Tab */}
      {activeTab === 0 && (
        <Grid container spacing={3}>
          <Grid item xs={12} md={6}>
            <Card>
              <CardContent>
                <Typography variant="h6" gutterBottom>
                  Thông tin nội dung
                </Typography>

                <TextField
                  fullWidth
                  label="Chủ đề hoặc ý tưởng"
                  name="topic"
                  value={formData.topic}
                  onChange={handleChange}
                  placeholder="VD: Khuyến mãi Black Friday"
                  sx={{ mb: 2 }}
                />

                <Grid container spacing={2} sx={{ mb: 2 }}>
                  <Grid item xs={12} sm={6}>
                    <TextField
                      fullWidth
                      select
                      label="Loại nội dung"
                      name="type"
                      value={formData.type}
                      onChange={handleChange}
                    >
                      {contentTypes.map((option) => (
                        <MenuItem key={option.value} value={option.value}>
                          {option.label}
                        </MenuItem>
                      ))}
                    </TextField>
                  </Grid>
                  <Grid item xs={12} sm={6}>
                    <TextField
                      fullWidth
                      select
                      label="Nền tảng"
                      name="platform"
                      value={formData.platform}
                      onChange={handleChange}
                    >
                      {platforms.map((option) => (
                        <MenuItem key={option.value} value={option.value}>
                          {option.label}
                        </MenuItem>
                      ))}
                    </TextField>
                  </Grid>
                </Grid>

                <Grid container spacing={2} sx={{ mb: 2 }}>
                  <Grid item xs={12} sm={6}>
                    <TextField
                      fullWidth
                      select
                      label="Phong cách"
                      name="tone"
                      value={formData.tone}
                      onChange={handleChange}
                    >
                      {tones.map((option) => (
                        <MenuItem key={option.value} value={option.value}>
                          {option.label}
                        </MenuItem>
                      ))}
                    </TextField>
                  </Grid>
                  <Grid item xs={12} sm={6}>
                    <TextField
                      fullWidth
                      select
                      label="Độ dài"
                      name="length"
                      value={formData.length}
                      onChange={handleChange}
                    >
                      {lengths.map((option) => (
                        <MenuItem key={option.value} value={option.value}>
                          {option.label}
                        </MenuItem>
                      ))}
                    </TextField>
                  </Grid>
                </Grid>

                <TextField
                  fullWidth
                  label="Từ khóa (tùy chọn)"
                  name="keywords"
                  value={formData.keywords}
                  onChange={handleChange}
                  placeholder="VD: giảm giá, khuyến mãi, sản phẩm"
                  sx={{ mb: 3 }}
                />

                <Button
                  fullWidth
                  variant="contained"
                  size="large"
                  startIcon={
                    loading ? <CircularProgress size={20} /> : <AutoAwesome />
                  }
                  onClick={handleGenerate}
                  disabled={loading || !formData.topic}
                  sx={{
                    background:
                      "linear-gradient(135deg, #667eea 0%, #764ba2 100%)",
                    "&:hover": {
                      background:
                        "linear-gradient(135deg, #5a6fd8 0%, #6a4190 100%)",
                    },
                  }}
                >
                  {loading ? "Đang tạo nội dung..." : "Tạo nội dung"}
                </Button>
              </CardContent>
            </Card>
          </Grid>

          <Grid item xs={12} md={6}>
            <Card>
              <CardContent>
                <Typography variant="h6" gutterBottom>
                  Kết quả
                </Typography>

                {generatedContent ? (
                  <Box>
                    <Paper
                      sx={{
                        p: 2,
                        mb: 2,
                        bgcolor: "grey.50",
                        border: "1px solid",
                        borderColor: "grey.200",
                        minHeight: 200,
                        maxHeight: 400,
                        overflow: "auto",
                      }}
                    >
                      <Typography
                        variant="body1"
                        sx={{
                          whiteSpace: "pre-wrap",
                          fontFamily: "monospace",
                        }}
                      >
                        {generatedContent}
                      </Typography>
                    </Paper>

                    <Box sx={{ display: "flex", gap: 1, flexWrap: "wrap" }}>
                      <Button
                        variant="contained"
                        startIcon={<Save />}
                        onClick={handleSave}
                      >
                        Lưu
                      </Button>
                      <Button
                        variant="outlined"
                        startIcon={<ContentCopy />}
                        onClick={() => handleCopyContent(generatedContent)}
                      >
                        Sao chép
                      </Button>
                      <Button variant="outlined" startIcon={<Share />}>
                        Chia sẻ
                      </Button>
                      <Button
                        variant="outlined"
                        startIcon={<Refresh />}
                        onClick={handleGenerate}
                      >
                        Tạo lại
                      </Button>
                    </Box>
                  </Box>
                ) : (
                  <Box
                    sx={{
                      display: "flex",
                      flexDirection: "column",
                      alignItems: "center",
                      justifyContent: "center",
                      minHeight: 200,
                      color: "text.secondary",
                    }}
                  >
                    <AutoAwesome sx={{ fontSize: 48, mb: 2, opacity: 0.5 }} />
                    <Typography variant="body1" align="center">
                      Nhập thông tin và nhấn "Tạo nội dung" để AI tạo nội dung
                      cho bạn
                    </Typography>
                  </Box>
                )}
              </CardContent>
            </Card>
          </Grid>
        </Grid>
      )}

      {/* Saved Content Tab */}
      {activeTab === 3 && (
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
              <Typography variant="h6">Nội dung đã lưu</Typography>
              <Typography variant="body2" color="text.secondary">
                {savedContents.length} nội dung
              </Typography>
            </Box>

            {savedContents.length === 0 ? (
              <Alert severity="info">
                Chưa có nội dung nào được lưu. Hãy tạo nội dung đầu tiên!
              </Alert>
            ) : (
              <List>
                {savedContents.map((content, index) => (
                  <Box key={content.id}>
                    <ListItem alignItems="flex-start">
                      <ListItemAvatar>
                        <Avatar>
                          <Article />
                        </Avatar>
                      </ListItemAvatar>
                      <ListItemText
                        primary={
                          <Box
                            sx={{
                              display: "flex",
                              alignItems: "center",
                              gap: 1,
                              mb: 1,
                            }}
                          >
                            <Typography
                              variant="subtitle1"
                              sx={{ fontWeight: 500 }}
                            >
                              {content.title}
                            </Typography>
                            <Chip label={content.type} size="small" />
                            <Chip
                              label={content.platform}
                              size="small"
                              variant="outlined"
                            />
                          </Box>
                        }
                        secondary={
                          <Box>
                            <Typography
                              variant="body2"
                              color="text.secondary"
                              sx={{
                                display: "-webkit-box",
                                WebkitLineClamp: 3,
                                WebkitBoxOrient: "vertical",
                                overflow: "hidden",
                                mb: 1,
                              }}
                            >
                              {content.content}
                            </Typography>
                            <Typography
                              variant="caption"
                              color="text.secondary"
                            >
                              Tạo ngày: {content.createdAt}
                            </Typography>
                          </Box>
                        }
                      />
                      <ListItemSecondaryAction>
                        <Box
                          sx={{ display: "flex", alignItems: "center", gap: 1 }}
                        >
                          <IconButton
                            onClick={() => handleToggleLike(content.id)}
                            color={content.isLiked ? "error" : "default"}
                          >
                            {content.isLiked ? (
                              <Favorite />
                            ) : (
                              <FavoriteBorder />
                            )}
                          </IconButton>
                          <IconButton
                            onClick={() => handleCopyContent(content.content)}
                          >
                            <ContentCopy />
                          </IconButton>
                          <IconButton>
                            <Edit />
                          </IconButton>
                          <IconButton
                            onClick={() => handleDeleteContent(content.id)}
                            color="error"
                          >
                            <Delete />
                          </IconButton>
                        </Box>
                      </ListItemSecondaryAction>
                    </ListItem>
                    {index < savedContents.length - 1 && <Divider />}
                  </Box>
                ))}
              </List>
            )}
          </CardContent>
        </Card>
      )}

      {/* Other tabs placeholder */}
      {(activeTab === 1 || activeTab === 2) && (
        <Card>
          <CardContent>
            <Box
              sx={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center",
                minHeight: 200,
                color: "text.secondary",
              }}
            >
              {activeTab === 1 ? (
                <ImageIcon sx={{ fontSize: 48, mb: 2 }} />
              ) : (
                <VideoCall sx={{ fontSize: 48, mb: 2 }} />
              )}
              <Typography variant="h6" gutterBottom>
                {activeTab === 1 ? "Tạo hình ảnh AI" : "Tạo video AI"}
              </Typography>
              <Typography variant="body2" align="center">
                Tính năng này sẽ sớm được ra mắt. Hãy theo dõi để cập nhật!
              </Typography>
            </Box>
          </CardContent>
        </Card>
      )}
    </Box>
  );
};

export default ContentGenerator;
