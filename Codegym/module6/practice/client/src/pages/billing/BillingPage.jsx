import React, { useState } from "react";
import {
  Box,
  Grid,
  Typography,
  Card,
  CardContent,
  Button,
  Chip,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  TextField,
  FormControl,
  FormLabel,
  RadioGroup,
  FormControlLabel,
  Radio,
  Alert,
  List,
  ListItem,
  ListItemText,
  ListItemIcon,
  Divider,
  Switch,
} from "@mui/material";
import {
  CreditCard,
  Download,
  CheckCircle,
  Cancel,
  Star,
  StarBorder,
  Payment,
  Receipt,
  Security,
  AutoAwesome,
  Business,
  Analytics,
  Schedule,
  Group,
  Storage,
  Support,
} from "@mui/icons-material";

const BillingPage = () => {
  const [showPaymentDialog, setShowPaymentDialog] = useState(false);
  const [showCancelDialog, setShowCancelDialog] = useState(false);
  const [selectedPlan, setSelectedPlan] = useState("");
  const [billingCycle, setBillingCycle] = useState("monthly");
  const [autoRenewal, setAutoRenewal] = useState(true);

  const currentPlan = {
    name: "Gói Pro",
    price: "299,000",
    cycle: "tháng",
    features: [
      "Tối đa 10 tài khoản mạng xã hội",
      "Lên lịch không giới hạn",
      "Phân tích chi tiết",
      "AI tạo nội dung",
      "Hỗ trợ 24/7",
    ],
    nextBilling: "2024-12-20",
    status: "active",
  };

  const plans = [
    {
      id: "basic",
      name: "Gói Cơ Bản",
      monthlyPrice: "99,000",
      yearlyPrice: "990,000",
      popular: false,
      features: [
        "3 tài khoản mạng xã hội",
        "50 bài đăng/tháng",
        "Phân tích cơ bản",
        "Hỗ trợ email",
      ],
      icon: <Star sx={{ color: "#10B981" }} />,
    },
    {
      id: "pro",
      name: "Gói Pro",
      monthlyPrice: "299,000",
      yearlyPrice: "2,990,000",
      popular: true,
      features: [
        "10 tài khoản mạng xã hội",
        "Lên lịch không giới hạn",
        "Phân tích chi tiết",
        "AI tạo nội dung",
        "Hỗ trợ 24/7",
      ],
      icon: <AutoAwesome sx={{ color: "#3B82F6" }} />,
    },
    {
      id: "enterprise",
      name: "Gói Doanh Nghiệp",
      monthlyPrice: "999,000",
      yearlyPrice: "9,990,000",
      popular: false,
      features: [
        "Không giới hạn tài khoản",
        "Tính năng nâng cao",
        "Báo cáo tùy chỉnh",
        "API tích hợp",
        "Quản lý nhóm",
        "Đào tạo chuyên biệt",
      ],
      icon: <Business sx={{ color: "#8B5CF6" }} />,
    },
  ];

  const billingHistory = [
    {
      id: "INV-001",
      date: "2024-11-20",
      amount: "299,000",
      status: "paid",
      description: "Gói Pro - Tháng 11/2024",
    },
    {
      id: "INV-002",
      date: "2024-10-20",
      amount: "299,000",
      status: "paid",
      description: "Gói Pro - Tháng 10/2024",
    },
    {
      id: "INV-003",
      date: "2024-09-20",
      amount: "299,000",
      status: "paid",
      description: "Gói Pro - Tháng 9/2024",
    },
  ];

  const paymentMethods = [
    {
      id: 1,
      type: "visa",
      last4: "4242",
      expiryMonth: "12",
      expiryYear: "2025",
      isDefault: true,
    },
    {
      id: 2,
      type: "mastercard",
      last4: "5555",
      expiryMonth: "08",
      expiryYear: "2026",
      isDefault: false,
    },
  ];

  const handleUpgrade = (planId) => {
    setSelectedPlan(planId);
    setShowPaymentDialog(true);
  };

  const handlePayment = () => {
    setShowPaymentDialog(false);
    // Handle payment processing
    console.log("Processing payment for plan:", selectedPlan);
  };

  const handleCancelSubscription = () => {
    setShowCancelDialog(false);
    // Handle subscription cancellation
    console.log("Cancelling subscription");
  };

  const getStatusColor = (status) => {
    switch (status) {
      case "paid":
        return "success";
      case "pending":
        return "warning";
      case "failed":
        return "error";
      default:
        return "default";
    }
  };

  const getStatusText = (status) => {
    switch (status) {
      case "paid":
        return "Đã thanh toán";
      case "pending":
        return "Chờ xử lý";
      case "failed":
        return "Thất bại";
      default:
        return status;
    }
  };

  return (
    <Box>
      {/* Header */}
      <Box
        sx={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          mb: 4,
        }}
      >
        <Box>
          <Typography variant="h4" gutterBottom>
            Thanh toán & Hóa đơn
          </Typography>
          <Typography variant="body1" color="text.secondary">
            Quản lý gói đăng ký và lịch sử thanh toán
          </Typography>
        </Box>
      </Box>

      {/* Current Plan */}
      <Grid container spacing={3} sx={{ mb: 4 }}>
        <Grid item xs={12} md={8}>
          <Card sx={{ p: 3, border: "2px solid", borderColor: "primary.main" }}>
            <Box
              sx={{
                display: "flex",
                justifyContent: "space-between",
                alignItems: "start",
                mb: 2,
              }}
            >
              <Box>
                <Typography variant="h5" gutterBottom>
                  {currentPlan.name}
                </Typography>
                <Typography variant="h6" color="primary" gutterBottom>
                  {currentPlan.price}₫/{currentPlan.cycle}
                </Typography>
                <Chip
                  label="Đang hoạt động"
                  color="success"
                  icon={<CheckCircle />}
                  sx={{ mb: 2 }}
                />
              </Box>
              <Button
                variant="outlined"
                color="error"
                startIcon={<Cancel />}
                onClick={() => setShowCancelDialog(true)}
              >
                Hủy đăng ký
              </Button>
            </Box>

            <Typography variant="body2" color="text.secondary" gutterBottom>
              Gia hạn tiếp theo: {currentPlan.nextBilling}
            </Typography>

            <List dense>
              {currentPlan.features.map((feature, index) => (
                <ListItem key={index} sx={{ px: 0 }}>
                  <ListItemIcon sx={{ minWidth: 32 }}>
                    <CheckCircle sx={{ color: "success.main", fontSize: 20 }} />
                  </ListItemIcon>
                  <ListItemText primary={feature} />
                </ListItem>
              ))}
            </List>

            <Box sx={{ mt: 3, display: "flex", alignItems: "center", gap: 2 }}>
              <Switch
                checked={autoRenewal}
                onChange={(e) => setAutoRenewal(e.target.checked)}
              />
              <Typography variant="body2">Tự động gia hạn</Typography>
            </Box>
          </Card>
        </Grid>

        <Grid item xs={12} md={4}>
          <Card sx={{ p: 3 }}>
            <Typography variant="h6" gutterBottom>
              Sử dụng trong tháng
            </Typography>
            <Box sx={{ mb: 2 }}>
              <Box
                sx={{ display: "flex", justifyContent: "space-between", mb: 1 }}
              >
                <Typography variant="body2">Bài đăng</Typography>
                <Typography variant="body2">47/∞</Typography>
              </Box>
              <Box
                sx={{ display: "flex", justifyContent: "space-between", mb: 1 }}
              >
                <Typography variant="body2">Tài khoản</Typography>
                <Typography variant="body2">6/10</Typography>
              </Box>
              <Box
                sx={{ display: "flex", justifyContent: "space-between", mb: 1 }}
              >
                <Typography variant="body2">AI tạo nội dung</Typography>
                <Typography variant="body2">23/100</Typography>
              </Box>
            </Box>
            <Button variant="outlined" fullWidth startIcon={<Analytics />}>
              Xem chi tiết
            </Button>
          </Card>
        </Grid>
      </Grid>

      {/* Available Plans */}
      <Card sx={{ mb: 4 }}>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            Các gói đăng ký khác
          </Typography>

          <Box sx={{ display: "flex", justifyContent: "center", mb: 3 }}>
            <FormControl>
              <RadioGroup
                row
                value={billingCycle}
                onChange={(e) => setBillingCycle(e.target.value)}
              >
                <FormControlLabel
                  value="monthly"
                  control={<Radio />}
                  label="Hàng tháng"
                />
                <FormControlLabel
                  value="yearly"
                  control={<Radio />}
                  label="Hàng năm (Tiết kiệm 17%)"
                />
              </RadioGroup>
            </FormControl>
          </Box>

          <Grid container spacing={3}>
            {plans.map((plan) => (
              <Grid item xs={12} md={4} key={plan.id}>
                <Card
                  sx={{
                    p: 3,
                    textAlign: "center",
                    border: plan.popular ? "2px solid" : "1px solid",
                    borderColor: plan.popular ? "primary.main" : "grey.300",
                    position: "relative",
                    height: "100%",
                  }}
                >
                  {plan.popular && (
                    <Chip
                      label="Phổ biến nhất"
                      color="primary"
                      sx={{
                        position: "absolute",
                        top: -12,
                        left: "50%",
                        transform: "translateX(-50%)",
                      }}
                    />
                  )}

                  <Box sx={{ mb: 2 }}>{plan.icon}</Box>

                  <Typography variant="h6" gutterBottom>
                    {plan.name}
                  </Typography>

                  <Typography variant="h4" color="primary" gutterBottom>
                    {billingCycle === "monthly"
                      ? plan.monthlyPrice
                      : plan.yearlyPrice}
                    ₫
                  </Typography>

                  <Typography
                    variant="body2"
                    color="text.secondary"
                    gutterBottom
                  >
                    /{billingCycle === "monthly" ? "tháng" : "năm"}
                  </Typography>

                  <List dense sx={{ mb: 3 }}>
                    {plan.features.map((feature, index) => (
                      <ListItem
                        key={index}
                        sx={{ px: 0, justifyContent: "center" }}
                      >
                        <ListItemIcon sx={{ minWidth: 24 }}>
                          <CheckCircle
                            sx={{ color: "success.main", fontSize: 16 }}
                          />
                        </ListItemIcon>
                        <ListItemText
                          primary={feature}
                          sx={{ textAlign: "left" }}
                          primaryTypographyProps={{ variant: "body2" }}
                        />
                      </ListItem>
                    ))}
                  </List>

                  <Button
                    variant={plan.id === "pro" ? "outlined" : "contained"}
                    fullWidth
                    disabled={plan.id === "pro"}
                    onClick={() => handleUpgrade(plan.id)}
                  >
                    {plan.id === "pro" ? "Gói hiện tại" : "Nâng cấp"}
                  </Button>
                </Card>
              </Grid>
            ))}
          </Grid>
        </CardContent>
      </Card>

      {/* Payment Methods & Billing History */}
      <Grid container spacing={3}>
        <Grid item xs={12} md={6}>
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
                <Typography variant="h6">Phương thức thanh toán</Typography>
                <Button variant="outlined" startIcon={<CreditCard />}>
                  Thêm thẻ
                </Button>
              </Box>

              {paymentMethods.map((method) => (
                <Card
                  key={method.id}
                  variant="outlined"
                  sx={{ p: 2, mb: 2, display: "flex", alignItems: "center" }}
                >
                  <Payment sx={{ mr: 2, color: "primary.main" }} />
                  <Box sx={{ flexGrow: 1 }}>
                    <Typography variant="body1">
                      •••• •••• •••• {method.last4}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                      Hết hạn: {method.expiryMonth}/{method.expiryYear}
                    </Typography>
                  </Box>
                  {method.isDefault && (
                    <Chip label="Mặc định" size="small" color="primary" />
                  )}
                </Card>
              ))}
            </CardContent>
          </Card>
        </Grid>

        <Grid item xs={12} md={6}>
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
                <Typography variant="h6">Lịch sử thanh toán</Typography>
                <Button variant="outlined" startIcon={<Download />}>
                  Xuất tất cả
                </Button>
              </Box>

              <TableContainer>
                <Table>
                  <TableHead>
                    <TableRow>
                      <TableCell>Mã hóa đơn</TableCell>
                      <TableCell>Ngày</TableCell>
                      <TableCell align="right">Số tiền</TableCell>
                      <TableCell>Trạng thái</TableCell>
                      <TableCell></TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {billingHistory.map((invoice) => (
                      <TableRow key={invoice.id}>
                        <TableCell>{invoice.id}</TableCell>
                        <TableCell>{invoice.date}</TableCell>
                        <TableCell align="right">{invoice.amount}₫</TableCell>
                        <TableCell>
                          <Chip
                            label={getStatusText(invoice.status)}
                            color={getStatusColor(invoice.status)}
                            size="small"
                          />
                        </TableCell>
                        <TableCell>
                          <Button size="small" startIcon={<Receipt />}>
                            Tải về
                          </Button>
                        </TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </TableContainer>
            </CardContent>
          </Card>
        </Grid>
      </Grid>

      {/* Payment Dialog */}
      <Dialog
        open={showPaymentDialog}
        onClose={() => setShowPaymentDialog(false)}
        maxWidth="sm"
        fullWidth
      >
        <DialogTitle>Nâng cấp gói đăng ký</DialogTitle>
        <DialogContent>
          <Typography variant="body1" gutterBottom>
            Bạn đang nâng cấp lên{" "}
            {plans.find((p) => p.id === selectedPlan)?.name}
          </Typography>
          <Alert severity="info" sx={{ my: 2 }}>
            <Box sx={{ display: "flex", alignItems: "center", gap: 1 }}>
              <Security fontSize="small" />
              <Typography variant="body2">
                Thông tin thanh toán được bảo mật với mã hóa SSL 256-bit
              </Typography>
            </Box>
          </Alert>
          <TextField
            fullWidth
            label="Số thẻ"
            placeholder="1234 5678 9012 3456"
            sx={{ mb: 2 }}
          />
          <Grid container spacing={2}>
            <Grid item xs={6}>
              <TextField fullWidth label="MM/YY" placeholder="12/25" />
            </Grid>
            <Grid item xs={6}>
              <TextField fullWidth label="CVV" placeholder="123" />
            </Grid>
          </Grid>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setShowPaymentDialog(false)}>Hủy</Button>
          <Button onClick={handlePayment} variant="contained">
            Thanh toán
          </Button>
        </DialogActions>
      </Dialog>

      {/* Cancel Subscription Dialog */}
      <Dialog
        open={showCancelDialog}
        onClose={() => setShowCancelDialog(false)}
      >
        <DialogTitle>Hủy đăng ký</DialogTitle>
        <DialogContent>
          <Typography variant="body1" gutterBottom>
            Bạn có chắc chắn muốn hủy đăng ký? Bạn sẽ mất quyền truy cập vào các
            tính năng cao cấp.
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Gói đăng ký sẽ tiếp tục hoạt động đến hết chu kỳ hiện tại (
            {currentPlan.nextBilling}).
          </Typography>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setShowCancelDialog(false)}>
            Giữ lại đăng ký
          </Button>
          <Button onClick={handleCancelSubscription} color="error">
            Xác nhận hủy
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default BillingPage;
