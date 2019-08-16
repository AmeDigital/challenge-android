import { StyleSheet } from 'react-native';

export default StyleSheet.create({
	container: {
		flex: 1,
		justifyContent: 'center',
		alignItems: 'center'
	},
	image: {
		resizeMode: 'contain',
		width: 300
	},
	description: {
		fontSize: 16,
		color: '#333'
	},
	title: {
		fontSize: 22,
		fontWeight: 'bold',
		color: '#333',
		margin: 15
	}
});
