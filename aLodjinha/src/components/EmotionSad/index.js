import React from 'react';
import { View, Image, Text } from 'react-native';

import styles from './styles';

export default function EmotionSad() {
	return (
		<View style={styles.container}>
			<Image source={require('../../images/triste.png')} style={styles.image} />
			<Text style={styles.description}>Produtos indisponíveis no momento</Text>
		</View>
	);
}
