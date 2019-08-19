import React from 'react';
import { View, ScrollView } from 'react-native';

import styles from './styles';

import LogoTitle from '../../components/LogoTitle';
import Banners from '../../components/Banners';
import Categories from '../../components/Categories';
import BestSellers from '../../components/BestSellers';

function HomeScreen({ navigation }) {
	return (
		<ScrollView>
			<View style={styles.container}>
				<Banners />
				<Categories navigation={navigation} />
				<BestSellers navigation={navigation} />
			</View>
		</ScrollView>
	);
}

HomeScreen.navigationOptions = ({ navigation }) => {
	return {
		headerTitle: <LogoTitle navigation={navigation} />
	};
};

export default HomeScreen;
