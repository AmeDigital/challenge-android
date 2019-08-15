import React, { useState, useEffect } from 'react';
import { Text, View, ScrollView, Image, TouchableOpacity } from 'react-native';

import api from '../../services/api';

import styles from './styles';

export default function Categories({ navigation }) {
	const [ categories, setCategories ] = useState([]);

	useEffect(() => {
		(async function loadCategoria() {
			const response = await api.get('/categoria');
			setCategories(response.data.data);
		})();
	}, []);

	function goPageCategories(descricao) {
		navigation.navigate('Categories', { descricao });
	}

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
				{categories.length > 0 &&
					categories.map((item, index) => (
						<TouchableOpacity key={index} onPress={() => goPageCategories(item.descricao)}>
							<View style={styles.category}>
								<Image style={styles.image} source={{ uri: item.urlImagem }} />
								<Text style={styles.description} numberOfLines={1}>
									{item.descricao}
								</Text>
							</View>
						</TouchableOpacity>
					))}
			</ScrollView>
		</View>
	);
}
