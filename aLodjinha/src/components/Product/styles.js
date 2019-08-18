import { StyleSheet } from 'react-native';
import { RFValue } from 'react-native-responsive-fontsize';

import material from '../../utils/MaterialDesign';

export default StyleSheet.create({
	produto: {
		flexDirection: 'row',
		justifyContent: 'space-between',
		paddingVertical: 15,
		paddingHorizontal: 5,
		borderBottomColor: material.colorGreyish,
		borderBottomWidth: 1
	},
	image: {
		width: 100,
		height: 100,
		marginRight: 5
	},
	containerDescription: {
		flex: 1,
		padding: 10
	},
	textName: {
		fontSize: RFValue(18),
		fontWeight: 'bold',
		color: material.colorDark,
		fontFamily: 'Roboto'
	},
	containerPrices: {
		flexDirection: 'row',
		justifyContent: 'space-between',
		alignItems: 'center',
		height: 40
	},
	priceOf: {
		color: material.colorGreyish,
		fontSize: RFValue(12),
		fontWeight: 'bold',
		textDecorationLine: 'line-through'
	},
	priceBy: {
		fontSize: RFValue(20),
		color: material.colorTomato,
		fontFamily: 'Roboto',
		fontWeight: 'bold'
	},
	containerImageRow: {
		width: 20,
		justifyContent: 'center',
		alignItems: 'center'
	},
	imageRow: {}
});
