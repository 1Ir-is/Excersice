import React from "react";

function ConfirmModal({ show, onConfirm, onCancel, message }) {
  if (!show) return null;
  return (
    <div
      style={{
        position: "fixed",
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        background: "rgba(0,0,0,0.3)",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <div
        style={{
          background: "#fff",
          padding: 24,
          borderRadius: 8,
          minWidth: 300,
        }}
      >
        <p>{message || "Bạn có chắc chắn muốn xóa?"}</p>
        <button onClick={onConfirm}>Xác nhận</button>
        <button onClick={onCancel} style={{ marginLeft: 8 }}>
          Huỷ
        </button>
      </div>
    </div>
  );
}

export default ConfirmModal;
