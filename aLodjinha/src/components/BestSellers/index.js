import React from 'react';
import { Text, View, ScrollView } from 'react-native';

import styles from './styles';
import Product from '../Product';

export default function BestSellers() {
	return (
		<View style={styles.container}>
			<View style={styles.bestHeader}>
				<Text style={styles.bestTitle}>Mais Vendidos</Text>
			</View>
			<ScrollView>
				<Product />
				<Product />
				<Product />
				<Product />
				<Product />
			</ScrollView>
		</View>
	);
}
