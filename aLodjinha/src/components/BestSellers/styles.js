import { StyleSheet } from 'react-native';
import MaterialDesign, { normalize } from '../../utils/MaterialDesign';

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
		fontSize: normalize(18),
		color: MaterialDesign.colorDark,
		fontWeight: 'bold'
	},
	bestContent: {}
});
