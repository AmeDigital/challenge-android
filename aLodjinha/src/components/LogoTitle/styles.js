import { StyleSheet } from 'react-native';
import { RFValue } from 'react-native-responsive-fontsize';

import material from '../../utils/MaterialDesign';

export default StyleSheet.create({
	image: {
		width: 30,
		height: 30,
		marginHorizontal: 15
	},
	textTile: {
		color: material.colorWhiteTwo,
		fontSize: RFValue(24),
		letterSpacing: 0.6,
		fontFamily: material.fontPacificoRegular
	}
});
