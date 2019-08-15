import React from 'react';
import { TouchableOpacity, Image } from 'react-native';

import styles from './styles';

export default function FloatingButton({ onPress }) {
	return (
		<TouchableOpacity activeOpacity={0.7} onPress={() => onPress()} style={styles.TouchableOpacityStyle}>
			<Image source={require('../../images/check_button.png')} style={styles.FloatingButtonStyle} />
		</TouchableOpacity>
	);
}
