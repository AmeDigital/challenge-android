import React, { useState } from 'react';
import { View, Image, Text, TouchableOpacity } from 'react-native';

import styles from './styles';
import { replacePonitForComma } from '../../utils/Utils';

export default function Product({ item, funcPage }) {
	const [ urlDefault, setUrlDefault ] = useState({ uri: item.urlImagem });

	function onError() {
		setUrlDefault(require('../../images/no-image.png'));
	}

	return (
		<TouchableOpacity style={styles.produto} onPress={() => funcPage(item.id, item.categoria.descricao)}>
			<Image style={styles.image} source={urlDefault} onError={onError} />
			<View style={styles.containerDescription}>
				<Text style={styles.textName} numberOfLines={2}>
					{item.nome}
				</Text>
				<View style={styles.containerPrices}>
					<Text style={styles.priceOf}>{`De: ${replacePonitForComma(item.precoDe)}`}</Text>
					<Text style={styles.priceBy}>{`Por ${replacePonitForComma(item.precoPor)}`}</Text>
				</View>
			</View>
			<View style={styles.containerImageRow}>
				<Image style={styles.imageRow} source={require('../../images/disclosure_indicator.png')} />
			</View>
		</TouchableOpacity>
	);
}
