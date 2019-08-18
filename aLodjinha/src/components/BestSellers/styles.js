import { StyleSheet } from 'react-native';
import { RFValue } from 'react-native-responsive-fontsize';

import MaterialDesign from '../../utils/MaterialDesign';

export default StyleSheet.create({
	container: {
		flex: 1
	},
	bestHeader: {
		paddingHorizontal: 15,
		paddingVertical: 15,
		borderBottomWidth: 1,
		borderColor: MaterialDesign.colorGreyish
	},
	bestTitle: {
		fontSize: RFValue(18),
		color: MaterialDesign.colorDark,
		fontWeight: 'bold'
	},
	bestContent: {}
});
