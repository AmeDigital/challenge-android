import React from 'react';
import { Text, View, Button } from 'react-native';

import styles from './styles';

import LogoTitle from '../../components/LogoTitle';
import Banners from '../../components/Banners';

function HomeScreen({ navigation }) {
	return (
		<View style={styles.container}>
			<Banners />
			<Button title="Vá a Sobre" onPress={() => navigation.navigate('About')} />
		</View>
	);
}

HomeScreen.navigationOptions = {
	headerTitle: <LogoTitle />
};

export default HomeScreen;
