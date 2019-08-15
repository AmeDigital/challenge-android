import React, { useEffect, useState } from 'react';
import { Text, View, StatusBar, Image, ScrollView } from 'react-native';

import api from '../../services/api';

import styles from './styles';

function DescriptionProductScreen({ navigation }) {
	const id = navigation.getParam('id');
	const [ product, setProduct ] = useState(undefined);

	useEffect(
		() => {
			(async function loadProduct() {
				const response = await api.get(`/produto/${id}`);
				setProduct(response.data);
			})();
		},
		[ id ]
	);

	return (
		<ScrollView>
			{product !== undefined && (
				<View style={styles.productDescription}>
					<StatusBar backgroundColor="#aaaaaa" />
					<Image
						source={{
							uri: product.urlImagem
						}}
						style={styles.productImage}
					/>
					<Text style={styles.nameDescription}>{product.nome}</Text>
					<View style={styles.containerPrices}>
						<Text style={styles.priceOf}>{`De: ${product.precoDe}`}</Text>
						<Text style={styles.priceBy}>{`Por ${product.precoPor}`}</Text>
					</View>
					<View style={styles.containerDescription}>
						<Text style={styles.title}>{product.categoria.descricao}</Text>
						<Text style={styles.description}>{product.descricao.replace(/<br\/>+/g, '\n')}</Text>
					</View>
				</View>
			)}
		</ScrollView>
	);
}

DescriptionProductScreen.navigationOptions = {
	headerStyle: {
		backgroundColor: '#fff',
		marginTop: StatusBar.currentHeight,
		elevation: 0
	},
	headerTintColor: '#333'
};

export default DescriptionProductScreen;
