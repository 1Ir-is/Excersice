export function filteredProducts(products, search) {
  if (!search) {
    return products;
  }
  return products.filter(
    (p) =>
      p.name.toLowerCase().includes(search.toLowerCase()) ||
      p.description.toLowerCase().includes(search.toLowerCase())
  );
}
