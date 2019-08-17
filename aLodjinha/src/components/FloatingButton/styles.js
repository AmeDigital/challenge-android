import { StyleSheet } from 'react-native';

import material from '../../utils/MaterialDesign';

export default StyleSheet.create({
	TouchableOpacityStyle: {
		position: 'absolute',
		width: 50,
		height: 50,
		alignItems: 'center',
		justifyContent: 'center',
		right: 18,
		bottom: 18,
		backgroundColor: material.colorWarmPurple,
		padding: 30,
		borderRadius: 50,
		elevation: 4
	},

	FloatingButtonStyle: {
		resizeMode: 'contain'
	}
});
