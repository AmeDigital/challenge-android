import { StyleSheet } from 'react-native';

import material from '../../utils/MaterialDesign';

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
		marginTop: 50
	},
	logoText: {
		fontFamily: material.fontPacificoRegular,
		fontSize: 64,
		letterSpacing: 1.5,
		color: material.colorDark
	},
	footer: {
		justifyContent: 'center',
		alignItems: 'center',
		marginBottom: 20
	},
	nameDev: {
		fontWeight: 'bold',
		fontSize: 14,
		letterSpacing: 0.3,
		color: material.colorDark
	},
	dataDev: {
		color: material.colorDark,
		letterSpacing: 0.3,
		margin: 10,
		fontSize: 12
	}
});
