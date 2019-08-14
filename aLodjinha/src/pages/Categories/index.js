import React from 'react';
import { ScrollView, View } from 'react-native';

import styles from './styles';
import Product from '../../components/Product';

function CategoriesScreen() {
	return (
		<View style={styles.container}>
			<ScrollView>
				<Product />
				<Product />
				<Product />
				<Product />
				<Product />
				<Product />
				<Product />
				<Product />
				<Product />
			</ScrollView>
		</View>
	);
}

CategoriesScreen.navigationOptions = {
	title: 'placas de video'
};

export default CategoriesScreen;
