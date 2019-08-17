import { StyleSheet } from 'react-native';

import material from '../../utils/MaterialDesign';

export default StyleSheet.create({
	produto: {
		flexDirection: 'row',
		justifyContent: 'space-between',
		padding: 15,
		borderBottomColor: '#ccc',
		borderBottomWidth: 1
	},
	image: {
		width: 80,
		height: 90,
		marginRight: 5
	},
	containerDescription: {
		flex: 1,
		padding: 10
	},
	textName: {
		fontSize: 17,
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
		fontSize: 12,
		fontWeight: 'bold',
		textDecorationLine: 'line-through'
	},
	priceBy: {
		fontSize: 20,
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
