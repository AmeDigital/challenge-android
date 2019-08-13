import React from 'react';
import { Text, View, Button } from 'react-native';

import LogoTitle from '../../components/LogoTitle';

function HomeScreen({ navigation }) {
	return (
		<View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
			<Text>Home</Text>
			<Button title="Vá a Sobre" onPress={() => navigation.navigate('About')} />
		</View>
	);
}

HomeScreen.navigationOptions = {
	headerTitle: <LogoTitle />
};

export default HomeScreen;
