// src/hooks/useTopics.js
import { useState, useEffect } from "react";
import { topicAPI } from "../services/api";

export const useTopics = (campaignId) => {
  const [topics, setTopics] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const loadTopics = async () => {
    if (!campaignId) return;

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

  const generateTopics = async (numberOfTopics, additionalInstructions) => {
    setLoading(true);
    setError(null);

    try {
      const requestData = {
        campaignId,
        numberOfTopics,
        additionalInstructions,
      };

      const response = await topicAPI.generateTopics(requestData);
      setTopics((prevTopics) => [...response.data, ...prevTopics]);
      return response.data;
    } catch (err) {
      setError(`Lỗi khi tạo chủ đề cho user 1Ir-is: ${err.message}`);
      throw err;
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
      return response.data;
    } catch (err) {
      setError(`Lỗi khi duyệt chủ đề: ${err.message}`);
      throw err;
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
      return response.data;
    } catch (err) {
      setError(`Lỗi khi từ chối chủ đề: ${err.message}`);
      throw err;
    }
  };

  const deleteTopic = async (topicId) => {
    try {
      await topicAPI.deleteTopic(topicId);
      setTopics((prevTopics) =>
        prevTopics.filter((topic) => topic.id !== topicId)
      );
      return true;
    } catch (err) {
      setError(`Lỗi khi xóa chủ đề: ${err.message}`);
      throw err;
    }
  };

  useEffect(() => {
    loadTopics();
  }, [campaignId]);

  return {
    topics,
    loading,
    error,
    loadTopics,
    generateTopics,
    approveTopic,
    rejectTopic,
    deleteTopic,
  };
};
