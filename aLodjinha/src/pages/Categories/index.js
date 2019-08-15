import React, { useEffect } from 'react';
import { ScrollView, View } from 'react-native';

import styles from './styles';
import Product from '../../components/Product';

function CategoriesScreen({ navigation }) {
	const id = navigation.getParam('id');

	return (
		<View style={styles.container}>
			<ScrollView>{/* <Product funcPage={} item={item}/> */}</ScrollView>
		</View>
	);
}

CategoriesScreen.navigationOptions = {
	title: 'placas de video'
};

export default CategoriesScreen;
