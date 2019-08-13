import React from 'react';
import { Image, Text } from 'react-native';

import logo from '../../images/logo_navbar.png';
import styles from './styles';
import Icon from 'react-native-vector-icons/MaterialIcons';

export default function LogoTitle() {
	return (
		<React.Fragment>
			<Icon name="menu" style={{ marginLeft: 15 }} size={24} color="#fff" />
			<Image source={logo} style={styles.image} />
			<Text style={styles.textTile}>o Lodjinha</Text>
		</React.Fragment>
	);
}
