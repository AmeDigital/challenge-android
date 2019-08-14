import React from 'react';
import { Text, View, Button } from 'react-native';

import styles from './styles';

import LogoTitle from '../../components/LogoTitle';
import Banners from '../../components/Banners';
import Categories from '../../components/Categories';

function HomeScreen({ navigation }) {
	return (
		<View style={styles.container}>
			<Banners />
			<Categories />
		</View>
	);
}

HomeScreen.navigationOptions = {
	headerTitle: <LogoTitle />
};

export default HomeScreen;
