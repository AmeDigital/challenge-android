import { StyleSheet } from 'react-native';

import material from '../../utils/MaterialDesign';

export default StyleSheet.create({
	container: {
		flex: 1,
		justifyContent: 'center',
		alignItems: 'center'
	},
	image: {
		resizeMode: 'contain',
		width: 150
	},
	description: {
		fontSize: 18,
		fontWeight: 'bold',
		color: material.colorDark
	}
});
