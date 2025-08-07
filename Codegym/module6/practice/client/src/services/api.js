// src/services/api.js
import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
  timeout: 30000, // 30 seconds timeout
});

// Request interceptor
api.interceptors.request.use(
  (config) => {
    const timestamp = new Date().toISOString();
    console.log(
      `[${timestamp}] Making ${config.method?.toUpperCase()} request to: ${
        config.url
      }`
    );
    console.log(`[${timestamp}] Request data:`, config.data);
    return config;
  },
  (error) => {
    console.error("Request interceptor error:", error);
    return Promise.reject(error);
  }
);

// Response interceptor
api.interceptors.response.use(
  (response) => {
    const timestamp = new Date().toISOString();
    console.log(
      `[${timestamp}] Response received from: ${response.config.url}`
    );
    console.log(`[${timestamp}] Response status: ${response.status}`);
    console.log(`[${timestamp}] Response data:`, response.data);
    return response;
  },
  (error) => {
    const timestamp = new Date().toISOString();
    console.error(`[${timestamp}] API Error:`, {
      url: error.config?.url,
      method: error.config?.method,
      status: error.response?.status,
      statusText: error.response?.statusText,
      message: error.message,
      data: error.response?.data,
    });

    // Custom error handling for user 1Ir-is
    if (error.response?.status === 500) {
      error.message = "Server error - vui lòng thử lại sau";
    } else if (error.response?.status === 404) {
      error.message = "Không tìm thấy tài nguyên yêu cầu";
    } else if (error.response?.status === 400) {
      error.message = "Dữ liệu không hợp lệ";
    }

    return Promise.reject(error);
  }
);

// Topic APIs with enhanced error handling
export const topicAPI = {
  generateTopics: async (data) => {
    try {
      console.log(
        `🚀 [User 1Ir-is] Generating ${data.numberOfTopics} topics for campaign ${data.campaignId}`
      );
      const response = await api.post("/topics/generate", data);
      console.log(
        `✅ [User 1Ir-is] Successfully generated ${response.data.length} topics`
      );
      return response;
    } catch (error) {
      console.error(
        `❌ [User 1Ir-is] Failed to generate topics:`,
        error.message
      );
      throw error;
    }
  },

  getTopicsByCampaign: async (campaignId) => {
    try {
      console.log(`🔍 [User 1Ir-is] Loading topics for campaign ${campaignId}`);
      const response = await api.get(`/topics/campaign/${campaignId}`);
      console.log(`✅ [User 1Ir-is] Loaded ${response.data.length} topics`);
      return response;
    } catch (error) {
      console.error(`❌ [User 1Ir-is] Failed to load topics:`, error.message);
      throw error;
    }
  },

  getTopicById: async (topicId) => {
    try {
      console.log(`🔍 [User 1Ir-is] Getting topic ${topicId}`);
      const response = await api.get(`/topics/${topicId}`);
      return response;
    } catch (error) {
      console.error(
        `❌ [User 1Ir-is] Failed to get topic ${topicId}:`,
        error.message
      );
      throw error;
    }
  },

  approveTopic: async (topicId) => {
    try {
      console.log(`👍 [User 1Ir-is] Approving topic ${topicId}`);
      const response = await api.put(`/topics/${topicId}/approve`);
      console.log(`✅ [User 1Ir-is] Topic ${topicId} approved successfully`);
      return response;
    } catch (error) {
      console.error(
        `❌ [User 1Ir-is] Failed to approve topic ${topicId}:`,
        error.message
      );
      throw error;
    }
  },

  rejectTopic: async (topicId) => {
    try {
      console.log(`👎 [User 1Ir-is] Rejecting topic ${topicId}`);
      const response = await api.put(`/topics/${topicId}/reject`);
      console.log(`✅ [User 1Ir-is] Topic ${topicId} rejected successfully`);
      return response;
    } catch (error) {
      console.error(
        `❌ [User 1Ir-is] Failed to reject topic ${topicId}:`,
        error.message
      );
      throw error;
    }
  },

  deleteTopic: async (topicId) => {
    try {
      console.log(`🗑️ [User 1Ir-is] Deleting topic ${topicId}`);
      const response = await api.delete(`/topics/${topicId}`);
      console.log(`✅ [User 1Ir-is] Topic ${topicId} deleted successfully`);
      return response;
    } catch (error) {
      console.error(
        `❌ [User 1Ir-is] Failed to delete topic ${topicId}:`,
        error.message
      );
      throw error;
    }
  },

  // Health check endpoint
  healthCheck: async () => {
    try {
      console.log(`🏥 [User 1Ir-is] Checking API health`);
      const response = await api.get("/topics/health");
      return response;
    } catch (error) {
      console.error(`❌ [User 1Ir-is] Health check failed:`, error.message);
      throw error;
    }
  },

  // Test endpoint
  testConnection: async () => {
    try {
      console.log(`🧪 [User 1Ir-is] Testing API connection`);
      const response = await api.get("/topics/test");
      return response;
    } catch (error) {
      console.error(`❌ [User 1Ir-is] Connection test failed:`, error.message);
      throw error;
    }
  },
};

// Post APIs (ready for future use)
export const postAPI = {
  generateContent: (data) => api.post("/posts/generate", data),
  schedulePost: (data) => api.post("/posts/schedule", data),
  updatePostContent: (postId, content) =>
    api.put(`/posts/${postId}/content`, content),
  getPostsByTopic: (topicId) => api.get(`/posts/topic/${topicId}`),
  getPostById: (postId) => api.get(`/posts/${postId}`),
  getScheduledPosts: () => api.get("/posts/scheduled"),
  deletePost: (postId) => api.delete(`/posts/${postId}`),
};

// Campaign APIs (for future expansion)
export const campaignAPI = {
  getAllCampaigns: () => api.get("/campaigns"),
  getCampaignById: (campaignId) => api.get(`/campaigns/${campaignId}`),
  createCampaign: (data) => api.post("/campaigns", data),
  updateCampaign: (campaignId, data) =>
    api.put(`/campaigns/${campaignId}`, data),
  deleteCampaign: (campaignId) => api.delete(`/campaigns/${campaignId}`),
};

export default api;
