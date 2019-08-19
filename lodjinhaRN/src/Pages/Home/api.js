export const getCategories = async () => {
  const response = await fetch('https://alodjinha.herokuapp.com/categoria');
  const categories = await response.json();
  return categories
}

export const getBestSells = async () => {
  const response = await fetch('https://alodjinha.herokuapp.com/produto/maisvendidos');
  const bestSells = await response.json();
  return bestSells
}

export const getBanners = async () => {
  const response = await fetch('https://alodjinha.herokuapp.com/banner');
  const banners = await response.json();
  return banners
}