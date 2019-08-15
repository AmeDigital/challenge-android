import React from 'react';
import { Text, View, Image } from 'react-native';

import styles from './styles';

function AboutScreen() {
	return (
		<View style={styles.container}>
			<View style={styles.header}>
				<Image style={styles.logo} source={require('../../images/logo_sobre.png')} />
				<Text style={styles.logoText}>a Lodjinha</Text>
			</View>
			<View style={styles.footer}>
				<Text style={styles.nameDev}>Washington Developer</Text>
				<Text style={styles.dataDev}>16 de Agosto 2019</Text>
			</View>
		</View>
	);
}

export default AboutScreen;
