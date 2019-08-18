import React from 'react';
import { TouchableOpacity, Image, ActivityIndicator } from 'react-native';

import styles from './styles';

export default function FloatingButton({ onPress, loading }) {
	return (
		<TouchableOpacity
			disabled={loading}
			activeOpacity={0.7}
			onPress={() => onPress()}
			style={styles.TouchableOpacityStyle}
		>
			{loading ? (
				<ActivityIndicator size="small" color="#fff" style={styles.FloatingButtonStyle} />
			) : (
				<Image source={require('../../images/check_button.png')} style={styles.FloatingButtonStyle} />
			)}
		</TouchableOpacity>
	);
}
