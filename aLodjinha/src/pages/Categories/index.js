import React, { useState, useEffect } from 'react';
import { FlatList, View, ActivityIndicator } from 'react-native';

import styles from './styles';
import Product from '../../components/Product';
import api from '../../services/api';
import Loading from '../../components/Loading';
import EmotionSad from '../../components/EmotionSad';

function CategoriesScreen({ navigation }) {
	const [ products, setProducts ] = useState([]);
	const [ offset, setOffset ] = useState(0);
	const [ load, setLoad ] = useState(false);
	const [ showEmotion, setShowEmotion ] = useState(false);
	const [ isTotal, setIsTotal ] = useState(false);

	const id = navigation.getParam('id');

	useEffect(() => {
		loadProducts();
	}, []);

	async function loadProducts() {
		if (load) return;

		if (isTotal) return;

		setLoad(true);

		try {
			const response = await api.get(`/produto?limit=20&offset=${offset}&categoriaId=${id}`);

			setProducts([ ...products, ...response.data.data ]);

			if (response.data.data.length === 0) {
				setShowEmotion(true);
				return;
			}

			/**Logica para limitar o tamanho da lista */
			if (response.data.data.length < 20) {
				setIsTotal(true);
			}

			setOffset(offset + 2);
			setLoad(false);
		} catch (e) {
			console.error(e);
		}
	}

	function funcPage(id, descricao) {
		navigation.navigate('DescriptionProduct', { id, descricao });
	}

	function listCategories() {
		return (
			<View style={styles.container}>
				{products.length > 0 ? (
					<FlatList
						data={products}
						renderItem={({ item }) => <Product key={item.id} funcPage={funcPage} item={item} />}
						keyExtractor={(item) => String(item.id + Math.floor(Math.random() * 1985419652))}
						onEndReached={loadProducts}
						onEndReachedThreshold={0.1}
						ListFooterComponent={
							load && <ActivityIndicator size="large" color="#5e2a84" style={{ margin: 20 }} />
						}
					/>
				) : (
					<Loading />
				)}
			</View>
		);
	}

	return showEmotion ? <EmotionSad /> : listCategories();
}

CategoriesScreen.navigationOptions = ({ navigation }) => {
	return {
		title: navigation.getParam('descricao')
	};
};

export default CategoriesScreen;
