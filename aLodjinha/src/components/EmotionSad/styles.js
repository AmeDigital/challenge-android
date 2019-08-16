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
		width: 300
	},
	description: {
		fontSize: 16,
		color: material.colorDark
	},
	title: {
		fontSize: 24,
		fontWeight: 'bold',
		color: material.colorDark,
		margin: 15
	}
});
