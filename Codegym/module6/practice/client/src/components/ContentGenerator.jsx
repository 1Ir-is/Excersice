// import React, { useState } from "react";
// import {
//   Box,
//   Card,
//   CardContent,
//   Typography,
//   TextField,
//   Button,
//   Alert,
//   CircularProgress,
//   Grid,
//   Chip,
//   FormControl,
//   InputLabel,
//   Select,
//   MenuItem,
//   FormControlLabel,
//   Switch,
//   Dialog,
//   DialogTitle,
//   DialogContent,
//   DialogActions,
// } from "@mui/material";
// import {
//   Create as CreateIcon,
//   Edit as EditIcon,
//   Image as ImageIcon,
// } from "@mui/icons-material";
// import { postAPI } from "../services/api";

// const ContentGenerator = () => {
//   const [formData, setFormData] = useState({
//     topicId: 1, // Default for testing
//     numberOfPosts: 3,
//     tone: "professional",
//     contentType: "social_post",
//     includeImage: true,
//     additionalInstructions: "",
//   });

//   const [posts, setPosts] = useState([]);
//   const [loading, setLoading] = useState(false);
//   const [error, setError] = useState("");
//   const [success, setSuccess] = useState("");
//   const [editDialog, setEditDialog] = useState({
//     open: false,
//     post: null,
//     content: "",
//   });

//   const toneOptions = [
//     "professional",
//     "casual",
//     "friendly",
//     "formal",
//     "enthusiastic",
//   ];
//   const contentTypeOptions = [
//     "social_post",
//     "article",
//     "announcement",
//     "promotion",
//   ];

//   const handleInputChange = (e) => {
//     const { name, value, type, checked } = e.target;
//     setFormData((prev) => ({
//       ...prev,
//       [name]:
//         type === "checkbox"
//           ? checked
//           : name === "numberOfPosts" || name === "topicId"
//           ? parseInt(value)
//           : value,
//     }));
//   };

//   const handleGenerateContent = async () => {
//     try {
//       setLoading(true);
//       setError("");
//       setSuccess("");

//       console.log("Generating content with data:", formData);

//       const response = await postAPI.generateContent(formData);
//       setPosts(response.data);
//       setSuccess(`Successfully generated ${response.data.length} posts!`);
//     } catch (err) {
//       console.error("Error generating content:", err);
//       setError(err.response?.data?.error || "Failed to generate content");
//     } finally {
//       setLoading(false);
//     }
//   };

//   const handleEditContent = (post) => {
//     setEditDialog({
//       open: true,
//       post: post,
//       content: post.contentText,
//     });
//   };

//   const handleSaveEdit = async () => {
//     try {
//       await postAPI.updatePostContent(editDialog.post.id, editDialog.content);
//       setPosts((prev) =>
//         prev.map((post) =>
//           post.id === editDialog.post.id
//             ? { ...post, contentText: editDialog.content, aiGenerated: false }
//             : post
//         )
//       );
//       setEditDialog({ open: false, post: null, content: "" });
//       setSuccess("Post content updated successfully!");
//     } catch (err) {
//       setError("Failed to update post content");
//     }
//   };

//   const getStatusColor = (status) => {
//     switch (status) {
//       case "PUBLISHED":
//         return "success";
//       case "SCHEDULED":
//         return "warning";
//       case "FAILED":
//         return "error";
//       case "DRAFT":
//         return "default";
//       default:
//         return "default";
//     }
//   };

//   return (
//     <Box sx={{ p: 3 }}>
//       <Typography variant="h4" gutterBottom>
//         Content Generator (Task 10)
//       </Typography>

//       {/* Form */}
//       <Card sx={{ mb: 3 }}>
//         <CardContent>
//           <Typography variant="h6" gutterBottom>
//             Generate Post Content with AI
//           </Typography>

//           <Grid container spacing={2}>
//             <Grid item xs={12} sm={6}>
//               <TextField
//                 fullWidth
//                 label="Topic ID"
//                 name="topicId"
//                 type="number"
//                 value={formData.topicId}
//                 onChange={handleInputChange}
//                 variant="outlined"
//               />
//             </Grid>

//             <Grid item xs={12} sm={6}>
//               <TextField
//                 fullWidth
//                 label="Number of Posts"
//                 name="numberOfPosts"
//                 type="number"
//                 value={formData.numberOfPosts}
//                 onChange={handleInputChange}
//                 inputProps={{ min: 1, max: 10 }}
//                 variant="outlined"
//               />
//             </Grid>

//             <Grid item xs={12} sm={6}>
//               <FormControl fullWidth>
//                 <InputLabel>Tone</InputLabel>
//                 <Select
//                   name="tone"
//                   value={formData.tone}
//                   onChange={handleInputChange}
//                   label="Tone"
//                 >
//                   {toneOptions.map((tone) => (
//                     <MenuItem key={tone} value={tone}>
//                       {tone.charAt(0).toUpperCase() + tone.slice(1)}
//                     </MenuItem>
//                   ))}
//                 </Select>
//               </FormControl>
//             </Grid>

//             <Grid item xs={12} sm={6}>
//               <FormControl fullWidth>
//                 <InputLabel>Content Type</InputLabel>
//                 <Select
//                   name="contentType"
//                   value={formData.contentType}
//                   onChange={handleInputChange}
//                   label="Content Type"
//                 >
//                   {contentTypeOptions.map((type) => (
//                     <MenuItem key={type} value={type}>
//                       {type.replace("_", " ").charAt(0).toUpperCase() +
//                         type.replace("_", " ").slice(1)}
//                     </MenuItem>
//                   ))}
//                 </Select>
//               </FormControl>
//             </Grid>

//             <Grid item xs={12}>
//               <FormControlLabel
//                 control={
//                   <Switch
//                     name="includeImage"
//                     checked={formData.includeImage}
//                     onChange={handleInputChange}
//                   />
//                 }
//                 label="Include AI-generated image"
//               />
//             </Grid>

//             <Grid item xs={12}>
//               <TextField
//                 fullWidth
//                 label="Additional Instructions"
//                 name="additionalInstructions"
//                 value={formData.additionalInstructions}
//                 onChange={handleInputChange}
//                 multiline
//                 rows={3}
//                 variant="outlined"
//                 placeholder="e.g., Make it more engaging, include call-to-action..."
//               />
//             </Grid>
//           </Grid>

//           <Box sx={{ mt: 2 }}>
//             <Button
//               variant="contained"
//               onClick={handleGenerateContent}
//               disabled={loading}
//               startIcon={
//                 loading ? <CircularProgress size={20} /> : <CreateIcon />
//               }
//               size="large"
//             >
//               {loading ? "Generating Content..." : "Generate Content with AI"}
//             </Button>
//           </Box>
//         </CardContent>
//       </Card>

//       {/* Alerts */}
//       {error && (
//         <Alert severity="error" sx={{ mb: 2 }} onClose={() => setError("")}>
//           {error}
//         </Alert>
//       )}

//       {success && (
//         <Alert severity="success" sx={{ mb: 2 }} onClose={() => setSuccess("")}>
//           {success}
//         </Alert>
//       )}

//       {/* Posts List */}
//       {posts.length > 0 && (
//         <Box>
//           <Typography variant="h6" gutterBottom>
//             Generated Posts ({posts.length})
//           </Typography>

//           <Grid container spacing={2}>
//             {posts.map((post) => (
//               <Grid item xs={12} md={6} key={post.id}>
//                 <Card variant="outlined" sx={{ height: "100%" }}>
//                   <CardContent>
//                     <Box
//                       sx={{
//                         display: "flex",
//                         justifyContent: "space-between",
//                         alignItems: "flex-start",
//                         mb: 2,
//                       }}
//                     >
//                       <Typography variant="h6" component="div">
//                         Post #{post.id}
//                       </Typography>
//                       <Box sx={{ display: "flex", gap: 1 }}>
//                         <Chip
//                           label={post.status}
//                           color={getStatusColor(post.status)}
//                           size="small"
//                         />
//                         {post.aiGenerated && (
//                           <Chip
//                             label="AI Generated"
//                             color="primary"
//                             size="small"
//                           />
//                         )}
//                       </Box>
//                     </Box>

//                     <Typography
//                       variant="body2"
//                       sx={{ mb: 2, whiteSpace: "pre-wrap" }}
//                     >
//                       {post.contentText.length > 200
//                         ? post.contentText.substring(0, 200) + "..."
//                         : post.contentText}
//                     </Typography>

//                     {post.contentImageUrl && (
//                       <Box
//                         sx={{
//                           display: "flex",
//                           alignItems: "center",
//                           mb: 2,
//                           color: "text.secondary",
//                         }}
//                       >
//                         <ImageIcon sx={{ mr: 1 }} />
//                         <Typography variant="caption">
//                           Image included
//                         </Typography>
//                       </Box>
//                     )}

//                     <Box sx={{ display: "flex", gap: 1, mt: "auto" }}>
//                       <Button
//                         size="small"
//                         variant="outlined"
//                         startIcon={<EditIcon />}
//                         onClick={() => handleEditContent(post)}
//                       >
//                         Edit Content
//                       </Button>
//                     </Box>

//                     <Typography
//                       variant="caption"
//                       color="text.secondary"
//                       sx={{ mt: 1, display: "block" }}
//                     >
//                       Created: {new Date(post.createdAt).toLocaleString()}
//                     </Typography>
//                   </CardContent>
//                 </Card>
//               </Grid>
//             ))}
//           </Grid>
//         </Box>
//       )}

//       {/* Edit Dialog */}
//       <Dialog
//         open={editDialog.open}
//         onClose={() => setEditDialog({ open: false, post: null, content: "" })}
//         maxWidth="md"
//         fullWidth
//       >
//         <DialogTitle>Edit Post Content</DialogTitle>
//         <DialogContent>
//           <TextField
//             fullWidth
//             multiline
//             rows={10}
//             value={editDialog.content}
//             onChange={(e) =>
//               setEditDialog((prev) => ({ ...prev, content: e.target.value }))
//             }
//             variant="outlined"
//             sx={{ mt: 1 }}
//           />
//         </DialogContent>
//         <DialogActions>
//           <Button
//             onClick={() =>
//               setEditDialog({ open: false, post: null, content: "" })
//             }
//           >
//             Cancel
//           </Button>
//           <Button onClick={handleSaveEdit} variant="contained">
//             Save Changes
//           </Button>
//         </DialogActions>
//       </Dialog>
//     </Box>
//   );
// };

// export default ContentGenerator;

// src/components/ContentGenerator.jsx
import React from "react";
import { Box, Typography, Card, CardContent } from "@mui/material";

const ContentGenerator = () => {
  return (
    <Box sx={{ py: 3 }}>
      <Typography variant="h4" component="h1" gutterBottom>
        Tạo Nội Dung Marketing
      </Typography>

      <Card>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            Tính năng đang phát triển
          </Typography>
          <Typography color="text.secondary">
            Tính năng tạo nội dung marketing sẽ được triển khai sớm cho user
            1Ir-is.
          </Typography>
        </CardContent>
      </Card>
    </Box>
  );
};

export default ContentGenerator;
