import React, { useState } from 'react';
import { Text, View, Image } from 'react-native';

import styles from './styles';

export default function Category({ item }) {
	const [ urlDefault, setUrlDefault ] = useState({ uri: item.urlImagem });

	function onError() {
		setUrlDefault(require('../../images/no-image.png'));
	}
	return (
		<View style={styles.category}>
			<Image style={styles.image} source={urlDefault} onError={onError} />
			<Text style={styles.description} numberOfLines={1}>
				{item.descricao}
			</Text>
		</View>
	);
}
