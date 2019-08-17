import React from 'react';
import { Text, View, Image } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

import styles from './styles';

function AboutScreen() {
	return (
		<View style={styles.container}>
			<View style={styles.header}>
				<Image style={styles.logo} source={require('../../images/logo_sobre.png')} />
				<Text style={styles.logoText}>a Lodjinha</Text>
			</View>
			<View style={styles.footer}>
				<Text style={styles.nameDev}>Washington Martins da Silva</Text>
				<Text style={styles.dataDev}>16 de Agosto 2019</Text>
			</View>
		</View>
	);
}

AboutScreen.navigationOptions = ({ navigation }) => {
	return {
		headerTitle: (
			<React.Fragment>
				<Icon name="menu" style={{ marginLeft: 15 }} onPress={navigation.openDrawer} size={24} color="#fff" />
				<Text style={{ marginLeft: 15, fontSize: 20, color: '#fff', fontWeight: 'bold' }}>Sobre</Text>
			</React.Fragment>
		),
		headerLeft: null
	};
};

export default AboutScreen;
