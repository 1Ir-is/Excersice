import { useNavigate, useParams } from "react-router-dom";
import { getById } from "../services/productService";
import { useEffect, useState } from "react";

function ProductDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);

  useEffect(() => {
    async function fetchProduct() {
      const data = await getById(Number(id));
      setProduct(data);
    }
    fetchProduct();
  }, [id]);

  if (!product) {
    return <div className="alert alert-danger">Không tìm thấy sản phẩm!</div>;
  }

  return (
    <div className="card mt-4">
      <div className="card-header">Chi tiết sản phẩm</div>
      <div className="card-body">
        <p>
          <strong>Tên sản phẩm: </strong> {product.name}
        </p>
        <p>
          <strong>Giá: </strong> {product.price}
        </p>
        <p>
          <strong>Danh mục: </strong> {product.category}
        </p>
        <p>
          <strong>Số lượng: </strong> {product.quantity}
        </p>
        <p>
          <strong>Mô tả: </strong> {product.description}
        </p>
        <button className="btn btn-primary" onClick={() => navigate(-1)}>
          Quay lại
        </button>
      </div>
    </div>
  );
}

export default ProductDetail;
