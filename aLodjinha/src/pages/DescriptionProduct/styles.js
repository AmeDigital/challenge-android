import { StyleSheet } from 'react-native';

import { RFValue } from 'react-native-responsive-fontsize';

import material from '../../utils/MaterialDesign';

export default StyleSheet.create({
	productDescription: {
		flex: 1,
		alignItems: 'center',
		marginTop: 20
	},
	productImage: {
		height: 200,
		width: 200
	},
	nameDescription: {
		alignSelf: 'stretch',
		fontSize: RFValue(20),
		fontFamily: 'Roboto',
		fontWeight: 'bold',
		color: material.colorDark,
		margin: 15
	},
	containerPrices: {
		borderTopColor: material.colorGreyish,
		borderTopWidth: 1,
		borderBottomColor: material.colorGreyish,
		borderBottomWidth: 1,
		paddingHorizontal: 15,
		paddingVertical: 30,
		flexDirection: 'row',
		alignSelf: 'stretch',
		justifyContent: 'space-between',
		alignItems: 'center',
		height: 40
	},
	priceOf: {
		color: material.colorWhite,
		fontSize: RFValue(12),
		fontWeight: 'bold',
		textDecorationLine: 'line-through'
	},
	priceBy: {
		fontSize: normalize(20),
		color: material.colorTomato,
		fontFamily: 'Roboto',
		fontWeight: 'bold'
	},
	containerDescription: {
		alignSelf: 'stretch'
	},
	title: {
		fontWeight: 'bold',
		fontSize: RFValue(18),
		marginLeft: 15,
		marginTop: 15,
		color: material.colorDark
	},
	description: {
		padding: 17,
		color: material.colorDark,
		letterSpacing: 0.4
	}
});
