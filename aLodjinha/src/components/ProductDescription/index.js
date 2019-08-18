import React, { useState } from 'react';
import { View, Image, Text } from 'react-native';

import { replacePonitForComma } from '../../utils/Utils';
import styles from './styles';

export default function ProductDescription({ product }) {
	const [ urlDefault, setUrlDefault ] = useState({ uri: product.urlImagem });

	function onError() {
		setUrlDefault(require('../../images/no-image.png'));
	}

	return (
		<View style={styles.productDescription}>
			<Image source={urlDefault} onError={onError} style={styles.productImage} />
			<Text style={styles.nameDescription}>{product.nome}</Text>
			<View style={styles.containerPrices}>
				<Text style={styles.priceOf}>{`De: ${replacePonitForComma(product.precoDe)}`}</Text>
				<Text style={styles.priceBy}>{`Por ${replacePonitForComma(product.precoPor)}`}</Text>
			</View>
			<View style={styles.containerDescription}>
				<Text style={styles.title}>Descrição</Text>
				<Text style={styles.description}>{product.descricao.replace(/<br\/>+/g, '\n')}</Text>
			</View>
		</View>
	);
}
