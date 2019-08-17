import React, { useState, useEffect } from 'react';
import { Text, View, ScrollView, Image, TouchableOpacity } from 'react-native';

import api from '../../services/api';

import styles from './styles';
import Loading from '../Loading';
import Category from '../Category';

export default function Categories({ navigation }) {
	const [ categories, setCategories ] = useState([]);

	useEffect(() => {
		(async function loadCategoria() {
			const response = await api.get('/categoria');
			setCategories(response.data.data);
		})();
	}, []);

	function goPageCategories(id, descricao) {
		navigation.navigate('Categories', { id, descricao });
	}

	return (
		<View style={styles.container}>
			<View style={styles.categoryHeader}>
				<Text style={styles.categoryTitle}>Categorias</Text>
			</View>

			{categories.length > 0 ? (
				<ScrollView showsHorizontalScrollIndicator={false} horizontal={true} style={styles.categoryContent}>
					{categories.map((item, index) => (
						<TouchableOpacity key={index} onPress={() => goPageCategories(item.id, item.descricao)}>
							<Category item={item} />
						</TouchableOpacity>
					))}
				</ScrollView>
			) : (
				<Loading />
			)}
		</View>
	);
}
