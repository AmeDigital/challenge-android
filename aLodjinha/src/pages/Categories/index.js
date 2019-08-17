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

	useEffect(() => {
		if (navigation.getParam('descricao') !== 'Games') {
			setShowEmotion(true);
		} else {
			loadProducts();
		}
	}, []);

	async function loadProducts() {
		if (load) return;

		if (listFinished()) return;

		setLoad(true);

		try {
			const response = await api.get(`/produto?offset=${offset}`);

			setProducts([ ...products, ...response.data.data ]);
			setOffset(offset + 1);
			setLoad(false);
		} catch (e) {
			console.error(e);
		}
	}

	function funcPage(id, descricao) {
		navigation.navigate('DescriptionProduct', { id, descricao });
	}

	function listFinished() {
		return products.length > 75;
	}

	function listCategories() {
		return (
			<View style={styles.container}>
				{products.length > 0 ? (
					<FlatList
						data={products}
						renderItem={({ item }) => <Product key={item.id} funcPage={funcPage} item={item} />}
						keyExtractor={(item) => String(item.id + Math.floor(Math.random() * 198541))}
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
