import { StyleSheet } from 'react-native';

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
		fontSize: 20,
		fontFamily: 'Roboto',
		fontWeight: 'bold',
		color: '#2d3142',
		margin: 15
	},
	containerPrices: {
		borderTopColor: '#ccc',
		borderTopWidth: 1,
		borderBottomColor: '#ccc',
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
		color: '#aaaaaa',
		fontSize: 12,
		fontWeight: 'bold',
		textDecorationLine: 'line-through'
	},
	priceBy: {
		fontSize: 20,
		color: '#f15025',
		fontFamily: 'Roboto',
		fontWeight: 'bold'
	},
	containerDescription: {
		alignSelf: 'stretch'
	},
	title: {
		fontWeight: 'bold',
		fontSize: 18,
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
