import React from "react";
import {
  Box,
  Container,
  Typography,
  Button,
  Grid,
  Card,
  CardContent,
  Chip,
  Avatar,
  Rating,
  Paper,
} from "@mui/material";
import {
  AutoAwesome,
  Schedule,
  TrendingUp,
  Group,
  CheckCircle,
  Facebook,
  Instagram,
  LinkedIn,
  Twitter,
  YouTube,
} from "@mui/icons-material";
import { useNavigate } from "react-router-dom";
import Header from "../components/landing/Header";
import Footer from "../components/landing/Footer";

const LandingPage = () => {
  const navigate = useNavigate();

  const features = [
    {
      icon: <AutoAwesome sx={{ fontSize: 40 }} />,
      title: "AI Tạo Nội Dung",
      description:
        "Tự động sinh ra nội dung bài viết và hình ảnh chất lượng cao với công nghệ AI tiên tiến",
      color: "#3B82F6",
    },
    {
      icon: <Schedule sx={{ fontSize: 40 }} />,
      title: "Lập Lịch Thông Minh",
      description:
        "Hẹn giờ đăng bài tự động cho nhiều tài khoản mạng xã hội cùng lúc",
      color: "#8B5CF6",
    },
    {
      icon: <TrendingUp sx={{ fontSize: 40 }} />,
      title: "Phân Tích Hiệu Quả",
      description:
        "Theo dõi và phân tích hiệu quả các chiến dịch marketing với báo cáo chi tiết",
      color: "#10B981",
    },
    {
      icon: <Group sx={{ fontSize: 40 }} />,
      title: "Quản Lý Đa Kênh",
      description:
        "Quản lý tất cả các tài khoản Facebook, Instagram, LinkedIn từ một nền tảng",
      color: "#F59E0B",
    },
  ];

  const pricingPlans = [
    {
      name: "Gói Cơ Bản",
      price: "99,000",
      period: "tháng",
      description: "Phù hợp cho cá nhân và doanh nghiệp nhỏ",
      features: [
        "2 tài khoản mạng xã hội",
        "30 bài đăng/tháng",
        "AI tạo nội dung cơ bản",
        "Lập lịch đăng bài",
        "Báo cáo cơ bản",
      ],
      popular: false,
    },
    {
      name: "Gói Chuyên Nghiệp",
      price: "299,000",
      period: "tháng",
      description: "Lý tưởng cho doanh nghiệp vừa",
      features: [
        "10 tài khoản mạng xã hội",
        "100 bài đăng/tháng",
        "AI tạo nội dung nâng cao",
        "Quản lý chiến dịch",
        "Phân tích chi tiết",
        "Hỗ trợ ưu tiên",
      ],
      popular: true,
    },
    {
      name: "Gói Doanh Nghiệp",
      price: "999,000",
      period: "tháng",
      description: "Cho doanh nghiệp lớn và agency",
      features: [
        "Không giới hạn tài khoản",
        "Không giới hạn bài đăng",
        "AI tạo nội dung cao cấp",
        "Quản lý team",
        "API tích hợp",
        "Hỗ trợ 24/7",
        "Tư vấn chiến lược",
      ],
      popular: false,
    },
  ];

  const testimonials = [
    {
      name: "Nguyễn Minh Tâm",
      position: "CEO, Tech Startup",
      avatar: "/api/placeholder/40/40",
      rating: 5,
      comment:
        "Nền tảng này đã giúp chúng tôi tăng 300% tương tác trên mạng xã hội. AI tạo nội dung rất ấn tượng!",
    },
    {
      name: "Trần Thị Hương",
      position: "Marketing Manager",
      avatar: "/api/placeholder/40/40",
      rating: 5,
      comment:
        "Tiết kiệm được rất nhiều thời gian. Chỉ cần 5 phút mỗi tuần để quản lý toàn bộ social media.",
    },
    {
      name: "Lê Văn Đức",
      position: "Founder, E-commerce",
      avatar: "/api/placeholder/40/40",
      rating: 5,
      comment:
        "ROI từ social media của chúng tôi tăng gấp đôi sau khi sử dụng nền tảng này.",
    },
  ];

  const socialPlatforms = [
    { icon: <Facebook />, name: "Facebook", color: "#1877F2" },
    { icon: <Instagram />, name: "Instagram", color: "#E4405F" },
    { icon: <LinkedIn />, name: "LinkedIn", color: "#0A66C2" },
    { icon: <Twitter />, name: "Twitter", color: "#1DA1F2" },
    { icon: <YouTube />, name: "YouTube", color: "#FF0000" },
  ];

  return (
    <Box>
      <Header />

      {/* Hero Section */}
      <Box
        sx={{
          background: "linear-gradient(135deg, #272882 0%, #4a4ba8 100%)",
          color: "white",
          pt: 12,
          pb: 8,
          position: "relative",
          overflow: "hidden",
        }}
      >
        <Container maxWidth="lg">
          <Grid container spacing={4} alignItems="center">
            <Grid item xs={12} md={6}>
              <Typography variant="h1" gutterBottom>
                Tự Động Hóa{" "}
                <Box component="span" sx={{ color: "#a8b3ff" }}>
                  Social Media Marketing
                </Box>
              </Typography>
              <Typography variant="h5" sx={{ mb: 4, opacity: 0.9 }}>
                Phần mềm giúp bạn tự động hoá social media marketing cho nhiều
                Fanpage cùng lúc. Gia tăng lượng người theo dõi, có thêm khách
                hàng mới. Tất cả chỉ với 5 phút mỗi tuần.
              </Typography>
              <Box sx={{ display: "flex", gap: 2, flexWrap: "wrap" }}>
                <Button
                  variant="contained"
                  size="large"
                  sx={{
                    bgcolor: "white",
                    color: "primary.main",
                    "&:hover": { bgcolor: "grey.100" },
                    px: 4,
                    py: 1.5,
                  }}
                  onClick={() => navigate("/create-workspace")}
                >
                  Tạo Workspace Miễn Phí
                </Button>
                <Button
                  variant="outlined"
                  size="large"
                  sx={{
                    borderColor: "white",
                    color: "white",
                    "&:hover": {
                      borderColor: "white",
                      bgcolor: "rgba(255,255,255,0.1)",
                    },
                    px: 4,
                    py: 1.5,
                  }}
                >
                  Xem Demo
                </Button>
              </Box>

              {/* Social Platforms */}
              <Box sx={{ mt: 4 }}>
                <Typography variant="body2" sx={{ mb: 2, opacity: 0.8 }}>
                  Hỗ trợ các nền tảng:
                </Typography>
                <Box sx={{ display: "flex", gap: 2, flexWrap: "wrap" }}>
                  {socialPlatforms.map((platform) => (
                    <Chip
                      key={platform.name}
                      icon={platform.icon}
                      label={platform.name}
                      sx={{
                        bgcolor: "rgba(255,255,255,0.1)",
                        color: "white",
                        "& .MuiChip-icon": { color: "white" },
                      }}
                    />
                  ))}
                </Box>
              </Box>
            </Grid>
            <Grid item xs={12} md={6}>
              <Box
                sx={{
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "center",
                  height: 400,
                }}
              >
                <Paper
                  sx={{
                    p: 4,
                    borderRadius: 4,
                    boxShadow: "0 20px 40px rgba(0,0,0,0.1)",
                    transform: "rotate(5deg)",
                    bgcolor: "background.paper",
                  }}
                >
                  <Typography variant="h6" color="text.primary" gutterBottom>
                    📊 Dashboard Preview
                  </Typography>
                  <Typography variant="body2" color="text.secondary">
                    Giao diện quản lý trực quan và dễ sử dụng
                  </Typography>
                </Paper>
              </Box>
            </Grid>
          </Grid>
        </Container>
      </Box>

      {/* Features Section */}
      <Container maxWidth="lg" sx={{ py: 8 }}>
        <Box sx={{ textAlign: "center", mb: 6 }}>
          <Typography variant="h2" gutterBottom>
            Tính Năng Nổi Bật
          </Typography>
          <Typography
            variant="h6"
            color="text.secondary"
            sx={{ maxWidth: 600, mx: "auto" }}
          >
            Những công cụ mạnh mẽ giúp bạn tối ưu hóa hiệu quả marketing trên
            mạng xã hội
          </Typography>
        </Box>

        <Grid container spacing={4}>
          {features.map((feature, index) => (
            <Grid item xs={12} sm={6} md={3} key={index}>
              <Card
                sx={{
                  height: "100%",
                  textAlign: "center",
                  p: 3,
                  border: "1px solid",
                  borderColor: "grey.200",
                  "&:hover": {
                    boxShadow: "0 8px 25px rgba(0,0,0,0.1)",
                    transform: "translateY(-5px)",
                    transition: "all 0.3s ease",
                  },
                }}
              >
                <Box
                  sx={{
                    color: feature.color,
                    mb: 2,
                    display: "flex",
                    justifyContent: "center",
                  }}
                >
                  {feature.icon}
                </Box>
                <Typography variant="h6" gutterBottom>
                  {feature.title}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  {feature.description}
                </Typography>
              </Card>
            </Grid>
          ))}
        </Grid>
      </Container>

      {/* Pricing Section */}
      <Box sx={{ bgcolor: "background.default", py: 8 }}>
        <Container maxWidth="lg">
          <Box sx={{ textAlign: "center", mb: 6 }}>
            <Typography variant="h2" gutterBottom>
              Bảng Giá
            </Typography>
            <Typography variant="h6" color="text.secondary">
              Chọn gói phù hợp với nhu cầu của bạn
            </Typography>
          </Box>

          <Grid container spacing={4} justifyContent="center">
            {pricingPlans.map((plan, index) => (
              <Grid item xs={12} sm={6} md={4} key={index}>
                <Card
                  sx={{
                    height: "100%",
                    position: "relative",
                    border: plan.popular ? 2 : 1,
                    borderColor: plan.popular ? "primary.main" : "grey.200",
                    "&:hover": {
                      boxShadow: "0 8px 25px rgba(0,0,0,0.1)",
                    },
                  }}
                >
                  {plan.popular && (
                    <Chip
                      label="Phổ Biến"
                      color="primary"
                      sx={{
                        position: "absolute",
                        top: -10,
                        left: "50%",
                        transform: "translateX(-50%)",
                      }}
                    />
                  )}
                  <CardContent sx={{ p: 4, textAlign: "center" }}>
                    <Typography variant="h5" gutterBottom>
                      {plan.name}
                    </Typography>
                    <Typography
                      variant="body2"
                      color="text.secondary"
                      sx={{ mb: 3 }}
                    >
                      {plan.description}
                    </Typography>
                    <Box sx={{ mb: 3 }}>
                      <Typography variant="h3" color="primary.main">
                        {plan.price.toLocaleString()}đ
                      </Typography>
                      <Typography variant="body2" color="text.secondary">
                        /{plan.period}
                      </Typography>
                    </Box>
                    <Box sx={{ mb: 3 }}>
                      {plan.features.map((feature, idx) => (
                        <Box
                          key={idx}
                          sx={{
                            display: "flex",
                            alignItems: "center",
                            mb: 1,
                            justifyContent: "flex-start",
                          }}
                        >
                          <CheckCircle
                            sx={{ color: "success.main", mr: 1, fontSize: 16 }}
                          />
                          <Typography variant="body2">{feature}</Typography>
                        </Box>
                      ))}
                    </Box>
                    <Button
                      variant={plan.popular ? "contained" : "outlined"}
                      fullWidth
                      size="large"
                      onClick={() => navigate("/register")}
                    >
                      Bắt Đầu Ngay
                    </Button>
                  </CardContent>
                </Card>
              </Grid>
            ))}
          </Grid>
        </Container>
      </Box>

      {/* Testimonials */}
      <Container maxWidth="lg" sx={{ py: 8 }}>
        <Box sx={{ textAlign: "center", mb: 6 }}>
          <Typography variant="h2" gutterBottom>
            Khách Hàng Nói Gì
          </Typography>
          <Typography variant="h6" color="text.secondary">
            Hơn 1000+ doanh nghiệp đã tin tưởng sử dụng nền tảng của chúng tôi
          </Typography>
        </Box>

        <Grid container spacing={4}>
          {testimonials.map((testimonial, index) => (
            <Grid item xs={12} md={4} key={index}>
              <Card sx={{ height: "100%", p: 3 }}>
                <Box sx={{ display: "flex", alignItems: "center", mb: 2 }}>
                  <Avatar src={testimonial.avatar} sx={{ mr: 2 }} />
                  <Box>
                    <Typography variant="subtitle1">
                      {testimonial.name}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                      {testimonial.position}
                    </Typography>
                  </Box>
                </Box>
                <Rating
                  value={testimonial.rating}
                  readOnly
                  size="small"
                  sx={{ mb: 2 }}
                />
                <Typography variant="body2" sx={{ fontStyle: "italic" }}>
                  "{testimonial.comment}"
                </Typography>
              </Card>
            </Grid>
          ))}
        </Grid>
      </Container>

      {/* CTA Section */}
      <Box
        sx={{
          bgcolor: "primary.main",
          color: "white",
          py: 8,
          textAlign: "center",
        }}
      >
        <Container maxWidth="md">
          <Typography variant="h2" gutterBottom>
            Sẵn Sàng Bắt Đầu?
          </Typography>
          <Typography variant="h6" sx={{ mb: 4, opacity: 0.9 }}>
            Dùng thử miễn phí 14 ngày, không cần thẻ tín dụng
          </Typography>
          <Button
            variant="contained"
            size="large"
            sx={{
              bgcolor: "white",
              color: "primary.main",
              "&:hover": { bgcolor: "grey.100" },
              px: 4,
              py: 1.5,
            }}
            onClick={() => navigate("/register")}
          >
            Đăng Ký Miễn Phí Ngay
          </Button>
        </Container>
      </Box>

      <Footer />
    </Box>
  );
};

export default LandingPage;
