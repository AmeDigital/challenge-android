import React from 'react';
import { Text, View, ScrollView } from 'react-native';

import styles from './styles';
import Product from '../Product';

export default function BestSellers({ navigation }) {
	return (
		<View style={styles.container}>
			<View style={styles.bestHeader}>
				<Text style={styles.bestTitle}>Mais Vendidos</Text>
			</View>
			<ScrollView>
				<Product navigation={navigation} />
				<Product navigation={navigation} />
				<Product navigation={navigation} />
				<Product navigation={navigation} />
				<Product navigation={navigation} />
			</ScrollView>
		</View>
	);
}
