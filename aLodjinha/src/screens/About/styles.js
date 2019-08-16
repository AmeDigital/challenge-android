import { StyleSheet } from 'react-native';

export default StyleSheet.create({
	container: {
		flex: 1,
		alignItems: 'center',
		justifyContent: 'space-between'
	},
	header: {
		justifyContent: 'center',
		alignItems: 'center'
	},
	logo: {
		marginTop: 20
	},
	logoText: {
		fontFamily: 'Pacifico-Regular',
		fontSize: 55,
		color: '#2d3142'
	},
	footer: {
		justifyContent: 'center',
		alignItems: 'center',
		marginBottom: 20
	},
	nameDev: {
		fontWeight: 'bold',
		fontSize: 16,
		color: '#2d3142'
	},
	dataDev: {
		color: '#aaaaaa',
		margin: 10
	}
});
