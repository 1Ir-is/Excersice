import React from "react";
import {
  Box,
  Container,
  Grid,
  Typography,
  Link,
  IconButton,
  Divider,
} from "@mui/material";
import {
  Facebook,
  Instagram,
  LinkedIn,
  Twitter,
  YouTube,
  Email,
  Phone,
  LocationOn,
} from "@mui/icons-material";

const Footer = () => {
  return (
    <Box
      component="footer"
      sx={{
        bgcolor: "grey.900",
        color: "white",
        pt: 6,
        pb: 3,
      }}
    >
      <Container maxWidth="lg">
        <Grid container spacing={4}>
          {/* Company Info */}
          <Grid item xs={12} sm={6} md={3}>
            <Box sx={{ mb: 3 }}>
              <Typography variant="h6" gutterBottom sx={{ fontWeight: "bold" }}>
                SocialAuto
              </Typography>
              <Typography variant="body2" sx={{ color: "grey.400", mb: 3 }}>
                Nền tảng tự động hóa social media marketing hàng đầu Việt Nam.
                Giúp doanh nghiệp tăng trưởng bền vững trên mạng xã hội.
              </Typography>

              {/* Social Links */}
              <Box sx={{ display: "flex", gap: 1 }}>
                <IconButton
                  size="small"
                  sx={{
                    color: "grey.400",
                    "&:hover": { color: "#1877F2" },
                  }}
                >
                  <Facebook />
                </IconButton>
                <IconButton
                  size="small"
                  sx={{
                    color: "grey.400",
                    "&:hover": { color: "#E4405F" },
                  }}
                >
                  <Instagram />
                </IconButton>
                <IconButton
                  size="small"
                  sx={{
                    color: "grey.400",
                    "&:hover": { color: "#0A66C2" },
                  }}
                >
                  <LinkedIn />
                </IconButton>
                <IconButton
                  size="small"
                  sx={{
                    color: "grey.400",
                    "&:hover": { color: "#1DA1F2" },
                  }}
                >
                  <Twitter />
                </IconButton>
                <IconButton
                  size="small"
                  sx={{
                    color: "grey.400",
                    "&:hover": { color: "#FF0000" },
                  }}
                >
                  <YouTube />
                </IconButton>
              </Box>
            </Box>
          </Grid>

          {/* Product Links */}
          <Grid item xs={12} sm={6} md={2}>
            <Typography variant="h6" gutterBottom sx={{ fontWeight: "bold" }}>
              Sản phẩm
            </Typography>
            <Box sx={{ display: "flex", flexDirection: "column", gap: 1 }}>
              <Link
                href="#"
                color="grey.400"
                underline="hover"
                sx={{ "&:hover": { color: "white" } }}
              >
                Tính năng
              </Link>
              <Link
                href="#"
                color="grey.400"
                underline="hover"
                sx={{ "&:hover": { color: "white" } }}
              >
                Bảng giá
              </Link>
              <Link
                href="#"
                color="grey.400"
                underline="hover"
                sx={{ "&:hover": { color: "white" } }}
              >
                API
              </Link>
              <Link
                href="#"
                color="grey.400"
                underline="hover"
                sx={{ "&:hover": { color: "white" } }}
              >
                Tích hợp
              </Link>
            </Box>
          </Grid>

          {/* Support Links */}
          <Grid item xs={12} sm={6} md={2}>
            <Typography variant="h6" gutterBottom sx={{ fontWeight: "bold" }}>
              Hỗ trợ
            </Typography>
            <Box sx={{ display: "flex", flexDirection: "column", gap: 1 }}>
              <Link
                href="#"
                color="grey.400"
                underline="hover"
                sx={{ "&:hover": { color: "white" } }}
              >
                Trung tâm trợ giúp
              </Link>
              <Link
                href="#"
                color="grey.400"
                underline="hover"
                sx={{ "&:hover": { color: "white" } }}
              >
                Hướng dẫn
              </Link>
              <Link
                href="#"
                color="grey.400"
                underline="hover"
                sx={{ "&:hover": { color: "white" } }}
              >
                Blog
              </Link>
              <Link
                href="#"
                color="grey.400"
                underline="hover"
                sx={{ "&:hover": { color: "white" } }}
              >
                Liên hệ
              </Link>
            </Box>
          </Grid>

          {/* Company Links */}
          <Grid item xs={12} sm={6} md={2}>
            <Typography variant="h6" gutterBottom sx={{ fontWeight: "bold" }}>
              Công ty
            </Typography>
            <Box sx={{ display: "flex", flexDirection: "column", gap: 1 }}>
              <Link
                href="#"
                color="grey.400"
                underline="hover"
                sx={{ "&:hover": { color: "white" } }}
              >
                Về chúng tôi
              </Link>
              <Link
                href="#"
                color="grey.400"
                underline="hover"
                sx={{ "&:hover": { color: "white" } }}
              >
                Nghề nghiệp
              </Link>
              <Link
                href="#"
                color="grey.400"
                underline="hover"
                sx={{ "&:hover": { color: "white" } }}
              >
                Đối tác
              </Link>
              <Link
                href="#"
                color="grey.400"
                underline="hover"
                sx={{ "&:hover": { color: "white" } }}
              >
                Tin tức
              </Link>
            </Box>
          </Grid>

          {/* Contact Info */}
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="h6" gutterBottom sx={{ fontWeight: "bold" }}>
              Liên hệ
            </Typography>
            <Box sx={{ display: "flex", flexDirection: "column", gap: 2 }}>
              <Box sx={{ display: "flex", alignItems: "center", gap: 1 }}>
                <Email sx={{ color: "grey.400", fontSize: 20 }} />
                <Typography variant="body2" sx={{ color: "grey.400" }}>
                  contact@socialauto.vn
                </Typography>
              </Box>
              <Box sx={{ display: "flex", alignItems: "center", gap: 1 }}>
                <Phone sx={{ color: "grey.400", fontSize: 20 }} />
                <Typography variant="body2" sx={{ color: "grey.400" }}>
                  +84 907 123 456
                </Typography>
              </Box>
              <Box sx={{ display: "flex", alignItems: "flex-start", gap: 1 }}>
                <LocationOn sx={{ color: "grey.400", fontSize: 20, mt: 0.5 }} />
                <Typography variant="body2" sx={{ color: "grey.400" }}>
                  Tầng 10, Tòa nhà ABC, 123 Nguyễn Văn Linh, Quận 7, TP.HCM
                </Typography>
              </Box>
            </Box>
          </Grid>
        </Grid>

        <Divider sx={{ my: 4, borderColor: "grey.700" }} />

        {/* Bottom Section */}
        <Grid container justifyContent="space-between" alignItems="center">
          <Grid item xs={12} md={6}>
            <Typography variant="body2" sx={{ color: "grey.400" }}>
              © 2024 SocialAuto. Tất cả quyền được bảo lưu.
            </Typography>
          </Grid>
          <Grid item xs={12} md={6}>
            <Box
              sx={{
                display: "flex",
                justifyContent: { xs: "flex-start", md: "flex-end" },
                gap: 3,
                mt: { xs: 2, md: 0 },
              }}
            >
              <Link
                href="#"
                color="grey.400"
                underline="hover"
                variant="body2"
                sx={{ "&:hover": { color: "white" } }}
              >
                Điều khoản sử dụng
              </Link>
              <Link
                href="#"
                color="grey.400"
                underline="hover"
                variant="body2"
                sx={{ "&:hover": { color: "white" } }}
              >
                Chính sách bảo mật
              </Link>
              <Link
                href="#"
                color="grey.400"
                underline="hover"
                variant="body2"
                sx={{ "&:hover": { color: "white" } }}
              >
                Cookie
              </Link>
            </Box>
          </Grid>
        </Grid>
      </Container>
    </Box>
  );
};

export default Footer;
