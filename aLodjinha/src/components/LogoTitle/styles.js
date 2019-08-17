import { StyleSheet } from 'react-native';

import material, { normalize } from '../../utils/MaterialDesign';

export default StyleSheet.create({
	image: {
		width: 30,
		height: 30,
		marginHorizontal: 15
	},
	textTile: {
		color: material.colorWhiteTwo,
		fontSize: normalize(24),
		letterSpacing: 0.6,
		fontFamily: material.fontPacificoRegular
	}
});
