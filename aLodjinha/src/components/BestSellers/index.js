import React, { useState, useEffect } from 'react';
import { Text, View, ScrollView } from 'react-native';

import styles from './styles';
import Product from '../Product';
import api from '../../services/api';

export default function BestSellers({ navigation }) {
	const [ best, setBest ] = useState([]);
	useEffect(() => {
		(async function loadBestSellers() {
			const response = await api.get('/produto/maisvendidos');
			setBest(response.data.data);
		})();
	}, []);

	function funcPage(id) {
		navigation.navigate('DescriptionProduct', { id });
	}

	return (
		<View style={styles.container}>
			<View style={styles.bestHeader}>
				<Text style={styles.bestTitle}>Mais Vendidos</Text>
			</View>
			<ScrollView>{best.map((item) => <Product key={item.id} item={item} funcPage={funcPage} />)}</ScrollView>
		</View>
	);
}
