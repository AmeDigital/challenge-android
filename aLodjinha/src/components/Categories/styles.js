import { StyleSheet } from 'react-native';
import { RFValue } from 'react-native-responsive-fontsize';
import MaterialDesign from '../../utils/MaterialDesign';

export default StyleSheet.create({
	container: {
		height: 200,
		borderBottomColor: MaterialDesign.colorGreyish,
		borderBottomWidth: 1
	},
	categoryHeader: {
		paddingHorizontal: 15,
		paddingVertical: 15,
		borderBottomWidth: 1,
		borderColor: MaterialDesign.colorGreyish
	},
	categoryTitle: {
		fontSize: RFValue(18),
		color: MaterialDesign.colorDark,
		fontWeight: 'bold'
	},
	categoryContent: {}
});
