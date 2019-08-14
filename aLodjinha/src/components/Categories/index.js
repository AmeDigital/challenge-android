import React, { useState, useEffect } from 'react';
import { Text, View, ScrollView, Image } from 'react-native';

import api from '../../services/api';

import styles from './styles';

export default function Categories() {
	const [ categories, setCategories ] = useState([]);

	useEffect(() => {
		(async function loadCategoria() {
			const response = await api.get('/categoria');
			setCategories(response.data.data);
		})();
	}, []);

	return (
		<View style={styles.container}>
			<View style={styles.categoryHeader}>
				<Text style={styles.categoryTitle}>Categorias</Text>
			</View>
			<ScrollView
				showsHorizontalScrollIndicator={false}
				horizontal={true}
				contentContainerStyle={{ padding: 8 }}
				style={styles.categoryContent}
			>
				{categories.map((item, index) => (
					<View key={index} style={styles.category}>
						<Image style={styles.image} source={{ uri: item.urlImagem }} />
						<Text style={styles.description} numberOfLines={1}>
							{item.descricao}
						</Text>
					</View>
				))}
			</ScrollView>
		</View>
	);
}
