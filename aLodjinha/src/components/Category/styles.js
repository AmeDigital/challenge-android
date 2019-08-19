import { StyleSheet } from 'react-native';

import material from '../../utils/MaterialDesign';

export default StyleSheet.create({
	image: {
		height: 100,
		width: 100
	},
	category: {
		justifyContent: 'center',
		alignItems: 'center',
		marginRight: 15,
		marginTop: 15,
		height: 120
	},
	description: {
		padding: 10,
		color: material.colorDark
	}
});
