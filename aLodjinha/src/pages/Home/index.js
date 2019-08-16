import React from 'react';
import { View } from 'react-native';

import styles from './styles';

import LogoTitle from '../../components/LogoTitle';
import Banners from '../../components/Banners';
import Categories from '../../components/Categories';
import BestSellers from '../../components/BestSellers';

function HomeScreen({ navigation }) {
	return (
		<View style={styles.container}>
			<Banners />
			<Categories navigation={navigation} />
			<BestSellers navigation={navigation} />
		</View>
	);
}

HomeScreen.navigationOptions = ({ navigation }) => {
	return {
		headerTitle: <LogoTitle navigation={navigation} />
	};
};

export default HomeScreen;
