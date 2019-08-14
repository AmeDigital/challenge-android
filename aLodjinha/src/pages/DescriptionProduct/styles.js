import { StyleSheet } from 'react-native';

export default StyleSheet.create({
	productDescription: {
		flex: 1,
		alignItems: 'center'
	},
	productImage: {
		height: 200,
		width: 200
	},
	nameDescription: {
		alignSelf: 'stretch',
		fontSize: 20,
		fontWeight: 'bold',
		color: '#2d3142',
		padding: 15,
		height: 100
	},
	containerPrices: {
		borderTopColor: '#ccc',
		borderTopWidth: 1,
		borderBottomColor: '#ccc',
		borderBottomWidth: 1,
		paddingHorizontal: 15,
		paddingVertical: 20,
		flexDirection: 'row',
		alignSelf: 'stretch',
		justifyContent: 'space-between',
		alignItems: 'center',
		height: 40
	},
	priceOf: {
		color: '#aaaaaa',
		fontSize: 15,
		fontWeight: 'bold',
		textDecorationLine: 'line-through'
	},
	priceBy: {
		fontSize: 23,
		color: '#f15025',
		fontFamily: 'Roboto',
		fontWeight: 'bold'
	},
	containerDescription: {
		alignSelf: 'stretch'
	},
	title: {
		fontWeight: 'bold',
		fontSize: 20,
		padding: 15,
		color: '#333'
	},
	description: {
		padding: 15,
		color: '#333'
	}
});
